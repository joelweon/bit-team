package bitcamp.java89.ems2.servlet.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");

    RequestDispatcher rd = request.getRequestDispatcher("/auth/loginform.jsp");
    rd.include(request, response);

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String saveEmail = request.getParameter("saveEmail");
      String urlPath = (String)request.getSession().getAttribute("urlPath");

      if (saveEmail != null) {
        Cookie cookie = new Cookie("email", email);
        cookie.setMaxAge(60 * 60 * 24 * 15);
        response.addCookie(cookie);
      } else {
        Cookie cookie = new Cookie("email", email);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
      }

      MemberDao memberDao = (MemberDao)ContextLoaderListener.applicationContext.getBean("memberDao");
      Member member = memberDao.getOne(email, password);

      if (member != null) {
        String userType = request.getParameter("userType");
        Member detailMember = this.getMemberInfo(userType, member.getMemberNo());

        if (detailMember != null) {
          request.getSession().setAttribute("member", detailMember);
          if (urlPath != null)
            response.sendRedirect(urlPath);
          response.sendRedirect("../student/list");
          request.getSession().setAttribute("urlPath", null);
          return;
        } 
      }
      
      response.setHeader("refresh", "2;url=login");
      response.setContentType("text/html;charset=UTF-8");
      
      RequestDispatcher rd = request.getRequestDispatcher("/auth/loginfail.jsp");
      rd.include(request, response);
      
    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
//      rd.forward(request, response);
      return;
    }
  }

  private Member getMemberInfo(String userType, int memberNo) throws Exception {
    if (userType.equals("student")) {
      StudentDao studentDao = (StudentDao)ContextLoaderListener.applicationContext.getBean("studentDao");
      return studentDao.getOne(memberNo);
    } else if (userType.equals("teacher")) {
      TeacherDao teacherDao = (TeacherDao)ContextLoaderListener.applicationContext.getBean("teacherDao");
      return teacherDao.getOne(memberNo);
    } else {
      ManagerDao managerDao = (ManagerDao)ContextLoaderListener.applicationContext.getBean("managerDao");
      return managerDao.getOne(memberNo);
    }
  }
}