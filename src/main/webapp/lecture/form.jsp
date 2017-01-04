<%@page import="bitcamp.java89.ems2.dao.TeacherDao"%>
<%@page import="bitcamp.java89.ems2.dao.ManagerDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bitcamp.java89.ems2.listener.ContextLoaderListener"%>
<%@page import="bitcamp.java89.ems2.dao.ClassroomDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>강의관리-입력폼</title>
</head>
<body>

<jsp:include page="../header"></jsp:include>

<%
ClassroomDao classroomDao = (ClassroomDao)ContextLoaderListener.applicationContext.getBean("classroomDao");
request.setAttribute("classroomList", classroomDao.getList());
ManagerDao managerDao = (ManagerDao)ContextLoaderListener.applicationContext.getBean("managerDao");
request.setAttribute("managerList", managerDao.getList());
TeacherDao teacherDao = (TeacherDao)ContextLoaderListener.applicationContext.getBean("teacherDao");
request.setAttribute("teacherList", teacherDao.getList());
%>

<h1>강의 정보</h1>
<form action='add' method='POST' enctype="multipart/form-data">
<table border='1'>
<tr><th>강의제목</th><td><input name='title' type='text' placeholder='자바기초'></td></tr>
<tr><th>강의설명</th><td><input name='description' type='text' placeholder='자바기초배우기'></td></tr>
<tr><th>시작일</th><td><input name='startDate' type='text' placeholder='2017-01-01'></td></tr>
<tr><th>종료일</th><td><input name='endDate' type='text' placeholder='2017-01-31'></td></tr>
<tr><th>총인원</th><td><input name='totalStudentNumber' type='number' placeholder='30'></td></tr>
<tr><th>수업료</th><td><input name='price' type='number' placeholder='500000'></td></tr>
<tr><th>총시간</th><td><input name='totalTime' type='number' placeholder='120'></td></tr>

<tr><th>강사1</th><td>
<select name="teacherNo1">
  <option value="${null}">없음</option>
  <c:forEach var="teacher" items="${teacherList}">
    <option value="${teacher.memberNo}">${teacher.name}</option>
  </c:forEach>
</select>
</td></tr>

<tr><th>강사2</th><td>
<select name="teacherNo2">
  <option value="${null}">없음</option>
  <c:forEach var="teacher" items="${teacherList}">
    <option value="${teacher.memberNo}">${teacher.name}</option>
  </c:forEach>
</select>
</td></tr>

<tr><th>강사3</th><td>
<select name="teacherNo3">
  <option value="${null}">없음</option>
  <c:forEach var="teacher" items="${teacherList}">
    <option value="${teacher.memberNo}">${teacher.name}</option>
  </c:forEach>
</select>


</td></tr>
<tr><th>매니저</th><td>
<select name="managerNo">
  <option value="${null}">없음</option>
  <c:forEach var="manager" items="${managerList}">
    <option value="${manager.memberNo}" >${manager.name}</option>
  </c:forEach>
</select>
</td></tr>
<tr><th>강의실</th><td>
<select name="classroomNo">
  <option value="${null}">없음</option>
  <c:forEach var="classroom" items="${classroomList}">
    <option value="${classroom.classroomNo}" >${classroom.name}</option>
  </c:forEach>
</select>
</td></tr>
</table>
<button type='submit'>등록</button>
 <a href='list'>목록</a>
</form>

<jsp:include page="../footer"></jsp:include>

</body>
</html>
