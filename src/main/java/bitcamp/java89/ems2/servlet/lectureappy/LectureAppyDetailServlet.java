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
import bitcamp.java89.ems2.domain.LectureApplication;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/lectureappy/detail")
public class LectureAppyDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      int[] number = new int[2];
      number[0] = Integer.parseInt(request.getParameter("lectureNo"));
      number[1] = Integer.parseInt(request.getParameter("memberNo"));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
  
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>강의관리-상세정보</title>");
      out.println("</head>");
      out.println("<body>");
      
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>강의 정보</h1>");
      out.println("<form action='apply' method='POST'>");
      
      LectureAppyMysqlDao lectureAppyMysqlDao = (LectureAppyMysqlDao)ContextLoaderListener.applicationContext.getBean("lectureAppyDao");
      
      LectureApplication lectureApplication = lectureAppyMysqlDao.getOne(number);
      
      
      if (lectureApplication == null) {
        throw new Exception("해당 강의가 없습니다.");
      }
      
      out.println("<table border='1'>");
      out.printf("<input name='memberNo' type='hidden' value='%d'>\n", number[1]);
      out.printf("<tr><th>강의일련번호</th><td>"
          + "<input name='lno' type='text' value='%d' readonly></td></tr>\n", 
          lectureApplication.getLectureNumber());
      out.printf("<tr><th>제목</th><td>"
          + "<input type='text' value='%s' readonly></td></tr>\n",
          lectureApplication.getTitl());
      out.printf("<tr><th>설명</th><td>"
          + "<input type='text' value='%s' readonly></td></tr>\n", 
          lectureApplication.getExplanation());
      out.printf("<tr><th>시작일</th><td>"
          + "<input type='text' value='%s' readonly></td></tr>\n", 
          lectureApplication.getStartDate());
      out.printf("<tr><th>종료일</th><td>"
          + "<input type='text' value='%s' readonly></td></tr>\n", 
          lectureApplication.getEndDate());
      out.printf("<tr><th>수강가능인원</th><td>"
          + "<input type='text' value='%d' readonly></td></tr>\n", 
          lectureApplication.getPossibleNumber());
      out.printf("<tr><th>수업료</th><td>"
          + "<input type='text' value='%d' readonly></td></tr>\n", 
          lectureApplication.getPrice());
      out.printf("<tr><th>총시간</th><td>"
          + "<input type='text' value='%s' readonly></td></tr>\n", 
          lectureApplication.getAllTime());
      out.printf("<tr><th>신청일</th><td>"
          + "<input name='rdt' type='text' value='%s' readonly></td></tr>\n", 
          lectureApplication.getReportingDate());
      out.printf("<tr><th>상태</th><td>"
          + "<input name='stat' type='text' value='%s' readonly></td></tr>\n", 
          lectureApplication.getStatement());
      
      out.println("</table>");
      
      if (lectureApplication.getReportingDate() == null) {
        out.println("<button type='submit'>신청</button>");
      }
      
      out.printf(" <a href='../lectureappy/list?memberNo=%d'>목록</a>\n", number[1]);
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