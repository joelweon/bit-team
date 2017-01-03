
package bitcamp.java89.ems2.servlet.download;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.DownloadDao;
import bitcamp.java89.ems2.domain.Download;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/download/list")
public class DownloadListServlet extends HttpServlet {

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
      out.println("<title>자료실-목록</title>");
      out.println("</head>");
      out.println("<body>");

      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/header");
      requestDispatcher.include(request, response);

      out.println("<h1>자료실</h1>");

      DownloadDao downloadDao = (DownloadDao) ContextLoaderListener.applicationContext.getBean("downloadDao");
      System.out.println("[/download/list] 시작");
      ArrayList<Download> list = downloadDao.getList();

      out.println("<a href='form.html'>추가</a><br>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("  <th>컨텐츠일련번호</th>");
      out.println("  <th>파일경로</th>");
      out.println("  <th>작성회원번호</th>");
      out.println("  <th>등록일</th>");
      out.println("</tr>");

      for (Download download : list) {
        out.println("<tr> ");
        out.printf("  <td>%d</td>" + "<td><a href='detail?contentNo=%1$d'>%s</a></td>" + "<td>%d</td>" + "<td>%s</td>",
            download.getContentNo(), download.getPath(), download.getMemberNo(), download.getRegisterDate());
      }

      out.println("</table>");

      out.println("</body>");
      out.println("</html>");

      requestDispatcher = request.getRequestDispatcher("/footer");
      requestDispatcher.include(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("../error");
      rd.forward(request, response);
      return;
    }
  }
}
