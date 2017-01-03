package bitcamp.java89.ems2.servlet.projmemb;

import java.io.IOException;
import java.io.PrintWriter;
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
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>프로젝트멤버-상세정보</title>");
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

      out.println("<div style='color:gray; font-weight:bold;"
          + " padding-top:5px; font-family:cursive,sans-serif;"
          + " font-size:x-large;'>"
          + "<marquee direction='lift'>프로젝트 멤버 상세보기</marquee></div>");
      out.println("<form action='update' method='POST'>");

      ProjectMemberDao projectMemberDao = (ProjectMemberDao)ContextLoaderListener.applicationContext.getBean("projectMemberDao");
      ProjectMember projectMember = projectMemberDao.getOne(projectNo, memberNo);
      if (projectMember == null) {
        throw new Exception("해당 프로젝트의 멤버가 없습니다.");
      }


      out.println("<table border='5' cellspacing='0' cellpadding='3' bordercolor='#999999'>");
      out.printf("<tr><th>프로젝트번호</th><td>"
          + "<input name='projectNo' type='number' value='%d' readonly></td></tr>\n", 
          projectNo);

        out.println("<tr><th>프로젝트명</th><td>");
        out.printf( "<input name='titl' value='%s' readonly></td></tr>\n", projectMember.getTitle());
      
      out.printf("<tr><th>이름</th><td>"
          + "<input name='name' type='text' value='%s' readonly></td></tr>\n", 
          projectMember.getName());

      out.println("<tr><th>역할</th><td>");
      out.println("<select name='rol'>"); 
      out.println("<option>팀장</option>");
      out.println("<option>팀원</option>/td></tr>\n"); 
         

      out.println("</table>");

      out.println("<button type='submit'>변경</button>");
      out.printf(" <a href='delete?memberNo=%s&projectNo=%s'>삭제</a>\n", memberNo, projectNo);
      out.printf("<input type='hidden' name='memberNo' value='%d'>\n", memberNo);

      out.println(" <a href='list'>목록</a>");
      out.println("</form>");

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
