package bitcamp.java89.ems2.control;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.annotation.RequestMapping;
import bitcamp.java89.ems2.dao.ProjectDao;
import bitcamp.java89.ems2.dao.ProjectMemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.domain.Project;
import bitcamp.java89.ems2.domain.ProjectMember;
import bitcamp.java89.ems2.domain.Student;

@Controller
public class ProjectMemberController {
  @Autowired ProjectMemberDao projectMemberDao;
  @Autowired StudentDao studentDao;
  @Autowired ProjectDao projectDao;

  @RequestMapping("/projectMember/list.do")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ArrayList<ProjectMember> list = projectMemberDao.getList();

    request.setAttribute("projectMembers", list);
    request.setAttribute("title", "프로젝트멤버관리-목록");
    request.setAttribute("contentPage", "/projectMember/list.jsp");

    return "/main.jsp";
  }

  @RequestMapping("/projectMember/addForm.do")
  public String addForm(HttpServletRequest request, HttpServletResponse response) 
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

  @RequestMapping("/projectMember/add.do")
  public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ProjectMember projectMember = new ProjectMember();
    int projectNo = Integer.parseInt(request.getParameter("projectNo"));
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));
    projectMember.setRol(request.getParameter("role"));
    projectMember.setProjectNo(projectNo);
    projectMember.setMemberNo(memberNo);

    if (projectMemberDao.getOne(projectNo, memberNo) != null) {
      throw new Exception("이미 존재하는 멤버입니다.");
    }
    projectMemberDao.insert(projectMember);

    return "redirect:list.do";
  }
}
