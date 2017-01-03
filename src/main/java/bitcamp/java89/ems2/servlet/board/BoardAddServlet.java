package bitcamp.java89.ems2.servlet.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.BoardDao;
import bitcamp.java89.ems2.dao.ContentDao;
import bitcamp.java89.ems2.dao.DownloadDao;
import bitcamp.java89.ems2.domain.Board;
import bitcamp.java89.ems2.domain.Download;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.listener.ContextLoaderListener;
import bitcamp.java89.ems2.util.MultipartUtil;

@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      Map<String, String> dataMap = MultipartUtil.parse(request);
      
      Board board = new Board();
      Download download = new Download();
//      board.setContentNo(Integer.parseInt(request.getParameter("contentsNo")));
      board.setTitle(dataMap.get("title"));
      board.setContents(dataMap.get("contents"));
      download.setPath(dataMap.get("filePath"));
      
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
  
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='Refresh' content='3; url=list'>");
      out.println("<title>게시판 글쓰기</title>");
      out.println("</head>");
      out.println("<body>");
      
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>등록이 완료되었습니다.</h1>");

      BoardDao boardDao = (BoardDao)ContextLoaderListener.applicationContext.getBean("boardDao");
      DownloadDao downloadDao = (DownloadDao)ContextLoaderListener.applicationContext.getBean("downloadDao");
      
      Member member = (Member)request.getSession().getAttribute("member");
      if (member != null) {
        board.setMemberNo(member.getMemberNo());
        download.setMemberNo(member.getMemberNo());
        
        ContentDao contentDao = (ContentDao)ContextLoaderListener.applicationContext.getBean("contentDao");
        contentDao.insert(board);
        boardDao.insert(board);
        
        if (download.getPath() != null) {
          downloadDao.insert(download);
        }
        
      } else {
        throw new Exception("로그인 후 글쓰기가 가능합니다.");
      }
      
      
      out.println("<p>등록하였습니다.</p>");
      
      rd = request.getRequestDispatcher("/footer");
      rd.include(request, response);
      
      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
  }
}
