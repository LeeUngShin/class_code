<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<link href="" rel="stylesheet" />
		<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
	</head>
	<body>

		<h1>로그인</h1>
		<form method="post" action="/login">
		<label for="id">아이디 : 
		<input type="text" name="id" id="id" value = "${cookie.REMEMBER.value}"><br>
		<label for=pw>비밀번호 : 
		<input type="password" name="pw" id="pw"><br>
		아이디 기억하기
		<input type="checkbox" name="idMemory" ><br>
		<input type=submit value="로그인""><br>
		<form>
		<h1>${cookie.REMEMBER.value}</h1>
	</body>
</html>