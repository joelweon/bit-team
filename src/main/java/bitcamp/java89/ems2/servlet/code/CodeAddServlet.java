package bitcamp.java89.ems2.servlet.code;

import java.io.IOException;
import java.io.PrintWriter;
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
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("<title>코드관리-등록</title>");
      out.println("</head>");
      out.println("<body>");
      
      // HeaderServlet에게 머리말 HTML 생성을 요청한다.
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>등록 결과</h1>");
    
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
      } else {
        throw new Exception("로그인 후 글쓰기가 가능합니다.");
      }
      

//      if (!contentDao.exist(code.getContentNo())) { // 콘텐트에 코드가 없다면
//        
//      } else { // 콘텐트 기등록 사용자는 기존 회원번호를 사용한다. 
//        Content content = contentDao.getOne(code.getContentNo()); 
//        code.setContentNo(content.getContentNo());
//      }
        
      
      out.println("<p>등록하였습니다.</p>");
      
      // FooterServlet에게 꼬리말 HTML 생성을 요청한다.
      rd = request.getRequestDispatcher("/footer");
      rd.include(request, response);
      
      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      // 오류 정보를 ServletRequest에 담는다.
      request.setAttribute("error", e);
      
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
  }
}








