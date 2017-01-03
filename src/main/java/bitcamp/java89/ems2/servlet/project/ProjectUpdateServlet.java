package bitcamp.java89.ems2.servlet.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.ProjectDao;
import bitcamp.java89.ems2.domain.Project;
import bitcamp.java89.ems2.listener.ContextLoaderListener;
import bitcamp.java89.ems2.util.MultipartUtil;

@WebServlet("/project/update")
public class ProjectUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    // doGet 메서드에는 업데이트 입력 폼 생성
    try {
      
      int projectNo = Integer.parseInt(request.getParameter("projectNo"));
      String projectMembers = request.getParameter("projectMember");
      
      ProjectDao projectDao = (ProjectDao)ContextLoaderListener.applicationContext.getBean("projectDao");
      Project project = projectDao.getOne(projectNo);

      String title = project.getTitle();
      String startDate = project.getStartDate();
      String endDate = project.getEndDate();
      String contents = project.getContents();
      String logoPath = project.getLogoPath();
      
      // 어디서 넘어왔는지.
      String referer = request.getHeader("Referer");
      
      response.setContentType("text/html;charset=UTF-8");

      RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
      request.setAttribute("projectMembers", projectMembers);
      request.setAttribute("project", project);
      request.setAttribute("refere", referer);
      rd.include(request, response);
      
      
    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
    
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // doPost 메서드에는 dao로 업데이트명령을 호출.
    
    try {
      Map<String,String> dataMap = MultipartUtil.parse(request);
      
      Project project = new Project();
      project.setProjectNo(Integer.parseInt(dataMap.get("projectNo")));
      project.setContentNo(Integer.parseInt(dataMap.get("projectNo")));
      project.setTitle(dataMap.get("projectName"));
      project.setStartDate(dataMap.get("projectStartDate"));
      project.setEndDate(dataMap.get("projectEndDate"));
      project.setContents(dataMap.get("textContents"));
      project.setLogoPath(dataMap.get("logoPath"));
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("<title>프로젝트관리-변경</title>");
      out.println("</head>");
      out.println("<body>");

      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);

      out.println("<h1>변경 결과</h1>");

      ProjectDao projectDao = (ProjectDao)ContextLoaderListener.applicationContext.getBean("projectDao");
      
      if (!projectDao.exist(project.getProjectNo())) {
        throw new Exception("해당 프로젝트가 없습니다.");
      }
      
      projectDao.update(project);

      out.println("<p>변경 하였습니다.</p>");

      rd = request.getRequestDispatcher("/footer");
      rd.include(request, response);

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }
  }
}
