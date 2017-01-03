package bitcamp.java89.ems2.servlet.lectureappy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.impl.LectureAppyMysqlDao;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/lectureappy/delete")
public class LectureAppyDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    try {
      int lectApplyNo = Integer.parseInt(request.getParameter("lectApplyNo"));
      int memberNo = Integer.parseInt(request.getParameter("memberNo"));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.printf("<meta http-equiv='Refresh' content='1;url=./reqlist?memberNo=%d'>\n", memberNo);
      out.println("<title>수강신청관리-삭제</title>");
      out.println("</head>");
      out.println("<body>");
      
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/header"); 
      requestDispatcher.include(request, response);
      
      out.println("<h1>삭제 결과</h1>");

      LectureAppyMysqlDao lectureAppyMysqlDao = (LectureAppyMysqlDao)ContextLoaderListener.applicationContext.getBean("lectureAppyDao"); 

//      if (!lectureMysqlDao.exist(memberNo)) {
//        throw new Exception("사용자를 찾지 못했습니다.");
//      }

      lectureAppyMysqlDao.delete(lectApplyNo);

      /*
      MemberDao memberDao = (MemberDao)this.getServletContext().getAttribute("memberDao");
      TeacherDao teacherDao = (TeacherDao)this.getServletContext().getAttribute("teacherDao");
      ManagerDao managerDao = (ManagerDao)this.getServletContext().getAttribute("managerDao");

      if (!managerDao.exist(memberNo) && !teacherDao.exist(memberNo))
        memberDao.delete(memberNo); 
*/
      out.println("<p>삭제하였습니다.</p>");   
      
      requestDispatcher = request.getRequestDispatcher("/footer"); 
      requestDispatcher.include(request, response);
      
      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      request.setAttribute("error", e);
      
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error"); 
      requestDispatcher.forward(request, response);
      return; 
    }
  }
}