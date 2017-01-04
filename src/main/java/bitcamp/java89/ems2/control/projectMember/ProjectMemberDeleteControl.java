package bitcamp.java89.ems2.control.projectMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ProjectMemberDao;
import bitcamp.java89.ems2.domain.ProjectMember;

@Controller("/projectMember/delete.do")
public class ProjectMemberDeleteControl implements PageController {
  
  @Autowired ProjectMemberDao projectMemberDao;

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ProjectMember projectMember = new ProjectMember();
    projectMember.setProjectNo(Integer.parseInt(request.getParameter("projectNo")));
    projectMember.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
    
//    if (!projectMemberDao.exist(projectMemberDao)) {
//      throw new Exception("학생을 찾지 못했습니다.");
//    }
    
    projectMemberDao.delete(projectMember);
    
    return "redirect:list.do";
  }  
}
