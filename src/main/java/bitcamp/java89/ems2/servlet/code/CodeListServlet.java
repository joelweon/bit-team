package bitcamp.java89.ems2.servlet.code;

import java.io.IOException;
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
import bitcamp.java89.ems2.domain.Member;
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
			CodeDao codeDao = (CodeDao) ContextLoaderListener.applicationContext.getBean("codeDao");
			TagDao tagDao = (TagDao) ContextLoaderListener.applicationContext.getBean("tagDao");

			Member member = (Member) request.getSession().getAttribute("member");
			if (member != null) {
				ArrayList<Code> list = codeDao.getList();
				for (Code code : list) {
					code.setTagList(tagDao.getOne(code.getContentNo()));
				}

				request.setAttribute("codes", list);
				RequestDispatcher rd = request.getRequestDispatcher("/code/list.jsp");
				rd.include(request, response);
			} else {
				response.sendRedirect("../auth/login");
			}

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
  }
}