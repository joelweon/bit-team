package bitcamp.java89.ems2.servlet.code;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.CodeDao;
import bitcamp.java89.ems2.dao.impl.TagMysqlDao;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/code/delete")
public class CodeDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      int contentNo = Integer.parseInt(request.getParameter("contentNo"));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
  
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("<title>코드관리-삭제</title>");
      out.println("</head>");
      out.println("<body>");
      
      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>삭제 결과</h1>");
      CodeDao codeDao = (CodeDao)ContextLoaderListener.applicationContext.getBean("codeDao");
      TagMysqlDao tagDao = (TagMysqlDao)ContextLoaderListener.applicationContext.getBean("tagDao");
      
      tagDao.delete(contentNo);
      codeDao.delete(contentNo);

      
      out.println("<p>삭제하였습니다.</p>");
    
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
