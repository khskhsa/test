<%@page import="com.studyjsp.day08.domain.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="com.studyjsp.day08.domain.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
	<h3>${ login.username }님 안녕하세요</h3>
	<a href="/user/logout">로그아웃</a>
	<a href = "/board/registerForm">글 작성하기</a>
	<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성시간</th>
		</tr>
		<c:forEach items="${ boardList }" var="vo">
			<tr>
				<td>${ vo.bnum }</td>
				<td>${ vo.title } </td>
				<td>${ vo.userid } </td>
				<td>${ vo.creatime }</td> 
			</tr>
		</c:forEach>
	</table>
</body>
</html>




