package bitcamp.java89.ems2.servlet.code;

import java.io.IOException;
import java.io.PrintWriter;
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
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
  
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>코드관리-상세정보</title>");
      out.println("</head>");
      out.println("<body>");
      
      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>코드 정보</h1>");
      out.println("<form action='update' method='POST' enctype='multipart/form-data'>");
    
      CodeMysqlDao codeDao = (CodeMysqlDao)ContextLoaderListener.applicationContext.getBean("codeDao");
      TagMysqlDao tagDao = (TagMysqlDao)ContextLoaderListener.applicationContext.getBean("tagDao");
      Code code = codeDao.getOne(contentNo);
      ArrayList<Tag> tags = tagDao.getOne(contentNo);
      if (code == null) {
        throw new Exception("해당 코드가 없습니다.");
      }
      
      out.println("<table border='1'>");
      out.println("<tr><th>태그</th><td>"
          + "<input name='tagName' type='text' value='"); 
          for (Tag tag : tags) {
           out.printf("#%s ", tag.getTagName());
          }
      out.println("'>\n</td></tr>\n");
      out.printf("<tr><th>코드내용</th>"
           + "<td><textarea cols='90' rows='10' name='conts'>%s</textarea></td></tr>",
          code.getCode());
      out.println("<tr><th>프로그래밍언어</th><td>");
      out.println("  <select name='pl'>");
      out.printf("  <option value='선택안함' %s>선택안함</option>\n", "선택안함".equals(code.getProgLanguage()) ? "selected" : "");
      out.printf("    <option value='c++' %s>c++</option>\n", "c++".equals(code.getProgLanguage()) ? "selected" : "");
      out.printf("    <option value='java' %s>java</option>\n", "java".equals(code.getProgLanguage()) ? "selected" : "");
      out.printf("    <option value='cobol' %s>cobol</option>\n", "cobol".equals(code.getProgLanguage()) ? "selected" : "");
      out.printf("    <option value='c' %s>c</option>\n", "c".equals(code.getProgLanguage()) ? "selected" : "");
      out.printf("    <option value='c#' %s>c#</option>\n", "c#".equals(code.getProgLanguage()) ? "selected" : "");
      out.println("  </select>");
      out.println("</td></tr>");
      out.printf("<tr><th>등록일</th><td>"
          + "<input name='resgisterDate' type='text' value='%s'></td></tr>\n", 
          code.getRegisterDate());
      out.println("</table>");
      
      out.println("<button type='submit'>변경</button>");
      out.printf(" <a href='delete?contentNo=%s'>삭제</a>\n", code.getContentNo());
      out.printf("<input type='hidden' name='contentNo' value='%d'>\n", code.getContentNo());
      
      out.println(" <a href='list'>목록</a>");
      out.println("</form>");
      
      // FooterServlet에게 꼬리말 HTML 생성을 요청한다.
      rd = request.getRequestDispatcher("/footer");
      rd.include(request, response);
      
      out.println("</body>");
      out.println("</html>");
      
    } catch (Exception e) {
      e.printStackTrace();
//      RequestDispatcher rd = request.getRequestDispatcher("/error");
//      rd.forward(request, response);
      return;
    }
    
  }
  
  
}
