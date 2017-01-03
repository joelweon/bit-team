
package bitcamp.java89.ems2.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.domain.Member;

@WebServlet("/boardheader")
public class boardHeaderServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<div id='header' style='background-color:rgb(64, 64, 64); height:180px;'>");
    out.println("<img src='../image/bitlogo.jpg'"
        + " height='100'style='float: left; margin-top: 25px; margin-left: 50px;'>");
    
    // 로그인 사용자 정보를 가져온다.
    out.println("<div style='width:200px; height:38px;"
        + "position:absolute; right:0px; top:0px; margin-right:10px;'>");
    Member member = (Member)request.getSession().getAttribute("member");
    if (member == null) {
      out.println("<a href='../auth/login' style='position:absolute; right:0px; top:15px;'>로그인</a>");
    } else {
//      String[] name = member.getEmail().split("@");
//      out.printf("<tr><th></th><td>"
//          + "<img src='../upload/%s.PNG' style='margin-top:12px; margin-left:50px; height:33px;'>", name[0]);
      out.printf("<span style='position:absolute; right:70px; top:15px;'>%s</span>\n", member.getName());
      out.println("<a href='../auth/logout' style='position:absolute; right:0px; top:15px;'>로그아웃</a>");
    }
    out.println("</div>");
    
    out.println("</div>");
  }
}
