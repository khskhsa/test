<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
	<form action="/user/doJoin" method="post">
		<div>
			<label for="userid">아이디 : </label>
			<input type="text" name="userid" id="userid" placeholder="아이디 입력">
		</div>
		<div>
			<label for="userpw">패스워드 : </label>
			<input type="password" name="userpw" id="userpw" placeholder="비밀번호 입력">
		</div>
		<div>
			<label for="username">사용자 이름 : </label>
			<input type="text" name="username" id="username" placeholder="이름 입력">
		</div>
		<div>
			<label for="email">이메일: </label>
			<input type="email" name="email" id="email" placeholder="이메일 입력">
		</div>
		테스트 수정-21:00
		<input type="submit" value="회원가입">
	</form>
</body>
</html>