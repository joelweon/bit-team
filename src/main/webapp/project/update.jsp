<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>프로젝트관리-변경</title>
</head>
<body>

<jsp:include page="../header"/>

 <div id='container' style='width: 800px'>
  <form action='update' method='POST' enctype='multipart/form-data'>
    <div style='height: 70px'></div>
  <div style='float: left; margin-left: 10px;'>
    <img src='../upload/${project.logoPath}' style='height: 80px;'>
  </div>
  <div id='title' style='float: left; height: 80px;'>
    <input name='projectName' type='text' placeholder='프로젝트 이름' value='${project.title}'
      style='width:350px; margin: 10px; margin-left: 20px; padding: 2px; font-size: 36px; vertical-align: middle; border-radius: 5px;'>
  </div>
<div style='float: right; padding-top: 10px'>
  <input name='logoPath' type='file' 
  style='margin: 10px; height: 40px; width: 180px;'>
</div>
      <div id='predata' style='clear:both; height: 130px;'>
        <div id='projectdate'
          style='float: left; height: 100px; width: 600px;'>
          <div id='startdate' style='margin: 10px; height: 30px;'>
            <span
              style='display: table-col; vertical-align: middle; font-size: 18px; font-weight: bold; margin: 10px;'>시작일</span>
            <input name='projectStartDate' type='text'
              placeholder='2016-12-01' value='${project.startDate}' 
              style='margin: 10px; padding: 2px; font-size: 18px; vertical-align: middle; border-radius: 5px;'>
          </div>
          <div id='enddate' style='margin: 10px; height: 40px;'>
            <span
              style='display: table-col; vertical-align: middle; font-size: 18px; font-weight: bold; margin: 10px;'>종료일</span>
            <input name='projectEndDate' type='text' placeholder='2017-02-01' value='${project.endDate}' 
              style='margin: 10px; padding: 2px; font-size: 18px; vertical-align: middle; border-radius: 5px;'>
          </div>
        </div>
        <div id='teamselect'
          style='float: right; height: 100px; width: 200px'>
          <input type='button' value='팀원선택'
            onclick="location.href='../projectMember/list'"
            style='margin: 10px; height: 80px; width: 180px; border: 0px; background-color: #bebebe; font-size: 20px; font-weight: bold; color: white; border-radius: 5px;'>
        </div>
        <div id='projectMemberListDiv' style='margin: 10px; height: 30px;'>
          <span style='display: table-col; vertical-align: middle; font-size: 18px; font-weight: bold; margin: 10px;'>
프로젝트 멤버 [ ${projectMembers} ]</span>        </div>
      </div>
      <div id='content' style='height: 320px;'>
        <div style='margin: 10px; background-color: #e0e0e0; height: 300px;'>
          <textarea rows='17' cols='102' name='textContents'
            placeholder='프로젝트의 내용을 입력해 주세요'
            style='margin: 20px; resize: none; border-radius: 5px;'>
${project.contents}
</textarea>
        </div>
      </div>
      <div id='postdata' style='height: 80px;'>
        <div id='projectdate'
          style='float: left; margin: 10px; height: 60px; width: 480px;'>
          <span
            style='display: table-col; vertical-align: middle; font-size: 18px; font-weight: bold; margin: 10px;'>태그</span>
          <input name='tagData' type='text' placeholder='#자바, #웹, #스프링'
            style='margin: 10px; padding: 2px; font-size: 18px; vertical-align: middle; border-radius: 5px; width: 350px'>
        </div>
        <div id='projectdate'
          style='float: right; margin: 10px; height: 60px; width: 280px;'>
          <div style='float: left; margin: 10px; height: 40px; width: 120px;'>
            <button type='submit'
              style='height: 40px; width: 120px; border: 0px; background-color: #bebebe; font-size: 20px; font-weight: bold; color: white; border-radius: 5px;'>확인</button>
          </div>
          <div
            style='float: right; margin: 10px; height: 40px; width: 120px;'>
            <input type='button' value='취소' onclick="location.href='${referer}'"
              style='height: 40px; width: 120px; border: 0px; background-color: #bebebe; font-size: 20px; font-weight: bold; color: white; border-radius: 5px;'>
          </div>
        </div>
      </div>
    <input type='hidden' name='projectNo' value='${project.projectNo}'>
    </form>
    <hr>
  </div>

<jsp:include page="../footer"/>

</body>
</html>
