package com.studyjsp.day08.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studyjsp.day08.domain.BoardDTO;
import com.studyjsp.day08.domain.BoardVO;
import com.studyjsp.day08.domain.LoginVO;
import com.studyjsp.day08.persistence.BoardConnectionMaker;
import com.studyjsp.day08.persistence.BoardDAO;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private BoardDAO boardDAO = new BoardDAO(new BoardConnectionMaker());
	
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		// 로그인 정보가 없으면 /user/loginForm으로 리다이렉트
		if(request.getSession().getAttribute("login") == null) {
			System.out.println("로그인이 되지 않았습니다.");
			response.sendRedirect("/user/loginForm");
			return;
		}
		
		String reqUri = request.getRequestURI();
		reqUri = reqUri.substring(reqUri.lastIndexOf("/"));
		
		try {
			
			if(reqUri.equals("/list")) {
				
				List<BoardVO> boardList = boardDAO.getList();
				
				request.setAttribute("boardList", boardList);
				
				RequestDispatcher rd = 
							request.getRequestDispatcher("/boardList.jsp");
				rd.forward(request, response);
				
			}else if(reqUri.equals("/get")) {
				// 1) 게시글 번호 받기
				// 2) DAO에서 받아온 게시글 번호를 이용해 조회
				// 3) get.jsp로 포워딩
				
				Integer bnum = Integer.parseInt(request.getParameter("bnum"));
				BoardVO boardVO = boardDAO.getBoard(bnum);
				
				request.setAttribute("boardVO", boardVO);
				
				RequestDispatcher rd = request.getRequestDispatcher("/get.jsp");
				rd.forward(request, response);
				
			}else if(reqUri.equals("/registerForm")) {
				
				RequestDispatcher rd = 
						request.getRequestDispatcher("/register.jsp");
				rd.forward(request, response);
				
			}else if(reqUri.equals("/register")) {
				
				//파라미터 수집
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				String userid = request.getParameter("userid");
				
				// DTO 구성하기
				BoardDTO dto = new BoardDTO();
				dto.setUserid(userid);
				dto.setContents(contents);
				dto.setTitle(title);
				
				int row = boardDAO.registerBoard(dto);
				
				// 게시글 삽입이 성공하면 /board/list로 리다이렉트
				response.sendRedirect("/board/list");
				
			}else if(reqUri.equals("/modifyForm")) {
				
				Integer bnum = Integer.parseInt(request.getParameter("bnum"));
				
				// 1) 게시글 가져오기
				BoardVO boardVO = boardDAO.getBoard(bnum);
				
				request.setAttribute("boardVO", boardVO);
				
				RequestDispatcher rd = 
						request.getRequestDispatcher("/modiForm.jsp");
				rd.forward(request, response);
				
			}else if(reqUri.equals("/modify")) {
				
				//파라미터 수집
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				
				// userid와 bnum은 hidden으로 숨겨진 형태로 들어옴.
				String userid = request.getParameter("userid");
				Integer bnum  = Integer.parseInt(request.getParameter("bnum"));
				
				BoardDTO board = new BoardDTO();
				board.setContents(contents);
				board.setTitle(title);
				board.setUserid(userid);
				
				boardDAO.updateBoard(board, bnum);
				
				response.sendRedirect("/board/list");
				
				
			}else if(reqUri.equals("/delete")) {
				// bnum은 parameter에서 수집.
				Integer bnum = Integer.parseInt(request.getParameter("bnum"));
				// userid는 세션에서 꺼내옴
				
				LoginVO login = (LoginVO)request.getSession().getAttribute("login");
				String userid = login.getUserid();
				
				boardDAO.deleteBoard(bnum, userid);
				
				response.sendRedirect("/board/list");
				
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
