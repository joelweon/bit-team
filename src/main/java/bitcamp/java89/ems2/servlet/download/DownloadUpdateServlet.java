package bitcamp.java89.ems2.servlet.download;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.DownloadDao;
import bitcamp.java89.ems2.domain.Download;
import bitcamp.java89.ems2.listener.ContextLoaderListener;
import bitcamp.java89.ems2.util.MultipartUtil;

@WebServlet("/download/update")
public class DownloadUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      Map<String, String> dataMap = MultipartUtil.parse(request);

      response.setContentType("text/html;charset=UTF-8");

      Download download = new Download();
      download.setContentNo(Integer.parseInt(dataMap.get("contentNo")));
      download.setMemberNo(Integer.parseInt(dataMap.get("memberNo")));
      download.setRegisterDate(dataMap.get("registerDate"));
      download.setPath(dataMap.get("filepath"));

      DownloadDao downloadDao = (DownloadDao) ContextLoaderListener.applicationContext.getBean("downloadDao");

      if (!downloadDao.exist(download.getContentNo())) {
        throw new Exception("해당 파일을 찾지 못했습니다.");
      }

      downloadDao.update(download);
      response.sendRedirect("./list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("../error");
      rd.forward(request, response);
      return;
    }
  }
}
