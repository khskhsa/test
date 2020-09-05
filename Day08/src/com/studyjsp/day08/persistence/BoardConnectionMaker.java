package com.studyjsp.day08.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class BoardConnectionMaker implements ConnectionMaker {

	@Override
	public Connection getConnection() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");

		// 한글처리를 위해서는 useUnicode 옵션, characterEncoding 옵션을 줘야 한다.
		String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC"
				+ "&useUnicode=true&characterEncoding=UTF-8";
		
		String username = "studyjsp";
		String password = "1111";
		
		Connection conn = DriverManager.getConnection(url, username, password);
		
		return conn;
	}
	
}


