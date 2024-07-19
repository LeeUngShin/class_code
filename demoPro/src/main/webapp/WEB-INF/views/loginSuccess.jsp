<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<script>
		var loginok = '<%=(String)session.getAttribute("id")%>';
		if(loginok == "" || loginok == "null") {
			alert("로그인이 되어있지 않습니다.");
			location.replace("/");
		}
	</script>
	<body>
		로그인 성공<br>
		현재 로그인된 아이디 : ${loginId}<br><br>
		${sessionScope.id}
		<br><br>
		<form method="get" action="/modify">
			<input type="submit" value="회원정보 수정하기">
		</form>
		<!--<form action="/modify">
			ID : <input type="text" name = "id" value="${loginId}" readonly><br>
			Password : <input type="text" name = "password" value="${password}" readonly><br>
			이름 : <input type="text" name = "name" value="${name}"><br>
			주소 : <input type="text" name = "addr" value="${addr}"><br>
			나이 : <input type="age" name = "age" value="${age}"><br>
			이메일 : <input type="email" name = "email" value="${email}"><br>
		</form> -->
		<input type=button value="책 등록" onclick="javascript:location.replace('/insertbook')">
		<input type=button value="책 리스트보러가기" onclick="javascript:location.replace('/list?page=1')">
		<form method="get" action="/logout">
			<input type="submit" value="로그아웃">
		</form>
			<br>
			
		<form action = "/delete" method="post">
			<input type="hidden" name="userId" value="${loginId}">
			<input type="submit" value="회원탈퇴">
		</form>
	</body>
</html>