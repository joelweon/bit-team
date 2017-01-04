package bitcamp.java89.ems2.control.project;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ProjectDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Project;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller("/project/add.do")
public class ProjectAddControl implements PageController {
  @Autowired ProjectDao projectDao;

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String,String> dataMap = MultipartUtil.parse(request);
    
    Project project = new Project();
    project.setTitle(dataMap.get("projectName"));
    project.setStartDate(dataMap.get("projectStartDate"));
    project.setEndDate(dataMap.get("projectEndDate"));
    project.setContents(dataMap.get("textContents"));
    project.setLogoPath(dataMap.get("logoPath"));
    
    // 해당 유저가 존재하는지 체크
    Member writer = (Member)request.getSession().getAttribute("member");
    if (writer == null) {
      throw new Exception("로그인이 필요합니다.");
    }
    
    // 작성자가 존재하면 해당 이메일의 멤버번호로 프로젝트 등록자를 설정 
    project.setMemberNo(writer.getMemberNo());
    projectDao.insertContent(project);
    projectDao.insert(project);
    
    return "redirect:list.do";
  }
}








