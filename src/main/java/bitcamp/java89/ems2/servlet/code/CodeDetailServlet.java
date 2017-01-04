package bitcamp.java89.ems2.servlet.code;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.impl.CodeMysqlDao;
import bitcamp.java89.ems2.dao.impl.TagMysqlDao;
import bitcamp.java89.ems2.domain.Code;
import bitcamp.java89.ems2.domain.Tag;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/code/detail")
public class CodeDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      int contentNo = Integer.parseInt(request.getParameter("contentNo"));

      
      CodeMysqlDao codeDao = (CodeMysqlDao)ContextLoaderListener.applicationContext.getBean("codeDao");
      TagMysqlDao tagDao = (TagMysqlDao)ContextLoaderListener.applicationContext.getBean("tagDao");
      Code code = codeDao.getOne(contentNo);
      ArrayList<Tag> tags = tagDao.getOne(contentNo);

      if (code == null) {
        throw new Exception("해당 코드가 없습니다.");
      }
      
      response.setContentType("text/html;charset=UTF-8");
      
      request.setAttribute("tags", tags);
      request.setAttribute("code", code);
      
      RequestDispatcher rd = request.getRequestDispatcher("/code/detail.jsp");
      rd.include(request, response);
          
      
    } catch (Exception e) {
      e.printStackTrace();
//      RequestDispatcher rd = request.getRequestDispatcher("/error");
//      rd.forward(request, response);
      return;
    }
    
  }
  
  
}
