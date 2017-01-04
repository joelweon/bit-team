/* FollowAddServlet.java */
package bitcamp.java89.ems2.servlet.download;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.impl.DownloadMysqlDao;
import bitcamp.java89.ems2.domain.Download;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.listener.ContextLoaderListener;
import bitcamp.java89.ems2.util.MultipartUtil;

@WebServlet("/download/add")
public class DownloadAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {
      Map<String, String> dataMap = MultipartUtil.parse(request);
      response.setContentType("text/html;charset=UTF-8");

      Download download = new Download();
      download.setPath(dataMap.get("filepath"));

      Member member = (Member)request.getSession().getAttribute("member");
      download.setMemberNo(member.getMemberNo());

      DownloadMysqlDao downloadDao = (DownloadMysqlDao) ContextLoaderListener.applicationContext.getBean("downloadDao");
      downloadDao.insert(download);
      response.sendRedirect("./list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("../error");
      rd.forward(request, response);
      return;
    }
  }
}