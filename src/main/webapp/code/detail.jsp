<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>코드관리-상세정보</title>
</head>
<body>

<jsp:include page="../header"></jsp:include>

<h1>코드 정보</h1>
<form action='update' method='POST' enctype='multipart/form-data'>

<table border='1'>
<tr><th>콘텐츠번호</th><td><input name='contentNo' type='number' value='${code.contentNo}' readOnly></td></tr>
<tr><th>태그</th><td><input name='tagName' type='text' value='
<c:forEach var="tag" items="${tags}">
#${tag.tagName} 
</c:forEach>
'></td></tr>
<tr><th>코드내용</th><td><textarea cols='90' rows='10' name='conts'>${code.code}</textarea></td></tr>
<tr><th>프로그래밍언어</th><td>
<select name='pl'>
<option value='선택안함' <c:if test="${code.progLanguage eq '선택안함'}">selected</c:if>>선택안함</option>
<option value='c++' <c:if test="${code.progLanguage eq 'c++'}">selected</c:if>>c++</option>
<option value='java' <c:if test="${code.progLanguage eq 'java'}">selected</c:if>>java</option>
<option value='cobol' <c:if test="${code.progLanguage eq 'cobol'}">selected</c:if>>cobol</option>
<option value='c' <c:if test="${code.progLanguage eq 'c'}">selected</c:if>>c</option>
<option value='c#' <c:if test="${code.progLanguage eq 'c#'}">selected</c:if>>c#</option>
</select>
<tr><th>등록일</th><td><input name='resgisterDate' type='text' value='${code.registerDate}'></td></tr>
</table>



<button type='submit'>변경</button>
<a href='delete?contentNo=${code.contentNo}'>삭제</a>
<input type='hidden' name='contentNo' value='${code.contentNo}'>
<a href='list'>목록</a>
</form>

<jsp:include page="../footer"></jsp:include>

</body>
</html>
    