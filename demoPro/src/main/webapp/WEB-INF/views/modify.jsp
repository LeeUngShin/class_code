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
		
		<form action="/modify" method=post>
			ID : <input type="text" name = "id" value="${id}" readonly><br>
			Password : <input type="text" name = "password"><br>
			이름 : <input type="text" name = "name" value="${name}"><br>
			주소 : <input type="text" name = "addr" value="${addr}"><br>
			나이 : <input type="number" name = "age" value="${age}"><br>
			이메일 : <input type="email" name = "email" value="${email}"><br>
			<input type=submit value="회원정보수정">
		</form>

	</body>
</html>