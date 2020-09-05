<%@page import="com.studyjsp.day08.domain.LoginVO"%>
<%@page import="com.studyjsp.day08.domain.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 조회 페이지</title>
</head>
<body>
	<% BoardVO boardVO = (BoardVO)request.getAttribute("boardVO"); %>
	<table border="1">
		<tr>
			<th>글번호 / 작성자</th>
			<td><%= boardVO.getBnum() %> / <%= boardVO.getUsername() %></td>		
		</tr>
		<tr>
			<th>제목</th>
			<td><%= boardVO.getTitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><%= boardVO.getContents() %></td>
		</tr>
		<tr>
			<th>작성시간 / 수정시간</th>
			<td><%= boardVO.getCreatime() %> / <%= boardVO.getModitime() %></td>
		</tr>
	</table>
	<!-- 로그인을 한 사람과 게시글 작성자가 일치하는지를 확인 -->
	<%
		LoginVO login = (LoginVO)session.getAttribute("login");
		if(boardVO.getUserid().equals(login.getUserid())){ %>
			<a href="/board/delete?bnum=<%= boardVO.getBnum()%>">글 삭제</a>
			<!-- 수정 대상 게시글의 bnum을 넘기면 된다. -->
			<a href="/board/modifyForm?bnum=<%= boardVO.getBnum()%>">글 수정</a>
	<% } %>
</body>
</html>





