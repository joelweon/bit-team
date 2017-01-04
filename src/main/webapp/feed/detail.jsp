<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>피드관리-상세정보</title>
</head>
<body>
<jsp:include page="../header"></jsp:include>

<h1>상세 정보</h1>
<form action='update' method='POST'>
<table border='1'>
<tr><th>게시물 번호</th><td><input name='cono' type='text' value='${feed.contentNo}' readonly></td></tr>
<tr><th>조회수</th><td><input name='vw_cnt' type='text' value='${feed.viewCount}' readonly></td></tr>
<tr><th>작성자 이름</th><td><input name='name' type='text' value='${feed.name}' readonly></td></tr>
<tr><th>등록일</th><td><input name='rdt' type='text' value='${feed.registerDate}'></td></tr>
<tr><th>내용</th><td><input name='conts' type='text' value='${feed.contents}'></td></tr>
</table>
<button type='submit'>변경</button>
 <a href='delete?contentNo=${feed.contentNo}'>삭제</a>
 <a href='list'>목록</a>
</form>

<jsp:include page="../footer"></jsp:include>
</body>
</html>