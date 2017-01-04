package bitcamp.java89.ems2.servlet.lecture;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.LectureDao;
import bitcamp.java89.ems2.domain.Lecture;
import bitcamp.java89.ems2.listener.ContextLoaderListener;
import bitcamp.java89.ems2.util.MultipartUtil;

@WebServlet("/lecture/update")
public class LectureUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    try {
      Map<String,String> dataMap = MultipartUtil.parse(request);
      
      Lecture lecture = new Lecture();
      lecture.setLectureNo(Integer.parseInt(dataMap.get("lectureNo")));
      lecture.setTitle(dataMap.get("title"));
      lecture.setClassroomNo(Integer.parseInt(dataMap.get("classroomNo")));
      lecture.setManagerNo(Integer.parseInt(dataMap.get("managerNo")));
      lecture.setDescription(dataMap.get("description"));
      lecture.setStartDate(dataMap.get("startDate"));
      lecture.setEndDate(dataMap.get("endDate"));
      lecture.setTotalStudentNumber(Integer.parseInt(dataMap.get("totalStudentNumber")));
      lecture.setPrice(Integer.parseInt(dataMap.get("price")));
      lecture.setTotalTime(Integer.parseInt(dataMap.get("totalTime")));
      
      ArrayList<Integer> teacherNoList = new ArrayList<>();
      teacherNoList.add(Integer.parseInt(dataMap.get("teacherNo1")));
      teacherNoList.add(Integer.parseInt(dataMap.get("teacherNo2")));
      teacherNoList.add(Integer.parseInt(dataMap.get("teacherNo3")));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("<title>강의관리-변경</title>");
      out.println("</head>");
      out.println("<body>");
      
      // HeaderServlet에게 머리말 HTML 생성을 요청한다. 
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>변경 결과</h1>");
      
      LectureDao lectureDao = (LectureDao)ContextLoaderListener.applicationContext.getBean("lectureDao");
      
      if (!lectureDao.exist(lecture.getLectureNo())) {
        throw new Exception("학생을 찾지 못했습니다.");
      }
      
      lecture.setTeacherNoList(teacherNoList);
      
      lectureDao.update(lecture);
      
      out.println("<p>변경 하였습니다.</p>");
      
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
