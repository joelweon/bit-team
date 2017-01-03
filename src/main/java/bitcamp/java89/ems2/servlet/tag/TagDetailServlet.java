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

@WebServlet("/tag/detail")
public class TagDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String tagName = request.getParameter("tagName");

      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>태그관리-상세정보</title>");
      out.println("</head>");
      out.println("<body>");

      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/header");
      requestDispatcher.include(request, response);

      out.printf("<h1>%s</h1>\n", tagName);

      TagDao tagDao = (TagDao)ContextLoaderListener.applicationContext.getBean("tagDao");
      ArrayList<Tag> tags = tagDao.getOne(tagName);

      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("  <th>콘텐츠일련번호</th>");
      out.println("  <th>회원일련번호</th>");
      out.println("  <th>제목 또는 내용</th>");
      out.println("</tr>");

      for (Tag tag : tags) {
        out.println("<tr>");
        out.printf("<td>%s</td>\n", tag.getContentNo());
        out.printf("<td>%s</td>\n", tag.getMemberNo());
        out.printf("<td>%s</td>\n", tagDao.getContent(tag.getContentNo()));
        out.println("</tr>");
      }

      out.println("</table>");
      out.println("<a href='list'>목록</a>");

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