package bitcamp.java89.ems2.control.project;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ProjectDao;
import bitcamp.java89.ems2.domain.Project;

@Controller("/project/list.do")
public class ProjectListControl implements PageController {
  @Autowired ProjectDao projectDao;
  
  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ArrayList<Project> list = projectDao.getList();
    request.setAttribute("projects", list);
    request.setAttribute("title", "프로젝트관리-목록");
    request.setAttribute("contentPage", "/project/list.jsp");
    return "/main.jsp";
    
  }
}
