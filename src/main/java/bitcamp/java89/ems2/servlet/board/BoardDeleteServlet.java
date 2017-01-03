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
import bitcamp.java89.ems2.dao.ContentDao;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      //int downNo = Integer.parseInt(request.getParameter("downNo"));
      int boardNo = Integer.parseInt(request.getParameter("contentsNo"));
      int contentsNo = Integer.parseInt(request.getParameter("contentsNo"));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
  
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("<title>게시판관리-삭제</title>");
      out.println("</head>");
      out.println("<body>");
      
      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>삭제 결과</h1>");
      
     // DownloadDao downloadDao = 
       //   (DownloadDao)ContextLoaderListener.applicationContext.getBean("downloadDao");
      BoardDao boardDao = 
          (BoardDao)ContextLoaderListener.applicationContext.getBean("boardDao");
      ContentDao contentDao = 
          (ContentDao)ContextLoaderListener.applicationContext.getBean("contentDao");
      
      //downloadDao.boardDelete(contentsNo);
      boardDao.delete(boardNo);
      contentDao.delete(contentsNo);
      //contentDao.downdelete(contentsNo);
      
      out.println("<p>삭제하였습니다.</p>");
    
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
