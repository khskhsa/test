<%@page import="com.studyjsp.day08.domain.BoardVO"%>
<%@page import="com.studyjsp.day08.domain.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정하기</title>
</head>
<body>
	
	<% 
		BoardVO boardVO = (BoardVO)request.getAttribute("boardVO");
	%>
	
	<form action="/board/modify" method="post">
		<div>
			<label>제목</label>
			<input type="text" name="title" id="title" value="<%= boardVO.getTitle() %>">
		</div>
		<div>
			<label>내용</label>
			<textarea name="contents" id="contents"><%= boardVO.getContents() %></textarea>
		</div>
		<div>
			작성자 : <%= boardVO.getUsername() %>
		</div>
		<input type="hidden" name="userid" value="<%= boardVO.getUserid() %>">
		<input type="hidden" name="bnum" value="<%= boardVO.getBnum() %>">
		<input type="submit" value="글  수정">
	</form>
</body>
</html>