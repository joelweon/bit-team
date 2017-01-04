package bitcamp.java89.ems2.servlet.feed;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.ContentDao;
import bitcamp.java89.ems2.dao.FeedDao;
import bitcamp.java89.ems2.dao.TagDao;
import bitcamp.java89.ems2.domain.Feed;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Tag;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/feed/add")
public class FeedAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;  
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    Member member = (Member)request.getSession().getAttribute("member");
    System.out.println((Member)request.getSession().getAttribute("member"));
    
    if (member == null) {
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/auth/loginform.jsp");
      requestDispatcher.forward(request, response);
    }

    try {
      Feed feed = new Feed();
      feed.setContents(request.getParameter("conts")); 
      Tag tag = new Tag();
      tag.setTagName(request.getParameter("tagName"));
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
  
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("<title>피드관리-등록</title>");
      out.println("</head>");
      out.println("<body>");
      
      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/header");
      requestDispatcher.include(request, response);
      
      out.println("<h1>등록 결과</h1>");
      
      feed.setMemberNo(member.getMemberNo());
      
      ContentDao contentDao = (ContentDao)ContextLoaderListener.applicationContext.getBean("contentDao");
      contentDao.insert(feed);
      
      FeedDao feedDao = (FeedDao)ContextLoaderListener.applicationContext.getBean("feedDao");
      feedDao.insert(feed);
      
      TagDao tagDao = (TagDao)ContextLoaderListener.applicationContext.getBean("tagDao");
      tag.setContentNo(feed.getContentNo());
      tagDao.insert(tag);
      out.println("<p>등록하였습니다.</p>");
      
      // FooterServlet에게 꼬리말 HTML 생성을 요청한다.
      requestDispatcher = request.getRequestDispatcher("/footer");
      requestDispatcher.include(request, response);
      
      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error");
      requestDispatcher.forward(request, response);
      return;
    }
  }
}
