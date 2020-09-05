package com.studyjsp.day08.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studyjsp.day08.domain.BoardDTO;
import com.studyjsp.day08.domain.BoardVO;

public class BoardDAO {
	private ConnectionMaker connectionMaker;
	
	public BoardDAO(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	// 게시글 리스트 보여주기
	public List<BoardVO> getList() throws Exception{
		Connection conn = connectionMaker.getConnection();
		String sql = "select bnum, title, userid, creatime from tb_board";
		PreparedStatement pstmt = conn.prepareStatement(sql);
	
		ResultSet rs = pstmt.executeQuery();
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		while(rs.next()) {
			Integer bnum = rs.getInt("bnum");
			String title = rs.getString("title");
			String userid = rs.getString("userid");
			String creatime = rs.getString("creatime");
			
			BoardVO boardVO = new BoardVO();
			boardVO.setBnum(bnum);
			boardVO.setTitle(title);
			boardVO.setUserid(userid);
			boardVO.setCreatime(creatime);
			
			boardList.add(boardVO);
			
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return boardList;
	}
	
	public BoardVO getBoard(int bnum) throws Exception {
		Connection conn = connectionMaker.getConnection();
		
		String sql = "select b.bnum, b.title,\r\n" + 
				"	   	     b.contents, u.username, u.userid,\r\n" + 
				"            b.creatime, b.moditime\r\n" + 
				"	  from tb_user u, tb_board b\r\n" + 
				"     where u.userid = b.userid \r\n" + 
				"       and b.bnum = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bnum);
		
		ResultSet rs = pstmt.executeQuery();
		
		BoardVO boardVO = null;
		if(rs.next()) {
			boardVO = new BoardVO();
			Integer bNumber = rs.getInt("bnum");
			String title = rs.getString("title");
			String contents = rs.getString("contents");
			String username = rs.getString("username");
			String creatime = rs.getString("creatime");
			String moditime = rs.getString("moditime");
			
			//추가
			String userid = rs.getString("userid");
			
			boardVO.setBnum(bNumber);
			boardVO.setTitle(title);
			boardVO.setContents(contents);
			boardVO.setUserid(userid);		// username에서 userid로 변경
			boardVO.setUsername(username);  //사용자 이름 추가
			boardVO.setCreatime(creatime);
			boardVO.setModitime(moditime);
		}
		
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return boardVO;
	}
	
	// 게시글 등록하기
	public int registerBoard(BoardDTO board) throws Exception{
		Connection conn = connectionMaker.getConnection();
		
		String sql = "insert into tb_board(title, contents, userid)\r\n" + 
					 "values(?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, board.getTitle());
		pstmt.setString(2, board.getContents());
		pstmt.setString(3, board.getUserid());
		
		int row = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return row;
	}
	
	public int updateBoard(BoardDTO board, Integer bnum) throws Exception {
		Connection conn = connectionMaker.getConnection();
		String sql = "update tb_board set\r\n" + 
					 "title = ?,\r\n" + 
					 "contents = ?,\r\n" + 
					 "moditime = current_timestamp\r\n" + 
					 "where userid = ?\r\n" + 
					 "  and bnum = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, board.getTitle());
		pstmt.setString(2, board.getContents());
		pstmt.setString(3, board.getUserid());
		pstmt.setInt(4, bnum);
		
		int row = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return row;
	}
	
	public int deleteBoard(int bnum, String userid) throws Exception {
		Connection conn = connectionMaker.getConnection();
		String sql = "delete from tb_board where bnum=? and userid=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, bnum);
		pstmt.setString(2, userid);
		
		int row = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return row;
	}
	
}











