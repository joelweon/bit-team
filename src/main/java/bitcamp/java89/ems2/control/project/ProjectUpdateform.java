package bitcamp.java89.ems2.control.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ProjectDao;
import bitcamp.java89.ems2.domain.Project;

@Controller("/project/updateform.do")
public class ProjectUpdateform implements PageController {
  @Autowired ProjectDao projectDao;

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int projectNo = Integer.parseInt(request.getParameter("projectNo"));
    String projectMembers = request.getParameter("projectMember");
    
    Project project = projectDao.getOne(projectNo);
    
    request.setAttribute("title", "프로젝트 업데이트");
    request.setAttribute("projectMembers", projectMembers);
    request.setAttribute("project", project);
    request.setAttribute("contentPage", "/project/update.jsp");
    return "/main.jsp";
  }
}
