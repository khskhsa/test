<%@page import="com.studyjsp.day08.domain.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	
	<% LoginVO loginVO = (LoginVO)session.getAttribute("login"); %>
	
	<form action="/board/register" method="post">
		<div>
			<label>제목</label>
			<input type="text" name="title" id="title">
		</div>
		<div>
			<label>내용</label>
			<textarea name="contents" id="contents"></textarea>
		</div>
		<div>
			작성자 : <%= loginVO.getUsername() %>
		</div>
		<input type="hidden" name="userid" value="<%= loginVO.getUserid() %>">
		<input type="submit" value="글 등록">
	</form>
	
</body>
</html>




