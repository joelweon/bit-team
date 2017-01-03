package bitcamp.java89.ems2.servlet.tag;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.TagDao;
import bitcamp.java89.ems2.domain.Tag;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/tag/list")
public class TagListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>테그관리-목록</title>");
      out.println("</head>");
      out.println("<body>");

      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/header");
      requestDispatcher.include(request, response);

      out.println("<h1>테그 정보</h1>");

      TagDao tagDao = (TagDao)ContextLoaderListener.applicationContext.getBean("tagDao");
      ArrayList<Tag> list = tagDao.getList();

      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("  <th>테그명</th>");
      out.println("</tr>");

      for (Tag tag : list) {
        out.println("<tr>");
        out.printf("<td><a href='detail?tagName=%1$s'>%s</td>", tag.getTagName());
        out.println("</tr>");
      }

      out.println("</table>");

      requestDispatcher = request.getRequestDispatcher("/footer");
      requestDispatcher.include(request, response);

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error");
      requestDispatcher.forward(request, response);
      return;
    }
  }
}