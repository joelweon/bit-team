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

<h1>강의 정보</h1>
<a href='form.jsp'>추가</a><br>
<table border='1'>
<tr>
  <th>강의번호</th>
  <th>강의제목</th>
  <th>설명</th>
  <th>시작일</th>
  <th>종료일</th>
  <th>총인원</th>
  <th>수업료</th>
  <th>총수업시간</th>
</tr>
<c:forEach var="lecture" items="${requestScope.lectures}">
<tr> 
  <td>${lecture.lectureNo}</td>
  <td><a href='detail?lectureNo=${lecture.lectureNo }'>${lecture.title}</a></td>
  <td>${lecture.description}</td>
  <td>${lecture.startDate}</td>
  <td>${lecture.endDate}</td>
  <td>${lecture.totalStudentNumber}</td>
  <td>${lecture.price}</td>
  <td>${lecture.totalTime}</td>
</tr>
</c:forEach>
</table>

<jsp:include page="../footer"></jsp:include>

</body>
</html>
