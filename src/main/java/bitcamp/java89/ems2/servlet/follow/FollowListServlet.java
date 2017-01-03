/* FollowListServlet.java*/
package bitcamp.java89.ems2.servlet.follow;

import java.io.IOException;
import java.io.PrintWriter;
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
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ArrayList<Follow> followingList = null;
    ArrayList<Follow> followerList = null;
    FollowDao followDao = (FollowDao)ContextLoaderListener.applicationContext.getBean("followDao");
    String loginEmail = null;
    String findEmail = null;
    Member memberSub = null;
    Member memberFind = null;
    String duplicateEmail = "";
    
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
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
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>팔로우관리-목록</title>");
      out.println("<link rel='stylesheet' type='text/css' href='http://aoicielv.woobi.co.kr/bit/follow.css'>");
      out.println("</head>");
      out.println("<body class='admin list'>");
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/header");
      requestDispatcher.include(request, response);
      out.println("<div class='con'>");

      
      /********* 팔로잉리스트 *********/
      out.println("<div class='conFollowing'>");
      out.println("<h4>팔로잉리스트 </h4>");
      out.println("<div class='board_list yp_list'>");
      out.println("<table border='0' cellspacing='0' cellpadding='0'>");
      out.println("<thead>");
      out.println("<tr>");
      out.println("<th scope='col' class='img'>이미지</th>");
      out.println("<th scope='col' class='name'>이름</th>");
      out.println("<th scope='col' class='email'>이메일</th>");
      out.println("<th scope='col' class='num_phone'>전화번호</th>");
      out.println("<th scope='col' class='btn'>버튼</th>");
      out.println("</tr>");
      out.println("</thead>");
      out.println("<tbody>");

      if (loginEmail != null && followingList != null) {
        for (Follow follow : followingList) {
          out.println("<tr>");
          out.printf("<td class='img'>");
          if (follow.getFollowPhoto() != null)  {
           out.printf("<img src='../upload/%s' height='30'>", follow.getFollowPhoto());
          } else {
            out.printf( "noImg");
          }
          out.printf("</td>");
          out.printf("<td class='name'>%s</td>"
              + "<td class='email'>%s</td>"
              + "<td class='num_phone'>%s</td>"
              + "<td class='btn'><a href='delete?deleteEmail=%s'>삭제</a></td>\n",
                  follow.getFollowName(), follow.getFollowEmail(), follow.getFollowTel(), follow.getFollowEmail());
          out.println("</tr>");
        }
      }
      out.println("</tbody>");
      out.println("</table>");
      out.println("</div>");
      out.println("</div>");

      
      /********* 팔로워리스트 *********/
      out.println("<div class='conFollower'>");
      out.println("<h4>팔로워리스트 </h4>");
      out.println("<div class='board_list yp_list'>");
      out.println("<table border='0' cellspacing='0' cellpadding='0'>");
      out.println("<thead>");
      out.println("<tr>");
      out.println("<th scope='col' class='img'>이미지</th>");
      out.println("<th scope='col' class='name'>이름</th>");
      out.println("<th scope='col' class='email'>이메일</th>");
      out.println("<th scope='col' class='num_phone'>전화번호</th>");
      out.println("</tr>");
      out.println("</thead>");
      out.println("<tbody>");

      if (loginEmail != null && followerList != null) {
        for (Follow follow : followerList) {
          out.println("<tr>");
          out.printf("<td class='img'>");
          if (follow.getFollowPhoto() != null)  {
           out.printf("<img src='../upload/%s' height='30'>", follow.getFollowPhoto());
          } else {
            out.printf( "noImg");
          }
          out.printf("</td>");
          out.printf(
              "<td class='name'>%s</td>" + "<td class='email'>%s</td>" + "<td class='num_phone'>%s</td>",
              follow.getFollowName(), follow.getFollowEmail(), follow.getFollowTel());
          out.println("</tr>");
        }
      }
      out.println("</tbody>");
      out.println("</table>");
      out.println("</div>");
      out.println("</div>");

      
      // ********* 회원검색 *********/
      out.println("<div class='conSearch'>");
      out.println("<h4>회원검색</h4>");
      out.println("<form action='find' method='POST'");
      out.println("<div class='searchBox2'>");
      out.println("<p class='search'>");
      out.println(
          "<input type='text' name='findEmail' class='input_style_search' maxlength='30' placeholder='이메일을 입력하세요' />");
      out.println("<button type='submit' class='btn_search'>검색</button>");
      out.println("</p>");
      out.println("</form>");
      
      if (loginEmail != null && followingList != null) {
        for (Follow follow : followingList) {
          if (follow.getFollowEmail().equals(findEmail))  {
          	duplicateEmail = findEmail;
          }
        }
      } 
      System.out.println(duplicateEmail + "top");
      
			if (memberSub == null) {
				out.println("<div class='empty'>로그인 후 검색하세요.</div>");
			} else if (findEmail == null) {
				out.println("<div class='empty'>팔로잉 가능한 회원을 검색하세요.</div>");
			} else if (duplicateEmail.equals(findEmail)) {
			  System.out.println(duplicateEmail);
				out.println("<div class='empty'>이미 팔로잉된 회원입니다.</div>");
			} else if (loginEmail != null && findEmail != null && !("".equals(findEmail))) {
				
				if (!loginEmail.equals(findEmail)) {
					out.println("<form action='add' method='POST'>");
					out.println("<div class='board_list yp_list'>");
					out.println("<table border='0' cellspacing='0' cellpadding='0'>");
					out.println("<tbody>");
					out.printf(
							"<td class='name'>%s</td>" + "<td class='email'>%s</td>" + "<td class='num_phone'>%s</td>"
									+ "<td class='btn'><button type='submit'>추가</button></td>\n",
							memberFind.getName(), memberFind.getEmail(), memberFind.getTel());
					out.printf("<input type='hidden' name='followSubject' value=%s>", memberFind.getMemberNo());
					out.printf("<input type='hidden' name='followObject' value=%s>", memberSub.getMemberNo());
					out.println("</tr>");
					out.println("</tbody>");
					out.println("</table>");
					out.println("</div>");
					out.println("</form>");
				} else {
					out.println("<div class='empty'>본인의 이메일은 검색하실 수 없습니다.</div>");
				} 
			} 
			System.out.println(findEmail + "find");
			System.out.println(duplicateEmail + "btm");
      out.println("</div>");
      out.println("</div>");
      
      
      out.println("</div>");	
      
      requestDispatcher = request.getRequestDispatcher("/footer");
      requestDispatcher.include(request, response);
      
      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("../error");
      rd.forward(request, response);
      return;
    }
  }
}