package bitcamp.java89.ems2.servlet.lectureappy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.impl.LectureAppyMysqlDao;
import bitcamp.java89.ems2.domain.LectureApplication;
import bitcamp.java89.ems2.listener.ContextLoaderListener;


@WebServlet("/lectureappy/list")
public class LectureAppyListServlet extends HttpServlet {
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
      out.println("<title>강의관리-목록</title>");
      out.println("</head>");
      out.println("<body>");
      
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      //rd = request.getRequestDispatcher("/join");
      //rd.include(request, response);
      
      out.println("<h1>강의 정보</h1>");

      LectureAppyMysqlDao lectureAppyMysqlDao = (LectureAppyMysqlDao)ContextLoaderListener.applicationContext.getBean("lectureAppyDao");
      ArrayList<LectureApplication> list = lectureAppyMysqlDao.getList();

      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("  <th>제목</th>");
      out.println("  <th>설명</th>");
      out.println("  <th>수업료</th>");
      out.println("</tr>");

      for (LectureApplication lectureApplication : list) {
        out.println("<tr> ");
        out.printf("<td><a href='detail?memberNo=%d&lectureNo=%d'>%s</a></td>"
            + "<td>%s</td>"
            + "<td>%d</td>\n",
          Integer.parseInt(request.getParameter("memberNo")),
          lectureApplication.getLectureNumber(),
          lectureApplication.getTitl(),
          lectureApplication.getExplanation(),
          lectureApplication.getPrice());
        out.println("</tr>");
      }

      out.println("</table>");
      out.println("<a href='../student/list'>목록</a>\n");
      
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
