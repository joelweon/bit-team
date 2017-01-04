/* FollowListServlet.java*/
package bitcamp.java89.ems2.servlet.follow;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bitcamp.java89.ems2.dao.FollowDao;
import bitcamp.java89.ems2.domain.Follow;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/follow/list")
public class FollowListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    ArrayList<Follow> followingList = null;
    ArrayList<Follow> followerList = null;
    FollowDao followDao = 
        (FollowDao)ContextLoaderListener.applicationContext.getBean("followDao");
    String loginEmail = null;
    String findEmail = null;
    Member memberSub = null;
    Member memberFind = null;
    String duplicateEmail = "";
    
    try {
      
      HttpSession session = request.getSession();
      
      memberSub = (Member)request.getSession().getAttribute("member");
      if (memberSub != null) {
        loginEmail = memberSub.getEmail();
      }
      
      if (loginEmail != null) {
        followingList = followDao.getFollowingList(loginEmail);
        followerList = followDao.getFollowerList(loginEmail);
      }
      
      findEmail = (String)session.getAttribute("findEmail");
      if (findEmail != null) {memberFind = followDao.getOne(findEmail);}
      
      response.setContentType("text/html;charset=UTF-8");
      
      RequestDispatcher rd = request.getRequestDispatcher("/follow/list.jsp");
      request.setAttribute("memberSub", memberSub);
      request.setAttribute("memberFind", memberFind);
      request.setAttribute("duplicateEmail", duplicateEmail);
      request.setAttribute("findEmail", findEmail);
      request.setAttribute("loginEmail", loginEmail);
      request.setAttribute("followingList", followingList);
      request.setAttribute("followerList", followerList);
      rd.include(request, response);
      

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("../error");
      rd.forward(request, response);
      return;
    }
  }
}

