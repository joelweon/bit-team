package bitcamp.java89.ems2.control.project;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ProjectDao;
import bitcamp.java89.ems2.domain.Project;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller("/project/update.do")
public class ProjectUpdateControl implements PageController {
  @Autowired ProjectDao projectDao;


  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String,String> dataMap = MultipartUtil.parse(request);
    
    Project project = new Project();
    project.setProjectNo(Integer.parseInt(dataMap.get("projectNo")));
    project.setContentNo(Integer.parseInt(dataMap.get("projectNo")));
    project.setTitle(dataMap.get("projectName"));
    project.setStartDate(dataMap.get("projectStartDate"));
    project.setEndDate(dataMap.get("projectEndDate"));
    project.setContents(dataMap.get("textContents"));
    project.setLogoPath(dataMap.get("logoPath"));
    
    if (!projectDao.exist(project.getProjectNo())) {
      throw new Exception("해당 프로젝트가 없습니다.");
    }
    
    projectDao.update(project);
    
    return "redirect:list.do";
  }
}
