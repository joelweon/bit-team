package bitcamp.java89.ems2.servlet.projmemb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.ProjectMemberDao;
import bitcamp.java89.ems2.domain.ProjectMember;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/projectMember/detail")
public class ProjectMemberDetailServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      int projectNo = Integer.parseInt(request.getParameter("projectNo"));
      int memberNo = Integer.parseInt(request.getParameter("memberNo"));

      response.setContentType("text/html;charset=UTF-8");

      ProjectMemberDao projectMemberDao = (ProjectMemberDao)ContextLoaderListener.applicationContext.getBean("projectMemberDao");
      ProjectMember projectMember = projectMemberDao.getOne(projectNo, memberNo);
      
      if (projectMember == null) {
        throw new Exception("해당 프로젝트의 멤버가 없습니다.");
      }

      RequestDispatcher rd = request.getRequestDispatcher("/projectmember/detail.jsp");
      request.setAttribute("projectMembers", projectMember);
      rd.include(request, response);


    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
  }
}
