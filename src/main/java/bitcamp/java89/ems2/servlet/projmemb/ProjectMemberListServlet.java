package bitcamp.java89.ems2.servlet.projmemb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.ProjectMemberDao;
import bitcamp.java89.ems2.domain.ProjectMember;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/projectMember/list")
public class ProjectMemberListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
      try {
        ProjectMemberDao projectMemberDao =
            (ProjectMemberDao)ContextLoaderListener.applicationContext.getBean("projectMemberDao");
        ArrayList<ProjectMember> list = projectMemberDao.getList();

        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher("/projectmember/list.jsp");
        request.setAttribute("projectMembers", list);
        rd.include(request, response);

      } catch (Exception e) {
        request.setAttribute("error", e);
        RequestDispatcher rd = request.getRequestDispatcher("/error");
        rd.forward(request, response);
        return;
      }
    }
  }
