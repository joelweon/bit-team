package bitcamp.java89.ems2.control.projectMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ProjectMemberDao;
import bitcamp.java89.ems2.domain.ProjectMember;

@Controller("/projectMember/detail.do")
public class ProjectMemberDetailContol implements PageController {
  
  @Autowired ProjectMemberDao projectMemberDao;

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int projectNo = Integer.parseInt(request.getParameter("projectNo"));
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));

    ProjectMember projectMember = projectMemberDao.getOne(projectNo, memberNo);
    
    if (projectMember == null) {
      throw new Exception("해당 프로젝트의 멤버가 없습니다.");
    }

    request.setAttribute("projectMember", projectMember);
    request.setAttribute("title", "프로젝트멤버관리-상세정보");
    request.setAttribute("contentPage", "/projectMember/detail.jsp");
    
    return "/main.jsp";
  }
  
}
