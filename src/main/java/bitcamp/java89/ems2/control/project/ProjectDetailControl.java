package bitcamp.java89.ems2.control.project;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ProjectDao;
import bitcamp.java89.ems2.domain.Project;

@Controller("/project/detail.do")
public class ProjectDetailControl implements PageController {
  
  @Autowired ProjectDao projectDao;

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int projectNo = Integer.parseInt(request.getParameter("projectNo"));
    String projectMembers = "";
    
    Project project = projectDao.getOne(projectNo);
    
    if (project == null) {
      throw new Exception("해당 프로젝트가 없습니다.");
    }
    
    ArrayList<String> members = project.getProjectMemberList();
    if (members.size() != 0) {
      projectMembers += members.get(0);
      for (int i = 1; i < members.size(); i++) {
        projectMembers += ", " + members.get(i);
      }
      request.setAttribute("projectMembers", projectMembers);
    }
    
    request.setAttribute("project", project);
    
    return "detail.jsp";
  }
}