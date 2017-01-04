<%@ page language="java"
    contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>자료실-상세정보</title>
</head>
<body>
  
<jsp:include page="../header"/>

<h1>자료실</h1>
<form action='update' method='POST' enctype='multipart/form-data'>
<table border='1'>
<input type='hidden' name='contentNo' value=${download.contentNo}><tr><th>작성회원 일련번호</th><td><input name='memberNo' type='text' value='${download.memberNo}'></td></tr>
<tr><th>등록일</th><td><input name='registerDate' type='text' value='${download.registerDate}'></td></tr>
<tr><th>파일</th><td><a href='../upload/${download.path}'>${download.path}</a><br><input name='filepath' type='file' value='${download.path}'></td></tr>
</table>
<button type='submit'>변경</button>
<a href='delete?contentNo=${download.contentNo}&filename=${download.path}'>삭제</a>
<a href='list'>목록</a>

<jsp:include page="../footer"/>

</body>
</html>