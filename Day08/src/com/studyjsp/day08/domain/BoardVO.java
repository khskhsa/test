package com.studyjsp.day08.domain;

// 게시글 리스트 보여주기, 게시글 상세 조회 할때 사용할 VO
public class BoardVO {

	private Integer bnum;
	private String title;
	private String contents;
	private String userid;
	private String creatime;
	private String moditime;
	
	//추가
	private String username;
	
	public Integer getBnum() {
		return bnum;
	}
	public void setBnum(Integer bnum) {
		this.bnum = bnum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCreatime() {
		return creatime;
	}
	public void setCreatime(String creatime) {
		this.creatime = creatime;
	}
	public String getModitime() {
		return moditime;
	}
	public void setModitime(String moditime) {
		this.moditime = moditime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
