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

import bitcamp.java89.ems2.dao.ProjectDao;
import bitcamp.java89.ems2.dao.ProjectMemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.domain.Project;
import bitcamp.java89.ems2.domain.ProjectMember;
import bitcamp.java89.ems2.domain.Student;
import bitcamp.java89.ems2.listener.ContextLoaderListener;


@WebServlet("/projectMember/addForm")
public class ProjectMemberAddFormServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      ProjectMember projectMember = new ProjectMember();
      projectMember.setRol(request.getParameter("rol"));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>프로젝트 멤버 등록</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<style>");
      out.println("body{");
      out.println("background: url('../image/back.png') no-repeat center center fixed;");
      out.println("background-size:100% 100%;");
      out.println("}");
      out.println("</style>");
      ProjectMemberDao projectMemberDao = (ProjectMemberDao)ContextLoaderListener.applicationContext.getBean("projectMemberDao");
      
      StudentDao studentDao = (StudentDao)ContextLoaderListener.applicationContext.getBean("studentDao");
      ProjectDao projectDao = (ProjectDao)ContextLoaderListener.applicationContext.getBean("projectDao");
      ArrayList<Student> studentList = studentDao.getList();
      ArrayList<Project> projectList = projectDao.getList();
      
     
      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h2>프로젝트 회원 역할 등록</h2>");
      out.println("<form action='add' method='POST'>");
      
      out.println("프로젝트명");
      out.println( "<select name='projectNo'>");
      for (Project project : projectList) {
      out.printf( "<option value=%s>%s</option>",project.getProjectNo(), project.getTitle()); }
      out.println( "</select>");
      
      out.println("역할");
      out.println("<select name ='role'>");
      out.println("<option>팀장</option>");
      out.println("<option>팀원</option>");
      out.println("</select>");
      
      out.println("회원명");
      out.println( "<select name='memberNo'>");
      for (Student student : studentList) {
      out.printf( "<option value=%s>%s</option>",student.getMemberNo(), student.getName()); }
      out.println( "</select>");

      out.println("<button type='submit'>등록</button>");
      out.println("<a href='list'>목록</a>");
      // FooterServlet에게 꼬리말 HTML 생성을 요청한다.
      rd = request.getRequestDispatcher("/footer");
      rd.include(request, response);
      out.println("</div>");
      out.println("</form>");
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
