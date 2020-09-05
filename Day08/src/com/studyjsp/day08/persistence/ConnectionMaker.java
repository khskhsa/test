package com.studyjsp.day08.persistence;

import java.sql.Connection;

public interface ConnectionMaker {
	
	// 모든 ConnectionMaker가 구현 해야 할 getConnection() 메소드를 추상화시킴
	public Connection getConnection() throws Exception;
	
}
