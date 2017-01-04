<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>강의관리-목록</title>
</head>
<body>

<jsp:include page="../header"></jsp:include>

<h1>학생 정보</h1>
<a href='form.html'>추가</a><br>
<table border='1'>
<tr>
  <th>강의실 번호</th>
  <th>강의실 이름</th>
</tr>

<c:forEach var="classroom" items="${classrooms}">
<tr> 
  <td>${classroom.classroomNo}</td>
  <td><a href='detail?classroomNo=${classroom.classroomNo}'>${classroom.name}</a></td>

</tr>
</c:forEach>
</table>

<jsp:include page="../footer"></jsp:include>

</body>
</html>
    