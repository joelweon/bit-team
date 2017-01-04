<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<meta http-equiv='Refresh' content='10;url=list'>
<title>피드관리-목록</title>
</head>
<body>

<jsp:include page="../header"></jsp:include>

<c:if test="${member == null}">
<a href='../auth/login'>글쓰기</a><br></c:if>
<c:if test="${member != null}">
<a href='form.jsp'>글쓰기</a><br></c:if>
<table border='1'>
<tr>
  <th>컨텐츠번호</th>
  <th>이름</th>
  <th>등록일</th>
  <th>조회수</th>
  <th>내용</th>
</tr>
<c:forEach var="feed" items="${feeds}">
<tr> 
  <td><a href='detail?ContentNo=${feed.contentNo}'>${feed.contentNo}</a></td>
  <td>${feed.name}</td>
  <td>${feed.registerDate}</td>
  <td>${feed.viewCount}</td>
  <td>${feed.contents}</td>
</tr>
</c:forEach>
</table>

<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
