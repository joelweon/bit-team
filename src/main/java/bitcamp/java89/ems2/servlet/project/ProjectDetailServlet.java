package bitcamp.java89.ems2.servlet.project;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.ProjectDao;
import bitcamp.java89.ems2.domain.Project;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/project/detail")
public class ProjectDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    try {
      
      int projectNo = Integer.parseInt(request.getParameter("projectNo"));
      String projectMembers = "";
      
      response.setContentType("text/html;charset=UTF-8");
  
      ProjectDao projectDao = (ProjectDao)ContextLoaderListener.applicationContext.getBean("projectDao");
      
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
      
      RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
      request.setAttribute("project", project);
      rd.include(request, response);
      
    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
    
  }
}