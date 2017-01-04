<%@ page language="java"
    contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>자료실-목록</title>
</head>   
<body>
<jsp:include page="../header"/>
<h1>자료실</h1>
<c:choose>
  <c:when test="${not empty member}">
	<a href='form.html'>추가</a><br>
  </c:when>
  <c:otherwise>
	<p>자료추가를 원하신다면 로그인을 해주세요.</p>
  </c:otherwise>
</c:choose>
<table border='1'>  
<tr>
  <th>컨텐츠일련번호</th>
  <th>파일경로</th> 
  <th>작성회원번호</th>
  <th>등록일</th>
</tr>
<c:forEach var='download' items='${downloads}'>
<tr> 
  <td>${download.contentNo}</td>
  <td><a href='detail?contentNo=${download.contentNo}'>${download.path}</a></td>
  <td>${download.memberNo}</td>
  <td>${download.registerDate}</td>
  </tr>
  </c:forEach>
  </table>
    
  <jsp:include page="../footer"/>
</body>
</html>
