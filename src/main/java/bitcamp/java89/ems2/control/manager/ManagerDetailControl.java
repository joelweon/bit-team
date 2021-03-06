package bitcamp.java89.ems2.control.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.domain.Manager;

@Controller("/manager/detail.do")
public class ManagerDetailControl implements PageController {
  @Autowired ManagerDao managerDao;

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));
    
    Manager manager = managerDao.getOne(memberNo);
    
    if (manager == null) {
      throw new Exception("해당 아이디의 학생이 없습니다.");
    }
    
    request.setAttribute("manager", manager);
    return "/manager/detail.jsp";
  }
}
