<%@ page language="java"
    contentType="text/html;
    charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>프로젝트멤버관리-목록</title>
</head>
<body>
<style>
body{
background: url('../image/back.png') no-repeat center center fixed;
background-size:100% 100%;
}
</style>

<jsp:include page="../header"></jsp:include>

<table border='5' cellspacing='0' cellpadding='3' bordercolor='#999999'>
<tr table bgcolor='#cccccc' cellspacing='1'>
  <th>프로젝트번호</th>
  <th>프로젝트명</th>
  <th>이름</th>
  <th>역할</th>
</tr>

<c:forEach var="projectMember" items="${projectMembers}">
<tr> 
  <td>${projectMember.projectNo}</td><td>
  <a href='detail?projectNo=${projectMember.projectNo}&memberNo=${projectMember.memberNo}'>${projectMember.title}</a></td>
  <td>projectMember.name</td>
  <td>projectMember.rol</td>
</tr>
</c:forEach>
</table>
<a href='addForm'>추가</a><br>

<jsp:include page="../footer"></jsp:include>

</body>
</html>
