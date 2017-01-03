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

@WebServlet("/lectureappy/reqlist")
public class LectureAppyRequestServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    try {
      int memberNo = Integer.parseInt(request.getParameter("memberNo"));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>수강신청-목록</title>");
      out.println("</head>");
      out.println("<body>");

      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/header"); 
      requestDispatcher.include(request, response);

      out.printf("<h1>%d번 학생의 수강신청 정보</h1>\n", memberNo);
      
      LectureAppyMysqlDao lectureAppyMysqlDao = (LectureAppyMysqlDao)ContextLoaderListener.applicationContext.getBean("lectureAppyDao");
      ArrayList<LectureApplication> llist = lectureAppyMysqlDao.getAppList(memberNo);
      out.println("<table border='1'>");
      out.println("<tr>");
      out.print(" <th>수강신청번호</th>");
      out.print(" <th>강의명</th>");
      out.print(" <th>강의설명</th>");
      out.print(" <th>삭제하기</th>");
      out.print("</tr>");
      
      /*
      for (Student student : slist) {
        out.printf("<tr>");
        out.printf("<td>%d</td>", student.getMemberNo());
        out.print("<td>이름</td>");
        out.print("<td>삭제하기</td>");
        out.print("<td>삭제하기</td>");
      }*/
      for (LectureApplication lecture : llist) {
        out.printf("<td>%d</td>", lecture.getLectAppyNo());
        out.printf("<td>%s</td>", lecture.getTitl());
        out.printf("<td>%s</td>", lecture.getExplanation());
        out.printf("<td><a href='delete?lectApplyNo=%d&memberNo=%d'>삭제하기</a></td>",
            lecture.getLectAppyNo(), memberNo);
        out.print("</tr>");
      }
      

      out.println("</table>");
      out.printf("<br><a href=../student/list>목록</a>");

      requestDispatcher = request.getRequestDispatcher("/footer"); 
      requestDispatcher.include(request, response);

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      //RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error"); 
      //requestDispatcher.forward(request, response);
      //return;
      e.printStackTrace();
    }
  }
}

