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
		도서 수정<br>
		<form name="bookModify" action="/modifytbook" method="POST">
			책 제목 : <input type="text" name="title" value=${title}"><br>
			ISBN : <input type="text" name="isbn" value=${isbn} readonly><br>
			저자명 : <input type="text" name="author" value=${author}><br>
			발행일 : <input type="date" name="publishedDate" value=${publishedDate}><br>
			책 정보 : <textarea name="info">${info}</textarea><br>
			<input type="hidden" id="isbn" value = "${isbn}">
			<input type="submit" value = "책 수정하기">
		</form>
	</body>
</html>