package bitcamp.java89.ems2.servlet.tag;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.impl.TagMysqlDao;
import bitcamp.java89.ems2.domain.Content;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/tag/delete")
public class TagDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Content content = new Content();
    content.setContentNo(Integer.parseInt(request.getParameter("ContentNo")));

    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='Refresh' content='2;url=main'>");
      out.println("<title>태그 삭제 결과</title>");
      out.println("</head>");
      out.println("<body>");

      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/header");
      requestDispatcher.include(request, response);
      TagMysqlDao tagDao = (TagMysqlDao)ContextLoaderListener.applicationContext.getBean("tagDao");

      out.println("<h4>태그 삭제 되었습니다.</h4>");

      tagDao.delete(content);

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