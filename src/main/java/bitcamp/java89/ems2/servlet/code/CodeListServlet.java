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

import bitcamp.java89.ems2.dao.CodeDao;
import bitcamp.java89.ems2.dao.TagDao;
import bitcamp.java89.ems2.domain.Code;
import bitcamp.java89.ems2.domain.Tag;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/code/list")
public class CodeListServlet extends HttpServlet {
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
      out.println("<title>코드관리-목록</title>");
      out.println("</head>");
      out.println("<body>");

      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);

      out.println("<h1>코드 정보</h1>");

      CodeDao codeDao = (CodeDao)ContextLoaderListener.applicationContext.getBean("codeDao");
      TagDao tagDao = (TagDao)ContextLoaderListener.applicationContext.getBean("tagDao");

      ArrayList<Code> list = codeDao.getList();

      out.println("<form action='tag' method='GET'");
      out.println("<div class='tag'>");
      out.println("<input type='text' name='tagname' class='tagname' placeholder='태그를 입력하세요' />");
      out.println("<button type='submit' class='button_tag'>검색</button>");
      out.println("</form>");

      out.println("<a href='form.html'>추가</a><br>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("  <th>콘텐츠번호</th>");
      out.println("  <th>등록일</th>");
      out.println("  <th>조회수</th>");
      out.println("  <th>프로그래밍언어</th>");
      out.println("  <th>태그</th>");
      out.println("</tr>");
      
      ArrayList<Tag> tags;

      for (Code code : list) {
        tags = tagDao.getOne(code.getContentNo());
        out.println("<tr> ");
        out.printf("  <td><a href='detail?contentNo=%1$d'>%d</a></td>"
            + "<td>%s</td>"
            + "<td>%s</td>"
            + "<td>%s</td>\n",
            code.getContentNo(),
            code.getRegisterDate(),
            code.getViewCount(),
            code.getProgLanguage());
        out.println("<td>");
        for (Tag tag : tags) {
          out.printf("#%s ", tag.getTagName());
        }
        out.println("</td>\n");
        out.println("</tr>");
      }

      out.println("</table>");

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