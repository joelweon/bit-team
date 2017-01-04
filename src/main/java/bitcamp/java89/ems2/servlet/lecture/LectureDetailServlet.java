package bitcamp.java89.ems2.servlet.lecture;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.ClassroomDao;
import bitcamp.java89.ems2.dao.LectureDao;
import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Classroom;
import bitcamp.java89.ems2.domain.Lecture;
import bitcamp.java89.ems2.domain.Manager;
import bitcamp.java89.ems2.domain.Teacher;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/lecture/detail")
public class LectureDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    try {
      int lectureNo = Integer.parseInt(request.getParameter("lectureNo"));

      LectureDao lectureDao = (LectureDao)ContextLoaderListener.applicationContext.getBean("lectureDao");
      Lecture lecture = lectureDao.getOne(lectureNo);

      if (lecture == null) {
        throw new Exception("해당 강의가 없습니다.");
      }
      
      ClassroomDao classroomDao = (ClassroomDao)ContextLoaderListener.applicationContext.getBean("classroomDao");
      ManagerDao managerDao = (ManagerDao)ContextLoaderListener.applicationContext.getBean("managerDao");
      TeacherDao teacherDao = (TeacherDao)ContextLoaderListener.applicationContext.getBean("teacherDao");
      
      ArrayList<Classroom> classroomList = classroomDao.getList();
      ArrayList<Manager> managerList = managerDao.getList();
      ArrayList<Teacher> teacherList = teacherDao.getList();
      
      response.setContentType("text/html;charset=UTF-8");

      RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
      request.setAttribute("lecture", lecture);
      request.setAttribute("classroomList", classroomList);
      request.setAttribute("managerList", managerList);
      request.setAttribute("teacherList", teacherList);
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
