<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>

	<%
		Object fail = request.getAttribute("fail");
		fail = (fail == null) ? "" : fail;
	%>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">로그인</div>
			<div class="panel-body">
				<form action="/user/doLogin" method="post" class="form-inline">
					<div class="form-group">
						<label for="userid">아이디</label> <input type="text" name="userid"
							id="userid" value="<%=fail%>" class="form-control">
					</div>
					<div class="form-group">
						<label for="userpw">비밀번호</label> <input type="password"
							name="userpw" id="userpw" class="form-control">
					</div>
					<button class="btn btn-primary" type="submit">로그인</button>
				</form>
			</div>
		</div>


	</div>
</body>
</html>