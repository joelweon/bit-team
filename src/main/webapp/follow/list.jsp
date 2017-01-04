<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>팔로우관리-목록</title>
<link rel='stylesheet' type='text/css' href='http://aoicielv.woobi.co.kr/bit/follow.css'>
</head>
<body class='admin list'>

<jsp:include page="/header"></jsp:include>

<div class='con'>
<div class='conFollowing'>
<h4>팔로잉리스트 </h4>
<div class='board_list yp_list'>
<table border='0' cellspacing='0' cellpadding='0'>
<thead>
<tr>
<th scope='col' class='img'>이미지</th>
<th scope='col' class='name'>이름</th>
<th scope='col' class='email'>이메일</th>
<th scope='col' class='num_phone'>전화번호</th>
<th scope='col' class='btn'>버튼</th>
</tr>
</thead>
<tbody>
<c:if test="${loginEmail != null && followingList != null}">
<c:forEach var="follow" items="${followingList}">
<tr>
  <td class='img'><img src='../upload/${follow.followPhoto}' height='30'></td>
  <td class='name'>${follow.followName}</td>
  <td class='email'>${follow.followEmail}</td>
  <td class='num.phone'>${follow.followTel}</td>
  <td class='btn'><a href='delete?deleteEmail=${follow.followEmail}'>삭제</a></td>
</tr>
</c:forEach>
</c:if>
</tbody>
</table>
</div>
</div>
<div class='conFollower'>
<h4>팔로워리스트 </h4>
<div class='board_list yp_list'>
<table border='0' cellspacing='0' cellpadding='0'>

<thead>
<tr>
<th scope='col' class='img'>이미지</th>
<th scope='col' class='name'>이름</th>
<th scope='col' class='email'>이메일</th>
<th scope='col' class='num_phone'>전화번호</th>
</tr>
</thead>
<tbody>
<c:if test="${loginEmail != null && followerList != null}">
<c:forEach var="follow" items="${followerList}">
<tr>
<td class='img'><img src='../upload/${follow.followPhoto}' height='30'></td>
<td class='name'>${follow.followName}</td>
<td class='email'>${follow.followEmail}</td>
<td class='num_phone'>${follow.followTel}</td>
</tr>
</c:forEach>
</c:if>
</tbody>
</table>
</div>
</div>
<div class='conSearch'>
<h4>회원검색</h4>
<form action='find' method='POST'>
<div class='searchBox2'>

<p class='search'>
<input type='text' name='findEmail' class='input_style_search' maxlength='30' placeholder='이메일을 입력하세요' />
<button type='submit' class='btn_search'>검색</button>
</p>
</div>
</form>

<c:if test="${loginEmail != null && followingList != null}">
  <c:forEach var="follow" items="${followingList}">
    <c:if test="${follow.followEmail == findEmail}">
    <input type='hidden' name='duplicateEmail' value='${duplicateEmail = findEmail}'>
    </c:if>
  </c:forEach>
</c:if>

<c:choose>
  <c:when test="${memberSub == null}">
  <div class='empty'>로그인 후 검색하세요.</div>
  </c:when>
  
  <c:when test="${findEmail == null}">
    <div class='empty'>팔로잉 가능한 회원을 검색하세요.</div>
  </c:when>
  
  <c:when test="${duplicateEmail == findEmail}">
    <div class='empty'>이미 팔로잉된 회원입니다.</div>
  </c:when>
  
  <c:when test="${loginEmail != null && findEmail != null}">
    <c:if test="${loginEmail == findEmail}">
      <div class='empty'>본인의 이메일은 등록하실수 없습니다.</div>
    </c:if>
      <form action='add' method='POST'>
      <div class='board_list yp_list'>
      <table border='0' cellspacing='0' cellpadding='0'>
      <tbody>
      <tr>
      <td class='name'>${memberFind.name}</td>
      <td class='email'>${memberFind.email}</td>
      <td class='num_phone'>${memberFind.tel}</td>
      <c:if test="${loginEmail != findEmail}">
      <td class='btn'><button type='submit'>추가</button></td>
      </c:if>
    <input type='hidden' name='followSubject' value='${memberFind.memberNo}'>
    <input type='hidden' name='followObject' value='${memberSub.memberNo}'>
      
      </tr>
      </tbody>
      </table>
      </div>
      </form>
  </c:when>
</c:choose>
</div>
</div>

<jsp:include page="/footer"></jsp:include>
</body>
</html>