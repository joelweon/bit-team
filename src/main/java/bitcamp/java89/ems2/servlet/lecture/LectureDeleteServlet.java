package bitcamp.java89.ems2.servlet.lecture;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.LectureDao;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/lecture/delete")
public class LectureDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    try {
      int lectureNo = Integer.parseInt(request.getParameter("lectureNo"));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("<title>강의관리-삭제</title>");
      out.println("</head>");
      out.println("<body>");
      
      // HeaderServlet에게 머리말 HTML 생성을 요청한다. 
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>삭제 결과</h1>");

      LectureDao lectureDao = (LectureDao)ContextLoaderListener.applicationContext.getBean("lectureDao");
    
      if (!lectureDao.exist(lectureNo)) {
        throw new Exception("학생을 찾지 못했습니다.");
      }
      
      lectureDao.delete(lectureNo);
      out.println("<p>삭제하였습니다.</p>");
      
      // FooterServlet에게 꼬리말 HTML 생성을 요청한다. 
      rd = request.getRequestDispatcher("/footer");
      rd.include(request, response);
      
      out.println("</body>");
      out.println("</html>");
      
    } catch (Exception e) {
      // 오류 정보를 ServletRequest에 담는다.
      request.setAttribute("error", e);
      
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
  }  
}
