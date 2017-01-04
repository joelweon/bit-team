<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>할일관리-상세정보</title>
</head>
<body>

<jsp:include page="../header"></jsp:include>

<h1>할일 정보</h1>
<form action='update' method='POST'>

<table border='1'>
<tr><th>콘텐츠번호</th><td>
<input name='contentNo' type='number' value='${todo.contentNo}' readOnly></td></tr>
<tr><th>회원번호</th><td>
<input name='memberNo' type='number' value='${todo.memberNo}' readOnly></td></tr>
<tr><th>이름</th><td style ='width: 400px'><input name='name' type='text' value='${todo.name}' readOnly></td></tr>
<tr><th><프로젝트명></th><td style ='width: 400px'><input name='titl' type='text' value='${todo.title}' readOnly></td></tr>
<tr><th>순서</th><td colspan='5'><input name='sequence' type='number' value='${todo.sequence}'></td></tr>
<tr><th>할일내용</th><td colspan='5'>
<textarea name='conts' rows='15' cols='80'>${todo.tdContents}</textarea></td></tr>
<tr><th>등록일</th><td><input name='rdt' type='datetime' value='${todo.registerDate}'></td></tr>
<tr><th>상태</th><td colspan='5'>
<select name='state'>
<option value='검토중' <c:if test="${todo.state eq '검토중'}">selected</c:if>>검토중</option>
<option value='계획중' <c:if test="${todo.state eq '계획중'}">selected</c:if>>계획중</option>
<option value='진행중' <c:if test="${todo.state eq '진행중'}">selected</c:if>>진행중</option>
<option value='진행완료' <c:if test="${todo.state eq '진행완료'}">selected</c:if>>진행완료</option>
</select> </td>
<tr><th>조회수</th><td>
<input name='vw_cnt' type='text' value='${todo.viewCount}'></td></tr>
</table>

<button type='submit'>변경</button>
<a href='delete?contentNo=${todo.contentNo}'>삭제</a>
<input type='hidden' name='contentNo' value='${todo.contentNo}'>
<a href='list'>목록</a>
</form>

<jsp:include page="../footer"></jsp:include>

</body>
</html>
    