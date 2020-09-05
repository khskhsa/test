package com.studyjsp.day08.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.studyjsp.day08.domain.LoginVO;
import com.studyjsp.day08.domain.UserDTO;

public class UserDAO {

	private ConnectionMaker connectionMaker;
	
	public UserDAO(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	public LoginVO doLogin(String userid, String userpw) throws Exception {
		Connection conn = connectionMaker.getConnection();
		String sql = "select userid, username, email\r\n" + 
					 "from tb_user\r\n" + 
					 "where userid = ?\r\n" + 
					 "  and userpw = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userid);
		pstmt.setString(2, userpw);
		
		ResultSet rs = pstmt.executeQuery();
		
		LoginVO loginVO = null;
		
		if(rs.next()) {
			String loginUserId = rs.getString("userid");
			String loginUserName = rs.getString("username");
			String loginUserEmail = rs.getString("email");
			
			loginVO = new LoginVO();
			loginVO.setUserid(loginUserId);
			loginVO.setUsername(loginUserName);
			loginVO.setEmail(loginUserEmail);
			
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return loginVO;
	}
	
	public int insertUser(UserDTO dto) throws Exception {
		Connection conn = connectionMaker.getConnection();
		String sql = "insert into tb_user(userid, userpw, username, email)\r\n" + 
					 "values(?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dto.getUserid());
		pstmt.setString(2, dto.getUserpw());
		pstmt.setString(3, dto.getUsername());
		pstmt.setString(4, dto.getEmail());
		
		int row = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return row;
	}
	
}









