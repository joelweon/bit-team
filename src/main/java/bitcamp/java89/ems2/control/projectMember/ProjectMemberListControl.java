package bitcamp.java89.ems2.control.projectMember;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ProjectMemberDao;
import bitcamp.java89.ems2.domain.ProjectMember;

@Component("/projectMember/list.do")
public class ProjectMemberListControl implements PageController {
  @Autowired ProjectMemberDao projectMemberDao;
  
  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ArrayList<ProjectMember> list = projectMemberDao.getList();
    request.setAttribute("projectMembers", list);
    request.setAttribute("title", "프로젝트멤버관리-목록");
    request.setAttribute("contentPage", "/projectMember/list.jsp");
    
    return "/main.jsp";
  }
}
