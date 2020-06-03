package com.test.board.model;

public class BoardVO {
	
	String regdate;
	String bno;
	String writer;
	String title;
	String content;
	
	public BoardVO() {
	
	}
	
	
	public BoardVO(String writer, String title, String content, String regdate, String bno) {
		super();
		this.regdate = regdate;
		this.bno = bno;
		this.writer = writer;
		this.title = title;
		this.content = content;
	}



	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
