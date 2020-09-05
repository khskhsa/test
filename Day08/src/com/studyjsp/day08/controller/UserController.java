package com.studyjsp.day08.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.studyjsp.day08.domain.LoginVO;
import com.studyjsp.day08.domain.UserDTO;
import com.studyjsp.day08.persistence.BoardConnectionMaker;
import com.studyjsp.day08.persistence.UserDAO;

@WebServlet("/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserDAO userDAO = new UserDAO(new BoardConnectionMaker());
	
    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// request와  response에 대한 타입을 지정해줘야 한다.
		//   => 문서 인코딩 타입 (Content Type)
		
		// 요청 받을 문자 타입을 UTF-8로 설정함
		request.setCharacterEncoding("UTF-8");
		
		// 응답할 타입을 UTF-8로 설정함
		response.setContentType("text/html;charset=UTF-8");
		
		String reqUri = request.getRequestURI();
		reqUri = reqUri.substring(reqUri.lastIndexOf("/"));
		
		try {
			
			if(reqUri.equals("/doLogin")) {
				
				String userid = request.getParameter("userid");
				String userpw = request.getParameter("userpw");
				
				LoginVO loginVO = userDAO.doLogin(userid, userpw);
				
				if(loginVO != null) {
					// 로그인 성공
					//  -> /board/list로 리다이렉트
					//   로그인 성공시에는 클라이언트가 새로운 작업을 요청 하도록 함.
					
					System.out.println("로그인 성공");
					
					// 로그인 성공의 결과물인 loginVO를 세션에 등록시켜서
					//  세션이 유지되는 동안(브라우저 종료, invalidate(), timeout)
					//  계속 로그인을 유지할 수 있도록 한다.
					
					// * 결론적으로 세션에 loginVO가 있으면 로그인을 하고 있다라고 생각한다.
					
					
					// session은 최초의 request에서 생성되고, 모든 request는
					// session 정보를 가지고 있다.
					HttpSession session = request.getSession();
					session.setAttribute("login", loginVO);
					
					// 클라이언트에게 리다이렉트 주소 전송
					response.sendRedirect("/board/list");
					
				}else {
					// 로그인 실패
					//  -> /board/loginForm으로 포워드
					//   로그인 실패시에는 클라이언트가 실패한 id를 가지고 다시 로그인을
					// 할 수 있도록 포워드
					System.out.println("로그인 실패");
					
					// 1) 실패한 아이디를 attribute로 저장
					request.setAttribute("fail", userid);
					
					// 2) 단순하게 페이지만 띄워주기 위해 loginForm.jsp로 이동
					RequestDispatcher rd =
							request.getRequestDispatcher("/loginForm.jsp");
					rd.forward(request, response);
					
				}
			} else if(reqUri.equals("/loginForm")) {
				// 로그인 페이지로 이동하기
				//   더이상 페이지를 보기위해 .jsp로 끝나는 url을 사용하지 않는다.
				
				// 추가적인 자바 코드 처리를 위해서...
				
				//세션 검사 -> 로그인이 된 상태에서 접근하면 /board/list로 리다이렉트
				if(request.getSession().getAttribute("login") != null) {
					System.out.println("이미 로그인이 된 상태 입니다.");
					response.sendRedirect("/board/list");
					return;	// 메소드 종료, 포워드 방지
				}
				
				RequestDispatcher rd =
							request.getRequestDispatcher("/loginForm.jsp");
				rd.forward(request, response);
			} else if(reqUri.equals("/logout")) {
				// invalidate() : 세션을 초기화
				// 기존에 있던 세션을 삭제하고 새로운 세션을 만들때 사용함.
				request.getSession().invalidate();
				response.sendRedirect("/user/loginForm");
			} else if(reqUri.equals("/joinForm")) {
				request.getRequestDispatcher("/joinForm.jsp")
							.forward(request, response);
			} else if(reqUri.equals("/doJoin")) {
				//1 ) 회원가입을 위한 파라미터 수집
				String userid = request.getParameter("userid");
				String userpw = request.getParameter("userpw");
				String username = request.getParameter("username");
				String email = request.getParameter("email");
				
				// 2) dao에 전달할 dto 만들기
				UserDTO userDTO = new UserDTO();
				userDTO.setUserid(userid);
				userDTO.setUserpw(userpw);
				userDTO.setUsername(username);
				userDTO.setEmail(email);
				
				// 3) DAO에서 메소드 호출
				userDAO.insertUser(userDTO);
				
				response.sendRedirect("/");
				
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
