package bitcamp.java89.ems2.servlet.projmemb;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ProjectMemberDao;
import bitcamp.java89.ems2.domain.ProjectMember;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@Controller("/projectMember/list.do")
public class ProjectMemberListServlet implements PageController {
  @Autowired ProjectMemberDao projectMemberDao;
  
  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ArrayList<ProjectMember> list = projectMemberDao.getList();
    
    RequestDispatcher rd = request.getRequestDispatcher("/projectmember/list.jsp");
    request.setAttribute("projectMembers", list);
    request.setAttribute("title", "프로젝트멤버관리-목록");
    request.setAttribute("projectMembers", list);
    
    
  }
}
