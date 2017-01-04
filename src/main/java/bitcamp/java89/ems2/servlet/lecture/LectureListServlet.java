package bitcamp.java89.ems2.servlet.lecture;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.LectureDao;
import bitcamp.java89.ems2.domain.Lecture;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/lecture/list")
public class LectureListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    try {
      
      LectureDao lectureDao = (LectureDao)ContextLoaderListener.applicationContext.getBean("lectureDao");
      ArrayList<Lecture> list = lectureDao.getList();
      
      response.setContentType("text/html;charset=UTF-8");
      RequestDispatcher rd = request.getRequestDispatcher("/lecture/list.jsp"); 
      request.setAttribute("lectures", list);
      rd.include(request, response);
      
    } catch (Exception e) {
      // 오류 정보를 ServletRequest에 담는다.
      request.setAttribute("error", e);
      
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
  }
}
