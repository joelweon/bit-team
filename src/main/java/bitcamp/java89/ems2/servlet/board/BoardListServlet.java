
package bitcamp.java89.ems2.servlet.board;

import java.io.IOException;
import java.io.PrintWriter;
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
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
  
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>게시판관리-목록</title>");
      out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css'>");
      out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css'>");
      out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js'></script>");
      
      
      out.println("</head>");
      out.println("<body>");
      
      RequestDispatcher rd = request.getRequestDispatcher("/boardheader");
      rd.include(request, response);
      
      out.println("<h1>게시판 정보</h1>");
     
      BoardDao boardDao = 
          (BoardDao)ContextLoaderListener.applicationContext.getBean("boardDao");
      ArrayList<Board> list = boardDao.getList();

      out.println("<a class='btn btn-default' href='form.html' role='button'>글쓰기</a><br>");
      out.println("<table border='1' class='table table-striped'>");
      out.println("<tr>");
      out.println("  <th>번호</th>");
      out.println("  <th>제목</th>");
      out.println("  <th>작성자</th>");
      out.println("  <th>등록일</th>");
      out.println("  <th>조회수</th>");
      out.println("</tr>");
      
      for (Board board : list) {
        out.println("<tr> ");
        out.printf(" <td>%d</td>"
            + "<td><a href='view?contentsNo=%1$d'>%s</a></td>"
            + "<td>%s</td>"
            + "<td>%s</td>"
            + "<td>%s</td>\n",
          board.getContentNo(),
          board.getTitle(),
          board.getName(),
          board.getRegisterDate(),
          board.getViewCount());
        out.println("</tr>");
      }
      
      out.println("</table>");
      
//    // FooterServlet에게 꼬리말 HTML 생성을 요청한다.
//    rd = request.getRequestDispatcher("/tag/add");
//    rd.include(request, response);
      
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
