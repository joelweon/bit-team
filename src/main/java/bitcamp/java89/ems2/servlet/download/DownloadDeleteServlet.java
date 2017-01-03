package bitcamp.java89.ems2.servlet.download;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.DownloadDao;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/download/delete")
public class DownloadDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int contentNo = Integer.parseInt(request.getParameter("contentNo"));
      String filename = request.getParameter("filename");

      File deleteFile = new File(request.getServletContext().getRealPath("/upload/" + filename));
      if (!deleteFile.delete()) {
        throw new Exception("[/download/delete] 파일 삭제 실패");
      }

      DownloadDao downloadDao = (DownloadDao) ContextLoaderListener.applicationContext.getBean("downloadDao");
      downloadDao.delete(contentNo);
      response.sendRedirect("./list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("../error");
      rd.forward(request, response);
      return;
    }

  }
}
