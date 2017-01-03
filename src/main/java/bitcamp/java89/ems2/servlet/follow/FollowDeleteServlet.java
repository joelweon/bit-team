/*FollowDeleteServlet.java*/
package bitcamp.java89.ems2.servlet.follow;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.FollowDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.domain.Follow;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/follow/delete")
public class FollowDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

	@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
  	Follow followSub = new Follow();
  	
    try {
      response.setContentType("text/html;charset=UTF-8");
      
      
      Member memberSub = (Member)request.getSession().getAttribute("member");
      followSub.setFollowSubject(memberSub.getMemberNo());
      String deleteEmail = request.getParameter("deleteEmail");
  

      FollowDao followDao = (FollowDao)ContextLoaderListener.applicationContext.getBean("followDao");
      MemberDao memberDao = (MemberDao)ContextLoaderListener.applicationContext.getBean("memberDao");
      Member memberDel = memberDao.getOne(deleteEmail);
    
      if (memberDel != null) {  //memberDao에 일련번호로 찾는 exist() 메서드 없음
        Follow follow = new Follow();
        follow.setFollowSubject(followSub.getFollowSubject());
        follow.setFollowObject(memberDel.getMemberNo());
        followDao.delete(follow);
        response.sendRedirect("./list");
        return;
      }
      throw new Exception("[FollowDelete] 대상 이메일의 회원이 없습니다.");

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("../error");
      rd.forward(request, response);
      return;
    }
  }  
}