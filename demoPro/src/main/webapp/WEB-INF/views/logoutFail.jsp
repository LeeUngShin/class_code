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
		로그아웃 실패
	</body>
</html>