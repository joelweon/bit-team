package bitcamp.java89.ems2.servlet.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.BoardDao;
import bitcamp.java89.ems2.domain.Board;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      int contentsNo = Integer.parseInt(request.getParameter("contentNo"));

      BoardDao boardDao = (BoardDao)ContextLoaderListener.applicationContext.getBean("boardDao");
      Board board = boardDao.getOne(contentsNo);
      
      if (board == null) {
        throw new Exception("게시글이 존재하지 않습니다.");
      }
      
      response.setContentType("text/html;charset=UTF-8");
      
      RequestDispatcher rd = request.getRequestDispatcher("/board/detail.jsp");
      request.setAttribute("board", board);
      rd.include(request, response);
      
    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
  }
}
