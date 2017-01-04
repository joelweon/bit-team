package bitcamp.java89.ems2.servlet.download;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.DownloadDao;
import bitcamp.java89.ems2.domain.Download;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/download/list")
public class DownloadListServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {
      response.setContentType("text/html;charset=UTF-8");

      DownloadDao downloadDao = (DownloadDao) ContextLoaderListener.applicationContext.getBean("downloadDao");
      ArrayList<Download> list = downloadDao.getList();
      request.setAttribute("downloads", list);

      Member member = (Member)request.getSession().getAttribute("member");
      if (member != null) {
        request.setAttribute("member", member);
      }

      System.out.println("[/download/list] 시작");

      RequestDispatcher rd = request.getRequestDispatcher("/download/list.jsp");
      rd.include(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("../error");
      rd.forward(request, response);
      return;
    }
  }
}