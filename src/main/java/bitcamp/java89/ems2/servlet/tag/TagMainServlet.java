package bitcamp.java89.ems2.servlet.tag;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tag/main")
public class TagMainServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/header");
      requestDispatcher.include(request, response);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>태그 서블릿 시연</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<form action='add' method='POST'>");

      out.println("<table style='border:1px; solid red;'>");

      out.println("<tr><th>ContentNo:</th><td><input name='contentNo' type='text'></td></tr>");
      out.println("<tr><th>TagName:</th><td><input name='tagName' type='text'></td></tr>");

      out.println("</table>");

      out.println("<br><button type='submit'>등록</button>");

      out.println("</form>");
      out.println("</body>");

      out.println("<hr>");

      out.println("<body>");
      out.println("<form action='delete' method='POST'>");

      out.println("<table>");

      out.println("<tr><th>ContentNo:</th><td><input name='ContentNo' type='text'></td></tr>");

      out.println("</table>");

      out.println("<br><button type='submit'>삭제</button>");

      out.println("</form>");
      out.println("</body>");
      out.println("</html>");

      requestDispatcher = request.getRequestDispatcher("/footer");
      requestDispatcher.include(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error");
      requestDispatcher.forward(request, response);
      return;
    }
  }
}