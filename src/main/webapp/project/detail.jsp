<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>프로젝트관리-상세정보</title>
</head>
<body>

<!-- 머리말 -->
<jsp:include page="../header"></jsp:include>

<div style='text-align:right;'>
<a href='../lectureappy/list?memberNo=${project.memberNo}'>강의조회</a>  <a href='../lectureappy/reqlist?memberNo=${project.memberNo}'>신청목록</a></div>
<div id='container' style='width: 800px'>
<form action='update' method='GET'  enctype='multipart/form-data'>
  <div style='height: 70px'></div>
  <div id='title' style='height: 80px;'>
    <div style='float: left; margin-left: 10px;'>
      <img src='../upload/${project.logoPath}' style='height: 80px;'>
    </div>
    <div style='float: right';height: 100px;>
      <span style='margin: 10px; margin-left: 20px; padding: 2px; font-size: 16px; vertical-align: middle; border-radius: 5px; font-weight: bold;'>
        등록일 [ ${project.registerDate} ]</span> <br>
      <span style='margin: 10px; margin-left: 20px; padding: 2px; font-size: 16px; vertical-align: middle; border-radius: 5px; font-weight: bold;'>
        조회수 ${project.viewCount}</span>
    </div>
    <div style='float: left';height: 80px;>
      <span style='margin: 10px; margin-left: 20px; padding: 2px; font-size: 36px; vertical-align: middle; border-radius: 5px; font-weight: bold;'>
        ${project.title}</span>
    </div>
  </div>
  <div id='predata' style='margin-top: 30px;height: 110px; clear:both;'>
    <div id='startdate' style='margin-left: 10px; height: 30px;'>
      <span style='display: table-col; vertical-align: middle; font-size: 18px; font-weight: bold; margin: 10px;'>시작일</span>
      <span style='display: table-col; vertical-align: middle; font-size: 18px; font-weight: bold; margin: 10px;'>
      [ ${project.startDate} ]</span>
    </div>
    <div id='enddate' style='margin-left: 10px; height: 50px;'>
      <span style='display: table-col; vertical-align: middle; font-size: 18px; font-weight: bold; margin: 10px;'>종료일</span>
      <span style='display: table-col; vertical-align: middle; font-size: 18px; font-weight: bold; margin: 10px;'>
      [ ${project.endDate} ]</span>
    </div>
    <div id='writer' style='margin-left: 10px; height: 30px;'>
      <span style='display: table-col; vertical-align: middle; font-size: 18px; font-weight: bold; margin: 10px;'>
<!-- 프로젝트 멤버 변경 필요 -->
      프로젝트 멤버 [ ${projectMembers} ]</span>
    </div>
  </div>
  <div id='content' style='height: 320px;'>
    <div style='margin: 10px; background-color: #e0e0e0; height: 300px;'>
      <div style='width: 740px; font-size: 16px; padding: 20px;'>
        ${project.contents}
      </div>
    </div>
  </div>
  <div id='postdata' style='height: 80px;'>
    <div id='projectdate' style='float: left; margin: 10px; height: 60px; width: 320px;'>
      <span style='display: table-col; vertical-align: middle; font-size: 14px; font-weight: bold; margin: 10px;'>
<!-- 태그 변경 필요 -->
      #태그기능은 아직 지원하지 않습니다.</span>
    </div>
    <div id='projectdate' style='float: right; margin: 10px; height: 60px; width: 420px;'>
      <input type='button' value='삭제' onclick="location.href='delete?projectNo=${project.projectNo}'"
        style='height: 40px; width: 120px; border: 0px; background-color: #bebebe; font-size: 20px; font-weight: bold; color: white; margin-right: 20px; border-radius: 5px;'></input>
      <button type='submit' 
        style='height: 40px; width: 120px; border: 0px; background-color: #bebebe; font-size: 20px; font-weight: bold; color: white; margin-right: 20px; border-radius: 5px;'>수정</button>
      <input type='button' value='목록' onclick="location.href='list'"
        style='height: 40px; width: 120px; border: 0px; background-color: #bebebe; font-size: 20px; font-weight: bold; color: white; border-radius: 5px;'></input>
    </div>
  </div>
<input type='hidden' name='projectNo' value='${project.projectNo}'>
<input type='hidden' name='projectMember' value='${projectMembers}'>
</form>
<hr>
</div>

<!-- 꼬리말 -->
<jsp:include page="../footer"></jsp:include>

</body>
</html>
