<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

    
    
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시판관리-목록</title>
</head>
<body>
<jsp:include page="../header"></jsp:include>
<h1>게시판 정보</h1>
<c:if test="${member == null}">
<a href='../auth/login'>글쓰기</a><br></c:if>
<c:if test="${member != null}">
<a class='btn btn-default' href='form.html' role='button'>글쓰기</a><br></c:if>
<table border='1' class='table table-striped'>
<tr>
  <th>번호</th>
  <th>제목</th>
  <th>작성자</th>
  <th>등록일</th>
  <th>조회수</th>
</tr>
<c:forEach var="board" items="${board}">
<tr> 
  <td>${board.contentNo}</td>
  <td><a href='detail?contentNo=${board.contentNo}'>${board.title}</a></td>
  <td>${board.name}</td>
  <td>${board.registerDate}</td>
  <td>${board.viewCount}</td>
</tr>
</c:forEach>
</table>
<jsp:include page="../footer"></jsp:include>
</body>
</html>
    