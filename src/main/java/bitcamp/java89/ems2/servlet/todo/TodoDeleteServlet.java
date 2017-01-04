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
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/todo/delete")
public class TodoDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
 
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      int contentNo = Integer.parseInt(request.getParameter("contentNo"));
      
      response.setHeader("Refresh", "1;url=list");
      response.setContentType("text/html;charset=UTF-8");
      
			try {
				TodoDao todoDao = (TodoDao) ContextLoaderListener.applicationContext.getBean("todoDao");
				ContentDao contentDao = (ContentDao) ContextLoaderListener.applicationContext.getBean("contentDao");
				todoDao.delete(contentNo);
				contentDao.delete(contentNo);

				if (!contentDao.exist(contentNo)) {
					throw new Exception("삭제할 컨텐츠가 컨텐츠 목록에 없습니다.");
				}
			} catch (Exception e) {
				request.setAttribute("/todo/list", e);
			}
      
      RequestDispatcher rd = request.getRequestDispatcher("/todo/delete.jsp");
      rd.include(request, response);
      
    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
  }  
}