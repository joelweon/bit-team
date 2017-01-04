package bitcamp.java89.ems2.servlet.code;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.CodeDao;
import bitcamp.java89.ems2.dao.ContentDao;
import bitcamp.java89.ems2.dao.TagDao;
import bitcamp.java89.ems2.domain.Code;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Tag;
import bitcamp.java89.ems2.listener.ContextLoaderListener;
import bitcamp.java89.ems2.util.MultipartUtil;

@WebServlet("/code/add")
public class CodeAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    try {
      Map<String,String> dataMap = MultipartUtil.parse(request);
      Code code = new Code();
      Tag tag = new Tag();
      tag.setTagName(dataMap.get("tagName"));
      code.setCode(dataMap.get("conts"));
      code.setProgLanguage(dataMap.get("pl"));
      
      response.setHeader("Refresh", "1;url=list");
      response.setContentType("text/html;charset=UTF-8");
      
      CodeDao codeDao = (CodeDao)ContextLoaderListener.applicationContext.getBean("codeDao");
      TagDao tagDao = (TagDao)ContextLoaderListener.applicationContext.getBean("tagDao");

      
      Member member = (Member)request.getSession().getAttribute("member");
      if (member != null) {
        code.setMemberNo(member.getMemberNo());
        
        ContentDao contentDao = (ContentDao)ContextLoaderListener.applicationContext.getBean("contentDao");
        contentDao.insert(code);
        tag.setContentNo(code.getContentNo());
        
        codeDao.insert(code);
        tagDao.insert(tag);
        
        RequestDispatcher rd = request.getRequestDispatcher("/code/add.jsp");
        rd.include(request, response);
      } else {
      	response.sendRedirect("../auth/login");
        //throw new Exception("로그인 후 글쓰기가 가능합니다.");
      }

    } catch (Exception e) {
      request.setAttribute("error", e);
      
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
  }
}








