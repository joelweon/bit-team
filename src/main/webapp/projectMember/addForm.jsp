<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
body{
	background: url('../image/back.png') no-repeat center center fixed;
	background-size:100% 100%;
}
</style>
<h2>프로젝트 회원 역할 등록</h2>
<form action='add.do' method='POST'>
프로젝트명
<select name='projectNo'>
<c:forEach var='project' items='${requestScope.projectList}'>
  <option value='${project.projectNo}'}>${project.title}</option>
</c:forEach>
</select>
역할
<select name ='role'>
  <option value='팀장'>팀장</option>
  <option value='팀원'>팀원</option>
</select>
회원명
<select name='memberNo'>
<c:forEach var='student' items='${requestScope.studentList}'>
  <option value='${student.memberNo}'>${student.name}</option>
</c:forEach>
</select>
<button type='submit'>등록</button>
<a href='list.do'>목록</a>
</form>