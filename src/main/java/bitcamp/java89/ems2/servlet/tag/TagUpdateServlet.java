//package bitcamp.java89.ems2.servlet.tag;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import bitcamp.java89.ems2.dao.ContentDao;
//import bitcamp.java89.ems2.dao.TagDao;
//import bitcamp.java89.ems2.domain.Tag;
//
//@WebServlet("/tag/update")
//public class TagUpdateServlet extends HttpServlet {
//  private static final long serialVersionUID = 1L;
//
//  @Override
//  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
//      throws ServletException, IOException {
//
//    try {
//      Tag tag = new Tag();
//      tag.setTagNo(Integer.parseInt(request.getParameter("tagNo")));
//      tag.setContentNo(Integer.parseInt(request.getParameter("contentNo")));
//      tag.setTagName(request.getParameter("tagName"));
//      tag.setRegisterDate(request.getParameter("registerDate"));
//      tag.setViewCount(Integer.parseInt(request.getParameter("viewcount")));
//     
//      response.setContentType("text/html;charset=UTF-8");
//      PrintWriter out = response.getWriter();
//
//      out.println("<!DOCTYPE html>");
//      out.println("<html>");
//      out.println("<head>");
//      out.println("<meta charset='UTF-8'>");
//      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
//      out.println("<title>학생관리-변경</title>");
//      out.println("</head>");
//      out.println("<body>");
//      
//      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/header"); 
//      requestDispatcher.include(request, response);
//      
//      out.println("<h1>변경 결과</h1>");
//
//      TagDao tagDao = (TagDao)this.getServletContext().getAttribute("tagDao"); 
//
//      if (!tagDao.exist(tag.ContentNo())) {
//        throw new Exception("사용자를 찾지 못했습니다.");
//      }
//
//      ContentDao contentDao = (ContentDao)this.getServletContext().getAttribute("contentDao"); 
//      contentDao.update(tag);
//      tagDao.update(tag);
//      out.println("<p>변경 하였습니다.</p>");   
//      
//      requestDispatcher = request.getRequestDispatcher("/footer"); 
//      requestDispatcher.include(request, response);
//      
//      out.println("</body>");
//      out.println("</html>");
//
//    } catch (Exception e) {
//      request.setAttribute("error", e);
//      
//      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error"); 
//      requestDispatcher.forward(request, response);
//      return; 
//    }
//  }
//}