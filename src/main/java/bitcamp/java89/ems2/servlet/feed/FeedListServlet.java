package bitcamp.java89.ems2.servlet.feed;

import java.io.IOException;
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
      
      FeedDao feedDao = (FeedDao)ContextLoaderListener.applicationContext.getBean("feedDao");
      ArrayList<Feed> list = feedDao.getList();
      
      response.setContentType("text/html;charset=UTF-8");
      
      String urlPath = "../feed/list"; 
      request.getSession().setAttribute("urlPath", urlPath);
      
      RequestDispatcher rd = request.getRequestDispatcher("/feed/list.jsp");
      request.setAttribute("feeds", list);
      rd.include(request, response);
      
      
    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
  }
}
