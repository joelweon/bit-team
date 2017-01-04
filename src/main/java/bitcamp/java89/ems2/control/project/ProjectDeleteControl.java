package bitcamp.java89.ems2.control.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ContentDao;
import bitcamp.java89.ems2.dao.ProjectDao;
import bitcamp.java89.ems2.dao.ProjectMemberDao;
import bitcamp.java89.ems2.domain.ProjectMember;

@Controller("/project/delete.do")
public class ProjectDeleteControl implements PageController {
  @Autowired ProjectDao projectDao;
  @Autowired ProjectMemberDao projectMemberDao;
  @Autowired ContentDao contentDao;
  


  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int projectNo = Integer.parseInt(request.getParameter("projectNo"));
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));
    ProjectMember projectMember = new ProjectMember();
    
    
    if (!projectDao.exist(projectNo)) {
      throw new Exception("프로젝트를 찾지 못했습니다.");
    }
    projectMemberDao.deleteMembers(projectNo);
    projectDao.delete(projectNo);
    
    // 이건 임시 사용. 나중에 컨텐츠dao 구현 끝나면 거기로 이전
    projectDao.deleteContent(projectNo);

    // 이건 주석. 나중에 proj_memb 쪽 dao 구현 끝나면 
    // 프로젝트 번호 넘겨서 해당 쪽에서 삭제
    //Proj_MembDao proj_MembDao = (Proj_MembDao)this.getServletContext().getAttribute("proj_MembDao");
    //proj_MembDao.delete(projectNo);
    
    // 이 아래는 수희가 적은 주석. 일단 남겨놓음.
//    ContentDao contentDao = (ContentDao)this.getServletContext().getAttribute("contentDao");
//    Proj_MembDao proj_MembDao = (Proj_MembDao)this.getServletContext().getAttribute("proj_MembDao");
//    if (!contentDao.exist(projectNo) && !proj_MembDao.exist(projectNo)) {
//      proj_MembDao.delete(projectNo);
//      contentDao.delete(projectNo);
//    }
    
     
    
    return "redirect:list.do";
  }
}
