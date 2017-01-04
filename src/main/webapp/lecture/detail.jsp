<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>강의관리-상세정보</title>
</head>
<body>

<jsp:include page="../header"></jsp:include>

<h1>강의 정보</h1>
<form action='update' method='POST' enctype='multipart/form-data'>
<table border='1'>
<tr><th>강의제목</th><td><input name='title' type='text' value='${lecture.title}'></td></tr>
<tr><th>강의설명</th><td><input name='description' type='text' value='${lecture.description}'></td></tr>
<tr><th>시작일</th><td><input name='startDate' type='text' value='${lecture.startDate}'></td></tr>
<tr><th>종료일</th><td><input name='endDate' type='text' value='${lecture.endDate}'></td></tr>
<tr><th>총인원</th><td><input name='totalStudentNumber' type='text' value='${lecture.totalStudentNumber}'></td></tr>
<tr><th>수업료</th><td><input name='price' type='text' value='${lecture.price}'></td></tr>
<tr><th>총시간</th><td><input name='totalTime' type='text' value='${lecture.totalTime}'></td></tr>

<tr><th>강사1</th><td>
<select name="teacherNo1">
  <option value="${null}">없음</option>
  <c:forEach var="teacher" items="${teacherList}">
    <option value="${teacher.memberNo}" <c:if test="${lecture.teacherNoList[0]==teacher.memberNo}">selected</c:if>>${teacher.name}</option>
  </c:forEach>
</select>
</td></tr>

<tr><th>강사2</th><td>
<select name="teacherNo2">
  <option value="${null}">없음</option>
  <c:forEach var="teacher" items="${teacherList}">
    <option value="${teacher.memberNo}" <c:if test="${lecture.teacherNoList[1]==teacher.memberNo}">selected</c:if>>${teacher.name}</option>
  </c:forEach>
</select>
</td></tr>

<tr><th>강사3</th><td>
<select name="teacherNo3">
  <option value="${null}">없음</option>
  <c:forEach var="teacher" items="${teacherList}">
    <option value="${teacher.memberNo}" <c:if test="${lecture.teacherNoList[2]==teacher.memberNo}">selected</c:if>>${teacher.name}</option>
  </c:forEach>
</select>
</td></tr>

<tr><th>매니저</th><td>
<select name="managerNo">
  <option value="${null}">없음</option>
  <c:forEach var="manager" items="${managerList}">
    <option value="${manager.memberNo}" <c:if test="${lecture.managerNo==manager.memberNo}">selected</c:if>>${manager.name}</option>
  </c:forEach>
</select>
</td></tr>
<tr><th>강의실</th><td>
<select name="classroomNo">
  <option value="${null}">없음</option>
  <c:forEach var="classroom" items="${classroomList}">
    <option value="${classroom.classroomNo}" <c:if test="${lecture.classroomNo==classroom.classroomNo}">selected</c:if>>${classroom.name}</option>
  </c:forEach>
</select>
</td></tr>
</table>
<button type='submit'>변경</button>
 <a href='delete?lectureNo=${lecture.lectureNo}'>삭제</a>
<input type='hidden' name='lectureNo' value='${lecture.lectureNo}'>
 <a href='list'>목록</a>
</form>

<jsp:include page="../footer"></jsp:include>

</body>
</html>
