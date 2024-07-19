<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>

package com.example.demo.Bookinfo;

<html lang="ko">
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
	<form action="/searchBook">
		검색 : <input type=text name="key">
		<input type="submit" value="검색">
	</form>
	
	<table border=2>
	<tr>
		<th>도서명</th>
		<th>저자</th>
		<th>ISBN</th>
		<th>상세정보</th>
		<th>출판날짜</th>
		<th>도서삭제</th>
		<th>도서수정</th>
		<th>파일</th>
	</tr>
	
	<c:forEach var="book" items="${paging.content}">
	<tr>
		<td>${book.title}</td>
		<td>${book.author}</td>
		<td>${book.isbn}</td>
		<td>${book.info}</td>
		<td><tf:formatDateTime value="${book.publishedDate}" pattern="yyyy-MM-dd" /></td>
		<td>
			<form action="/deleteBook" style="display: flex; align-items:center; margin:10px">
			<input type="hidden" name="num" value="${book.num}">
			<input type=submit value="삭제">
			</form>	
		</td>
		<td>
			<form action="/modifyBook" style="display: flex; align-items:center; margin:10px">
			<input type="hidden" name="num" value="${book.num}">
			<input type=submit value="수정">
			</form>	
		</td>
		<td>
			<img src="."
	</tr>
	</c:forEach>
	</table>
	
	<c:if test="${paging.totalPages > 0}">
	    <c:choose>
	        <c:when test="${startPage == 1}">
	            이전
	        </c:when>
	        <c:otherwise>
	            <a href="/list?page=${startPage - 1}">이전</a>
	        </c:otherwise>
	    </c:choose>
	    
	    <c:forEach begin="${startPage}" end="${endPage}" var="count">
	        <c:choose>
	            <c:when test="${count != currentPage}">
	                <a href="/list?page=${count}">${count}</a>
	            </c:when>
	            <c:otherwise>
	                ${count}
	            </c:otherwise>
	        </c:choose>
	    </c:forEach>
	    
	    <c:choose>
	        <c:when test="${startPage > paging.totalPages-5}">
	            다음
	        </c:when>
	        <c:otherwise>
	            <a href="/list?page=${endPage + 1}">다음</a>
	        </c:otherwise>
	    </c:choose>
	</c:if>
	

	</body>
</html>