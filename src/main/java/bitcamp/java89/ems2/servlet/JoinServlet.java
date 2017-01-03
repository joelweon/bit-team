package bitcamp.java89.ems2.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<div id='join' style='margin-top:10px; margin-left:40px;'>");
    out.printf("<!DOCTYPE html>\n<html>\n<head>\n<meta charset='UTF-8'>\n</head>\n<body>\n<form>\n");
    out.printf("<tr><th>이메일: </th><td><input type='text' name='email'><td>\n<button>로그인</button></tr>\n");
    out.printf("</form>\n</body>\n</html>\n");
    out.println("</div>");

  }
}
