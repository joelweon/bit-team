package bitcamp.java89.ems2.servlet.feed;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.FeedDao;
import bitcamp.java89.ems2.domain.Feed;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/feed/detail")
public class FeedDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      
      int contentNo = Integer.parseInt(request.getParameter("ContentNo"));

      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>피드관리-상세정보</title>");
      out.println("</head>");
      out.println("<body>");
      
      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>상세 정보</h1>");
    
     out.println("<form action='update' method='POST'>");

      FeedDao feedDao = (FeedDao)ContextLoaderListener.applicationContext.getBean("feedDao");


      Feed feed = feedDao.getOne(contentNo);
      
      if (feed == null) {
        throw new Exception("해당 학생이 없습니다.");
      }
      
      out.println("<table border='1'>");
      out.printf("<tr><th>게시물 번호</th><td>"
          + "<input name='cono' type='text' value='%d' readonly></td></tr>\n", 
          feed.getContentNo());
      out.printf("<tr><th>조회수</th><td>"
          + "<input name='vw_cnt' type='text' value='%s' readonly></td></tr>\n", 
          feed.getViewCount()+1);
      out.printf("<tr><th>작성자 이름</th><td>"
          + "<input name='name' type='text' value='%s' readonly></td></tr>\n", 
          feed.getName());
      out.printf("<tr><th>등록일</th><td>"
          + "<input name='rdt' type='text' value='%s'></td></tr>\n", 
          feed.getRegisterDate());
      out.printf("<tr><th>내용</th><td>"
          + "<input name='conts' type='text' value='%s'></td></tr>\n", 
          feed.getContents());
      out.println("</td></tr>");
      out.println("</table>");
      
      out.println("<button type='submit'>변경</button>");
      out.printf(" <a href='delete?contentNo=%d'>삭제</a>\n", feed.getContentNo());
      
      out.println(" <a href='list'>목록</a>");
      out.println("</form>");
      feedDao.updateCount(feed.getViewCount()+1, feed.getContentNo());
      
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