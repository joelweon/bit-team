package bitcamp.java89.ems2.servlet.download;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.DownloadDao;
import bitcamp.java89.ems2.domain.Download;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/download/detail")
public class DownloadDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {
      int contentNo = Integer.parseInt(request.getParameter("contentNo"));

      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>자료실-상세정보</title>");
      out.println("</head>");
      out.println("<body>");

      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/header");
      requestDispatcher.include(request, response);

      out.println("<h1>자료실</h1>");
      out.println("<form action='update' method='POST' enctype='multipart/form-data'>");

      DownloadDao downloadDao = (DownloadDao) ContextLoaderListener.applicationContext.getBean("downloadDao");
      Download download = downloadDao.getOne(contentNo);

      if (download == null) {
        throw new Exception("잘못된 컨텐츠 일련번호입니다.");
      }
      out.println("<table border='1'>");
      out.printf("<input type='hidden' name='contentNo' value=%d>", download.getContentNo());
      out.printf("<tr><th>작성회원 일련번호</th><td>" + "<input name='memberNo' type='text' value='%s'></td></tr>\n",
          download.getMemberNo());
      out.printf("<tr><th>등록일</th><td>" + "<input name='registerDate' type='text' value='%s'></td></tr>\n",
          download.getRegisterDate());
      out.printf("<tr><th>파일</th><td>" + "<a href='../upload/%1$s'>%1$s</a><br>"
          + "<input name='filepath' type='file' value='%1$s'></td></tr>\n", download.getPath());
      out.println("</table>");
      out.println("<button type='submit'>변경</button>");
      out.printf("<a href='delete?contentNo=%d&filename=%s'>삭제</a>", download.getContentNo(), download.getPath());
      out.println(" <a href='list'>목록</a>");
      System.out.println(download.getContentNo());

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
