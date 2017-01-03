<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>프로젝트멤버-상세정보</title>
</head>
<body>

<jsp:include page="../header"></jsp:include>

<style>
body{
background: url('../image/back.png') no-repeat center center fixed;
background-size:100% 100%;
} 
</style>

<form action='update' method='POST'>
<table border='5' cellspacing='0' cellpadding='3' bordercolor='#999999'>
<tr><th>프로젝트번호</th><td><input name='projectNo' type='number' value='${projectMembers.projectNo}' readonly></td></tr>
<tr><th>프로젝트명</th><td><input name='titl' value='${projectMembers.title}' readonly></td></tr>
<tr><th>이름</th><td><input name='name' type='text' value='${projectMembers.name}' readonly></td></tr>
<tr><th>역할</th><td>
  <select name='rol'>
    <option value='팀장' <c:if test="${projectMembers.rol=='팀장'}">selected</c:if>>팀장</option>
    <option value='팀원' <c:if test="${projectMembers.rol=='팀원'}">selected</c:if>>팀원</option></td></tr>
</table>
<button type='submit'>변경</button>
 <a href='delete?memberNo=${projectMembers.memberNo}'>삭제</a>
<input type='hidden' name='memberNo' value='${projectMembers.memberNo}'>
 <a href='list'>목록</a>
</form>

<jsp:include page="../footer"></jsp:include>

</body>
</html>
    