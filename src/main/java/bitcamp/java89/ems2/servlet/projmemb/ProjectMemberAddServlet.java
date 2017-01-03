package bitcamp.java89.ems2.servlet.projmemb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.ProjectMemberDao;
import bitcamp.java89.ems2.domain.ProjectMember;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/projectMember/add")
public class ProjectMemberAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      ProjectMember projectMember = new ProjectMember();
      projectMember.setRol(request.getParameter("role"));
      projectMember.setProjectNo(Integer.parseInt(request.getParameter("projectNo")));
      projectMember.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
  
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("<title>프로젝트멤버 관리-등록</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<style>");
      out.println("body{");
      out.println("background: url('../image/back.png') no-repeat center center fixed;");
      out.println("background-size:100% 100%;");
      out.println("}");
      out.println("</style>");
      
      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>등록 결과</h1>");
    
      ProjectMemberDao projectMemberDao = (ProjectMemberDao)ContextLoaderListener.applicationContext.getBean("projectMemberDao");
      projectMemberDao.insert(projectMember);
      out.println("<p>등록하였습니다.</p>");
      
      // FooterServlet에게 꼬리말 HTML 생성을 요청한다.
      rd = request.getRequestDispatcher("/footer");
      rd.include(request, response);
      
      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
  }
}
