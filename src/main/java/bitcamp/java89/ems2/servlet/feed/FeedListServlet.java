package bitcamp.java89.ems2.servlet.feed;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.FeedDao;
import bitcamp.java89.ems2.domain.Feed;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/feed/list")
public class FeedListServlet extends HttpServlet {
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
      out.println("<meta http-equiv='Refresh' content='10;url=list'>");
      out.println("<title>피드관리-목록</title>");
      out.println("</head>");
      out.println("<body>");
      
      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>피드 정보</h1>");

      FeedDao feedDao = (FeedDao)ContextLoaderListener.applicationContext.getBean("feedDao");
      ArrayList<Feed> list = feedDao.getList();

      out.println("<a href='form.html'>추가</a><br>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("  <th>컨텐츠번호</th>");
      out.println("  <th>이름</th>");
      out.println("  <th>등록일</th>");
      out.println("  <th>조회수</th>");
      out.println("  <th>내용</th>");
      out.println("</tr>");
      
      for (Feed feed : list) {
        out.println("<tr> ");        
        out.printf("  <td><a href='detail?ContentNo=%1$d'>%d</a></td>"
            + "<td>%s</td>"
            + "<td>%s</td>"
            + "<td>%d</td>"
            + "<td>%s</td>\n",  
          feed.getContentNo(),
          feed.getName(),
          feed.getRegisterDate(),
          feed.getViewCount(),
          feed.getContents());
        
        out.println("</tr>");
      }
      
      out.println("</table>");
      
      // FooterServlet에게 꼬리말 HTML 생성을 요청한다.
      rd = request.getRequestDispatcher("/footer");
      rd.include(request, response);
      
      out.println("</body>");
      out.println("</html>");
      
    } catch (Exception e) {
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
  }
}
