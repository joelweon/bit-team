<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>할일관리-목록</title>
</head>
<body>
<div id='header' style='background-color:gray; height:40px;position:relative;'>
<div style='width:300px; height:38px; position:absolute; left:0px; top:0px;'>
<img src='../image/logo.jpeg' height='30' style='float:left; margin-top:6px; margin-left:6px;'>
<div style='color:white; font-weight:bold; margin-left:60px; padding-top:7px; font-family:돋움체,sans-serif; font-size:x-large;'>교육센터관리시스템</div>
</div>
<div style='height:30px; margin: 5px; float:right;'>
<a href='../auth/login'>로그인</a>
</div>
</div>
<h1>할일 정보</h1>
<a href='form.html'>추가</a><br>
<table border='1'>
<tr>
  <th>번호</th>
  <th>순서</th>
  <th>이름</th>
  <th>프로젝트명</th>
  <th>상태</th>
  <th>등록일</th>
</tr>
<c:forEach var="todo" items="${todoes}">
<tr> 
  <td><a href='detail?tdno=${todo.todoNo}'>${todo.todoNo}</a></td>
  <td>${todo.sequence}</td>
  <td>${todo.name}</td> 
  <td>${todo.title}</td>
  <td>${todo.state}</td>
  <td>${todo.registerDate}</td>
</tr>
</c:forEach>
</table>
<div id='footer' style='background-color:gray; height:20px; margin-top:20px;'>
<div style='color:white; padding-top:2px; font-family:돋움체,sans-serif; text-align:center; font-weight:lighter;'>
@2016 비트캠프 자바89기
</div>
</div>
</body>
</html>
