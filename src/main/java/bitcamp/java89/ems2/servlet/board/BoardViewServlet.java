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
import bitcamp.java89.ems2.dao.DownloadDao;
import bitcamp.java89.ems2.domain.Board;
import bitcamp.java89.ems2.domain.Download;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/board/view")
public class BoardViewServlet extends HttpServlet {
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
   
      out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css'>");
      out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css'>");
      out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js'></script>");
      
      out.println("<title>게시글</title>");
      out.println("</head>");
      
      out.println("<body>");

      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/boardheader");
      rd.include(request, response);

      out.println("<form>");

      BoardDao boardDao = (BoardDao)ContextLoaderListener.applicationContext.getBean("boardDao");
      DownloadDao downloadDao = (DownloadDao)ContextLoaderListener.applicationContext.getBean("downloadDao");
      
      Board board = boardDao.getOne(contentsNo);
      Download download = downloadDao.getOne(contentsNo + 1); /***파일no수정필요****/

      if (board == null) {
        throw new Exception("게시글이 존재하지 않습니다.");
      }
      
      out.println("<div style='margin:auto;'>");
      
      out.println("<div style='padding-top:50px; padding-bottom:10px; text-align:center; margin:auto;'>");
      out.printf("<h2 style='border-bottom:3px solid gray; width:500px; margin:auto;'>%s</h2>\n", board.getTitle());
      out.println("</div>");
      
      out.println("<div style='width:70%; margin:auto;'>");
      out.println("<table class='table table-bordered' style='margin:auto; max-width:85%; text-align:center;'>");
      out.printf("<tr><th><input name='contentsNo' type='text' value=%s readOnly style='border:none; text-align:center;'></th>"
          + "<th><input name='name' type='text' value=%s readOnly style='border:none; text-align:center;'></th>"
          + "<th><input type='text' value='                         ' readOnly style='border:none; text-align:center;'>"
          + "<th><input name='registDate' value=%s readOnly style='border:none; text-align:center;'></th>"
          + "<th><input name='viewContents' value='조회수 |              %d' readOnly style='border:none;'></th>"
          + "</tr>", board.getContentNo(), board.getName(), board.getRegisterDate(), board.getViewCount()+1);
      out.println("<tr><td colspan='5'>");
      out.printf("<br><textarea name='contents' rows='30' cols='125' readOnly style='border:none;'>%s</textarea><br>", board.getContents());
      out.println("</td></tr>");
      if (download != null) {
        out.printf("<tr><th style='text-align:center;'>첨부파일</th><td colspan='4' style='text-align:left;'><a href='../download/detail?contentNo=%d'>%s</a></td></tr>\n",
            board.getContentNo() + 1, download.getPath()); /***파일no수정필요****/
      }
      out.println("</table>");
      out.println("</form>");

      out.println("<form action='detail' method='GET'>");
      out.printf("<input type='hidden' name='contentsNo' value='%d'>\n", board.getContentNo());

      out.println("<div style='width:300px; height:60px; padding-left:3%; padding-top:10px;'>");
      out.println("<button type='submit'>수정</button> \n");
      out.printf("<a href='delete?contentsNo=%d'>삭제</a>\n", board.getContentNo());
      out.println("<a href='list'>목록</a>");
      out.println("</div>");
      out.println("</div>");

      out.println("</form>");
      out.println("</div>");

      boardDao.updateViewCount(board.getViewCount()+1, board.getContentNo());
      
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
