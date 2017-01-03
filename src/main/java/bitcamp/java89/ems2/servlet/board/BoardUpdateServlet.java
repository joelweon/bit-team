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
import bitcamp.java89.ems2.domain.Board;
import bitcamp.java89.ems2.listener.ContextLoaderListener;
import bitcamp.java89.ems2.util.MultipartUtil;

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet{

  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      Map<String,String> dataMap = MultipartUtil.parse(request);
      Board board = new Board();
      board.setContentNo(Integer.parseInt(dataMap.get("contentsNo")));
      board.setTitle(dataMap.get("title"));
      board.setContents(dataMap.get("contents"));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.printf("<meta http-equiv='Refresh' content='1;url=view?contentsNo=%d'>", board.getContentNo());
      out.println("<title>게시판내용-변경</title>");
      out.println("</head>");
      out.println("<body>");
      
      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>변경 결과</h1>");
    
      BoardDao boardDao = (BoardDao)ContextLoaderListener.applicationContext.getBean("boardDao");
      
      if (!boardDao.exist(board.getContentNo())) {
        throw new Exception("해당 글을 찾지 못했습니다.");
      }
      
      ContentDao contentDao = (ContentDao)ContextLoaderListener.applicationContext.getBean("contentDao");
      contentDao.update(board);
      boardDao.update(board);
      
      out.println("<p>변경 하였습니다.</p>");
      
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