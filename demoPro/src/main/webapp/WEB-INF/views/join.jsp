<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<form method="post" action="/join">
		<label for="id">아이디 : 
		<input type="text" name="id" id="id"><br>
		${error.id}
		<label for=pw>비밀번호 : 
		<input type="password" name="pw" id="pw"><br>
		<label for=name>이름 : 
		<input type="text" name="name" id="name"><br>
		<label for=addr>주소 : 
		<input type="text" name="addr" id="addr"><br>
		<label for=age>나이 : 
		<input type="number" name="age" id="age"><br>
		<label for=email>이메일 : 
		<input type="email" name="email" id="email"><br>
		<input type=submit value="회원가입"><br>
		<form>
	</body>
</html>