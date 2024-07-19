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
		도서 등록<br>
		<form name="bookRegister" action="/insertbook" method="POST" enctype="multipart/form-data">
			책 제목 : <input type="text" name="title"><br>
			ISBN : <input type="text" name="isbn"><br>
			저자명 : <input type="text" name="author"><br>
			발행일 : <input type="date" name="publishedDate"><br>
			책 정보 : <textarea name="info"></textarea><br>
			<input type="file" name="file"><br>
			<input type="submit" value = "책 추가하기">
		</form>
	</body>
</html>