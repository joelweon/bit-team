package bitcamp.java89.ems2.servlet.todo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.ContentDao;
import bitcamp.java89.ems2.dao.TodoDao;
import bitcamp.java89.ems2.domain.Todo;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/todo/add")
public class TodoAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
 
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      Todo todo = new Todo();
      todo.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
      todo.setProjectNo(Integer.parseInt(request.getParameter("projectNo")));
      todo.setTitle(request.getParameter("title"));
      todo.setName(request.getParameter("name"));
      todo.setEmail(request.getParameter("email"));
      todo.setSequence(Integer.parseInt((request.getParameter("sequence"))));
      todo.setTdContents(request.getParameter("tdcontents"));
      todo.setState(request.getParameter("state"));
      todo.setStateDate(request.getParameter("stateDate"));
      todo.setRegisterDate(request.getParameter("rdt"));
 
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("<title>할일관리-등록</title>");
      out.println("</head>");
      out.println("<body>");

      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);

      out.println("<h1>등록 결과</h1>");

       ContentDao contentDao = (ContentDao)ContextLoaderListener.applicationContext.getBean("contentDao");
       contentDao.insert(todo);
       TodoDao todoDao = (TodoDao)ContextLoaderListener.applicationContext.getBean("todoDao");
        todoDao.insert(todo);
      out.println("<p>등록하였습니다.</p>");

      // FooterServlet에게 꼬리말 HTML 생성을 요청한다.
      rd = request.getRequestDispatcher("/footer");
      rd.include(request, response);

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
  }
}