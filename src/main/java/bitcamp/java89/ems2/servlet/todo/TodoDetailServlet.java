package bitcamp.java89.ems2.servlet.todo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.TodoDao;
import bitcamp.java89.ems2.domain.Todo;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/todo/detail")
public class TodoDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      int contentNo = Integer.parseInt(request.getParameter("tdno"));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
   
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>"); 
      out.println("<meta charset='UTF-8'>");   
      out.println("<title>할일관리-상세정보</title>");
      out.println("</head>");
      out.println("<body>");    
      
      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>할일 정보</h1>");
      out.println("<form action='update' method='POST'>");
    
      TodoDao todoDao = (TodoDao)ContextLoaderListener.applicationContext.getBean("todoDao");
      Todo todo = todoDao.getOne(contentNo);
      
      if (todo == null) {
        throw new Exception("해당 할일이 없습니다.");
      }
      
      out.println("<table border='1'>");
      out.println("<tr><th>회원번호</th><td>");
      out.printf("<input name='memberNo' type='number' value='%s'></td></tr>\n",
          todo.getMemberNo());
      out.println("<tr><th><프로젝트명></th><td style ='width: 400px'>");
      out.printf("<input name='titl' type='text' value='%s'></td></tr>\n", 
          todo.getTitle());
      out.printf("<tr><th>이름</th><td style ='width: 400px'>"
          + "<input name='name' type='text' value='%s'></td></tr>\n",
          todo.getName());
      out.printf("<tr><th>전화</th><td style ='width: 400px'>"
          + "<input name='tel' type='text' value='%s'></td></tr>\n", 
          todo.getTel());
      out.printf("<tr><th>이메일</th><td style ='width: 400px'>"
          + "<input name='email' type='text' value='%s'></td></tr>\n", 
          todo.getEmail());
      out.println("<tr><th>순서</th><td colspan='5'>");
      out.printf("<input name='sequence' type='number' value='1'></td></tr>\n",
          todo.getSequence());
      out.println("<tr><th>할일내용</th><td colspan='5'>");
      //out.println("<div style='margin: 10px; background-color: white; height: 300px; width: 400px'>");
      out.printf("<input name='conts' type='text' style='margin: 10px; background-color: white; height: 300px; width: 400px' value='%s'></td></tr>\n",
      todo.getTdContents());
     /* out.printf("<textarea rows='17' cols='102' style='margin: 20px; resize: none; border-radius: 5px;'>"
          + "<input name='tdconts' type='text' value='%s'></td></tr>\n",
          todo.getTdContents());
      out.println("</textarea>");*/
                 // out.println("</div>");
      out.printf("<tr><th>등록일</th><td>"
          + "<input name='rdt' type='datetime' value='%s'></td></tr>\n", 
          todo.getRegisterDate());
      
      out.println("  <tr><th>상태</th><td colspan='5'>");
      out.println("    <select name='state'> ");
      out.println("    <option value='검토중'>검토중</option>");
      out.println("    <option value='계획중'>계획중</option>");
      out.println("     <option value='진행중'>진행중</option>");
      out.println("     <option value='진행완료'>진행완료</option>");
      out.println("  </select> </td>");
      
      out.printf("<tr><th>조회수</th><td>"
          + "<input name='vw_cnt' type='text' value='%s'></td></tr>\n", 
          todo.getViewCount());

      out.println("</table>");
      out.println("<button type='submit'>변경</button>");
      out.printf(" <a href='delete?contentNo=%d'>삭제</a>\n", todo.getContentNo());
      out.printf("<input type='hidden' name='contentNo' value='%d'>\n", todo.getContentNo());
      
      out.println(" <a href='list'>목록</a>");
      out.println("</form>");
      
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
