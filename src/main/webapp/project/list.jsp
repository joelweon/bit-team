<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div id='container' style='width: 800px'>
<div style='margin-top: 30px;float: right;'>
<input type='button' value='프로젝트 생성' onclick="location.href='form.html'"
          style='height: 50px; width: 200px; border: 0px; background-color: #bebebe; font-size: 20px; font-weight: bold; color: white; border-radius: 5px;'></input>
</div>
<div style='height: 30px;clear:both;'>
</div>

<c:forEach var='project' items='${requestScope.projects}'>

<hr>
<div style='background-color:#F5F5F5; padding: 20px; width: 700px; margin: 30px'>
<div  style='width:60px; float:left;'>
<img src='../upload/${project.logoPath}' height='36px' style='background-color:blue; position:relative; margin-left:10px;'></div>
<div style='float:left; position:relative; padding-left:10px; font-size: 36px; font-weight: bold; width: 620px; height: 80px;'>
<a href='detail.do?projectNo=${project.projectNo}'>${project.title}</a>
</div>
<div style='float: right; font-size: 16px; font-weight: bold; width: 170px; height: 80px;'>
등록일 [<fmt:formatDate value="${request.registerDate}" pattern="yyyy-MM-dd"/>]<br>
글쓴이 [${project.name}]
    </div>
<div style='margin-left: 40px; font-size: 18px; font-weight: bold;'>
시작일 [${project.startDate}]<br>종료일 [${project.endDate}]    </div>
<div style='font-size: 14px; font-weight: bold; margin-top: 10px;'>
#태그 미지원
</div>
</div>
</c:forEach>

</div>
