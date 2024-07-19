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
		로그인 실패
		<input type="button" value="로그인 다시 하기" onclck="javascript:location.replace('/login')">
	</body>
</html>