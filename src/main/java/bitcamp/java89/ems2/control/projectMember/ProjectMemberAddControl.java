package bitcamp.java89.ems2.control.projectMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ProjectMemberDao;
import bitcamp.java89.ems2.domain.ProjectMember;

@Component("/projectMember/add.do")
public class ProjectMemberAddControl implements PageController {
  @Autowired ProjectMemberDao projectMemberDao;
  
  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ProjectMember projectMember = new ProjectMember();
    int projectNo = Integer.parseInt(request.getParameter("projectNo"));
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));
    projectMember.setRol(request.getParameter("role"));
    projectMember.setProjectNo(projectNo);
    projectMember.setMemberNo(memberNo);
//    System.out.println("role:" + request.getParameter("role") + ", projectNo:"
//        + Integer.parseInt(request.getParameter("projectNo")) + ", memberNo: "
//        + Integer.parseInt(request.getParameter("memberNo")));
    
    if (projectMemberDao.getOne(projectNo, memberNo) != null) {
      throw new Exception("이미 존재하는 멤버입니다.");
    }
    projectMemberDao.insert(projectMember);

    return "redirect:list.do";
  }
}
