
package bitcamp.java89.ems2.servlet.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.BoardDao;
import bitcamp.java89.ems2.domain.Board;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      BoardDao boardDao = 
          (BoardDao)ContextLoaderListener.applicationContext.getBean("boardDao");
      ArrayList<Board> list = boardDao.getList();
      request.setAttribute("board", list);
      
      String urlPath = "../board/list"; 
      request.getSession().setAttribute("urlPath", urlPath);
      
      response.setContentType("text/html;charset=UTF-8");
      RequestDispatcher rd = request.getRequestDispatcher("/board/list.jsp");
      rd.include(request, response);
      
      
    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
    
  }
}
