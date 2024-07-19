<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<script>
		var loginok = '<%=(String)session.getAttribute("id")%>';
		if(loginok != "" && loginok != "null") {
			//alert(loginok);
			location.replace("/loginok");
		}
	</script>
	<body>

		<a href="/login" onclick>로그인 하러 가기</a><br>
		<a href="/join">회원가입 하러 가기</a><br>
	jsp 파일임
	</body>
</html>