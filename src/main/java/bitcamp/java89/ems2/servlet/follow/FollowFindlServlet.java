/* FollowFindServlet.java*/
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

import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.domain.Follow;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/follow/find")
public class FollowFindlServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");

      HttpSession session = request.getSession();
      MemberDao memberDao = (MemberDao)ContextLoaderListener.applicationContext.getBean("memberDao");
      
      String findEmail = request.getParameter("findEmail");
      Member member = memberDao.getOne(findEmail);
      
			if (member != null) {
				session.setAttribute("findEmail", findEmail);
				response.sendRedirect("./list");
				return;
			} else {
				response.sendRedirect("./list");
			}

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("../error");
      rd.forward(request, response);
      return;
    }
  }
}