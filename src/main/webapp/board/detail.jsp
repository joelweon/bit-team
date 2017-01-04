<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시글</title>
</head>
<body>

<jsp:include page="../header"></jsp:include>

<form>
<div style='margin:auto;'>
<div style='border:1px; padding-top:50px; padding-bottom:10px; text-align:center; margin:auto;'>
<h2 style='border-bottom:3px solid gray; width:500px; margin:auto;'>${board.title}</h2>
</div>
<div style='width:1000px; margin:auto;'>
<table border='1' style='margin:auto;'>
<tr><th><input name='contentsNo' type='text' value='${board.contentNo}' readOnly style='border:none; text-align:center;'></th><th><input name='name' type='text' value=학생1 readOnly style='border:none; text-align:center;'></th><th><input type='text' value='                         ' readOnly style='border:none; text-align:center;'><th><input name='registDate' value=2017-01-03 17:30:55.0 readOnly style='border:none; text-align:center;'></th><th><input name='viewContents' value='조회수 |              1' readOnly style='border:none;'></th></tr><tr><td colspan='5'>
<br><textarea name='contents' rows='30' cols='125' readOnly style='text-indent:20px; border:none;'>${board.contents}</textarea><br></td></tr>
</table>
</form>
<form action='detail' method='GET'>
<input type='hidden' name='contentsNo' value='${board.contentNo}'>
<div style='width:300px; height:60px; text-align:center; padding-top:10px;'>
<button type='submit'>수정</button> 

<a href='delete?contentsNo=${board.contentNo}'>삭제</a>
<a href='list'>목록</a>
</div>
</div>
</form>

<jsp:include page="../footer"></jsp:include>

</body>
</html>
