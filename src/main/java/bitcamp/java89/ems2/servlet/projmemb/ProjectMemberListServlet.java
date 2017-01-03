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

@WebServlet("/projectMember/list")
public class ProjectMemberListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
      try {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>프로젝트멤버관리-목록</title>");
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
            + "<marquee direction='lift'>프로젝트 멤버 관리 페이지 입니다.</marquee></div>");

        ProjectMemberDao projectMemberDao = (ProjectMemberDao)ContextLoaderListener.applicationContext.getBean("projectMemberDao");
        ArrayList<ProjectMember> list = projectMemberDao.getList();

        
        out.println("<table border='5' cellspacing='0' cellpadding='3' bordercolor='#999999'>");
        out.println("<tr table bgcolor='#cccccc' cellspacing='1'>");
        out.println("  <th>프로젝트번호</th>");
        out.println("  <th>프로젝트명</th>");
        out.println("  <th>이름</th>");
        out.println("  <th>역할</th>");
        out.println("</tr>");
        
        for (ProjectMember projectMember : list) {
          out.println("<tr> ");
          out.printf("  <td>%d</td>"
              + "<td><a href='detail?projectNo=%1$d&memberNo=%d'>%s</a></td>"
              + "<td>%s</td>"
              + "<td>%s</td>\n",
            projectMember.getProjectNo(),
            projectMember.getMemberNo(),
            projectMember.getTitle(),
            projectMember.getName(),
            projectMember.getRol());
          out.println("</tr>");
        }
        
        out.println("</table>");
        out.println("<a href='addForm'>추가</a><br>");
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
