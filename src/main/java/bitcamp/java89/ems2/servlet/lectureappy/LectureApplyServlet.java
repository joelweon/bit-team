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

@WebServlet("/lectureappy/apply")
public class LectureApplyServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      LectureApplication lectureApplication = new LectureApplication();
      lectureApplication.setLectureNumber(Integer.parseInt(request.getParameter("lno")));
      lectureApplication.setReportingDate(request.getParameter("rdt"));
      lectureApplication.setStatement(Integer.parseInt(request.getParameter("stat")));
      lectureApplication.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
  
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.printf("<meta http-equiv='Refresh' content='1;url=../lecture/list?memberNo=%d'>",
          lectureApplication.getMemberNo());
      out.println("<title>수강신청</title>");
      out.println("</head>");
      out.println("<body>");
      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>등록 결과</h1>");
    
      LectureAppyMysqlDao lectureAppyDao = (LectureAppyMysqlDao)ContextLoaderListener.applicationContext.getBean("lectureAppyDao");
    
      if (lectureAppyDao.exist(lectureApplication)) {
        throw new Exception("이미 수강신청된 강의입니다.");
      }
      
      lectureAppyDao.insert(lectureApplication);
      out.println("<p>등록하였습니다.</p>");
      
      // FooterServlet에게 꼬리말 HTML 생성을 요청한다.
      rd = request.getRequestDispatcher("/footer");
      rd.include(request, response);
      
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
