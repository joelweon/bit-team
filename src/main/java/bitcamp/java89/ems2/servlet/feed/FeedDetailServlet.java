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
      FeedDao feedDao = (FeedDao)ContextLoaderListener.applicationContext.getBean("feedDao");
      Feed feed = feedDao.getOne(contentNo);
      
      if (feed == null) {
        throw new Exception("해당 학생이 없습니다.");
      }
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/feed/detail.jsp");
      request.setAttribute("feed", feed); 
      requestDispatcher.include(request, response);
    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
    
  }
  
  
}