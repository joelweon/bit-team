<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>코드관리-목록</title>
</head>
<body>
	<div id='header'
		style='background-color: gray; height: 40px; position: relative;'>
		<div
			style='width: 300px; height: 38px; position: absolute; left: 0px; top: 0px;'>
			<img src='../image/logo.jpeg' height='30'
				style='float: left; margin-top: 6px; margin-left: 6px;'>
			<div
				style='color: white; font-weight: bold; margin-left: 60px; padding-top: 7px; font-family: 돋움체, sans-serif; font-size: x-large;'>교육센터관리시스템</div>
		</div>
		<div style='height: 30px; margin: 5px; float: right;'>
			<img src='../upload/null' height='30' style='vertical-align: middle;'>
			<span>학생1</span> <a href='../auth/logout'>로그아웃</a>
		</div>
	</div>
	<div style='text-align: right;'>
		<a href='../lectureappy/list?memberNo=1'>강의조회</a> <a
			href='../lectureappy/reqlist?memberNo=1'>신청목록</a>
	</div>
	<h1>코드 정보</h1>
	<form action='tag' method='GET'>
		<div class='tag'>
			<input type='text' name='tagname' class='tagname'
				placeholder='태그를 입력하세요' />
			<button type='submit' class='button_tag'>검색</button>
	</form>
	<a href='form.html'>추가</a>
	<br>
	<table border='1'>
		<tr>
			<th>콘텐츠번호</th>
			<th>등록일</th>
			<th>조회수</th>
			<th>프로그래밍언어</th>
			<th>태그</th>
		</tr>

		<c:forEach var="code" items="${codes}">
			<tr>
				<td><a href='detail?contentNo=${code.contentNo}'>${code.contentNo}</a></td>
				<td>${code.registerDate}</td>
				<td>${code.viewCount}</td>
				<td>${code.progLanguage}</td>
				<td>
					<c:forEach var="tag" items="${code.tagList}">${tag.tagName}</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div id='footer'
		style='background-color: gray; height: 20px; margin-top: 20px;'>
		<div
			style='color: white; padding-top: 2px; font-family: 돋움체, sans-serif; text-align: center; font-weight: lighter;'>
			@2016 비트캠프 자바89기</div>
	</div>
</body>
</html>
