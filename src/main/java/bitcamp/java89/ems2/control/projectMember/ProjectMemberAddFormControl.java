package bitcamp.java89.ems2.control.projectMember;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ProjectDao;
import bitcamp.java89.ems2.dao.ProjectMemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.domain.Project;
import bitcamp.java89.ems2.domain.ProjectMember;
import bitcamp.java89.ems2.domain.Student;


@Component("/projectMember/addForm.do")
public class ProjectMemberAddFormControl implements PageController {
  @Autowired ProjectMemberDao projectMemberDao;
  @Autowired StudentDao studentDao;
  @Autowired ProjectDao projectDao;
  
  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
    ProjectMember projectMember = new ProjectMember();
    projectMember.setRol(request.getParameter("rol"));
    
    ArrayList<Student> studentList = studentDao.getList();
    ArrayList<Project> projectList = projectDao.getList();
    
    request.setAttribute("projectList", projectList);
    request.setAttribute("studentList", studentList);
    request.setAttribute("title", "프로젝트 멤버 등록");
    request.setAttribute("contentPage", "/projectMember/addForm.jsp");
    
    return "/main.jsp";
  }
}
