<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>강의실관리-상세정보</title>
</head>
<body>

<jsp:include page="../header"></jsp:include>

<h1>학생 정보</h1>
<form action='update' method='POST' enctype='multipart/form-data'>
<table border='1'>
<tr><th>강의실이름</th><td><input name='email' type='text' value='${classroom.name}'></td></tr>
<tr><th>사진</th><td><img src='../upload/${classroom.photoList[0].filePath}' height='80'><input name='photoPath1' type='file'></td></tr>
<tr><th>사진</th><td><img src='../upload/${classroom.photoList[1].filePath}' height='80'><input name='photoPath2' type='file'></td></tr>
<tr><th>사진</th><td><img src='../upload/${classroom.photoList[2].filePath}' height='80'><input name='photoPath3' type='file'></td></tr>
</table>
<button type='submit'>변경</button>
 <a href='delete?classroomNo=${classroom.classroomNo}'>삭제</a>
<input type='hidden' name='classroomNo' value='${classroom.classroomNo}'>
 <a href='list'>목록</a>
</form>

<jsp:include page="../footer"></jsp:include>

</body>
</html>
    