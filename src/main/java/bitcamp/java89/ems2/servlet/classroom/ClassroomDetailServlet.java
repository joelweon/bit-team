package bitcamp.java89.ems2.servlet.classroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.ClassroomDao;
import bitcamp.java89.ems2.domain.Classroom;
import bitcamp.java89.ems2.domain.Photo;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/classroom/detail")
public class ClassroomDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      int classroomNo = Integer.parseInt(request.getParameter("classroomNo"));
      
      ClassroomDao classroomDao = (ClassroomDao)ContextLoaderListener.applicationContext.getBean("classroomDao");
      Classroom classroom = classroomDao.getOne(classroomNo);
      
      
      if (classroom == null) {
        throw new Exception("해당 강의실이 없습니다.");
      }
      
      response.setContentType("text/html;charset=UTF-8");
   
      RequestDispatcher rd = request.getRequestDispatcher("/classroom/detail.jsp");
      request.setAttribute("classroom", classroom);
      rd.include(request, response);
      

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
    
  }
  
  
}
