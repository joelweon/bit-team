package bitcamp.java89.ems2.servlet.board;

import java.io.IOException;
import java.io.PrintWriter;

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
      int contentsNo = Integer.parseInt(request.getParameter("contentsNo"));

      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>게시글</title>");
      out.println("</head>");
      out.println("<body>");

      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/boardheader");
      rd.include(request, response);

      //out.printf("<h1>학생 정보</h1>");
      out.println("<form action='update' method='POST' enctype='multipart/form-data'>");

      BoardDao boardDao = (BoardDao)ContextLoaderListener.applicationContext.getBean("boardDao");

      Board board = boardDao.getOne(contentsNo);

      if (board == null) {
        throw new Exception("게시글이 존재하지 않습니다.");
      }

      out.println("<table border='1'>");
      out.printf("<tr><th>제목</th><td><input name='title' type='text' value=%s style='width:650px;'></td></tr>", board.getTitle());
      out.printf("<tr><td colspan='2'><textarea name='contents' rows='50' cols='100'>%s</textarea><br></td></tr>", board.getContents());
      out.println("<tr><th>첨부파일</th><td><input name='path' type='file'></td></tr>\n");
      out.println("</table>");

      out.printf("<input type='hidden' name='contentsNo' value='%d'>\n", board.getContentNo());

      out.println("<button type='submit'>등록</button> ");
      out.printf("<a href='delete?contentsNo=%d'>삭제</a>\n", board.getContentNo());
      out.printf("<a href='view?contentsNo=%d'>취소</a>", board.getContentNo());
      out.println("</form>");

      // FooterServlet에게 꼬리말 HTML 생성을 요청한다.
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
