package bitcamp.java89.ems2.servlet.download;

import java.io.IOException;

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

      DownloadDao downloadDao = (DownloadDao) ContextLoaderListener.applicationContext.getBean("downloadDao");
      Download download = downloadDao.getOne(contentNo);

      if (download == null) {
        throw new Exception("잘못된 컨텐츠 일련번호입니다.");
      }

      request.setAttribute("download", download);
      request.getRequestDispatcher("/download/detail.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("../error");
      rd.forward(request, response);
      return;
    }
  }
}