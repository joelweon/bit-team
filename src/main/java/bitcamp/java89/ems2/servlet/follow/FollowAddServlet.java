/* FollowAddServlet.java */
package bitcamp.java89.ems2.servlet.follow;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bitcamp.java89.ems2.dao.FollowDao;
import bitcamp.java89.ems2.domain.Follow;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/follow/add")
public class FollowAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
  	
    try {
      Follow follow = new Follow();
      follow.setFollowSubject(Integer.parseInt(request.getParameter("followSubject")));
      follow.setFollowObject(Integer.parseInt(request.getParameter("followObject")));
      
      HttpSession session = request.getSession();
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
  
      FollowDao followDao = (FollowDao)ContextLoaderListener.applicationContext.getBean("followDao");
      out.println(followDao.existFollow(follow.getMemberNo()));
      
      followDao.insert(follow);
      session.setAttribute("findEmail", "");
      response.sendRedirect("./list");
      
    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("../error");
      rd.forward(request, response);
      return;
    }
  }
}
