<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     

<form action='update.do' method='POST'>
<table border='5' cellspacing='0' cellpadding='3' bordercolor='#999999'>
<tr><th>프로젝트번호</th><td><input name='projectNo' type='number' value='${projectMember.projectNo}' readonly></td></tr>
<tr><th>프로젝트명</th><td><input name='titl' value='${projectMember.title}' readonly></td></tr>
<tr><th>이름</th><td><input name='name' type='text' value='${projectMember.name}' readonly></td></tr>
<tr><th>역할</th><td>
  <select name='rol'>
    <option value='팀장' <c:if test="${projectMember.rol=='팀장'}">selected</c:if>>팀장</option>
    <option value='팀원' <c:if test="${projectMember.rol=='팀원'}">selected</c:if>>팀원</option>
  </select>
</table>
<button type='submit'>변경</button>
 <a href='delete.do?memberNo=${projectMember.memberNo}&projectNo=${projectMember.projectNo}'>삭제</a>
<input type='hidden' name='memberNo' value='${projectMember.memberNo}'>
 <a href='list.do'>목록</a>
</form>
