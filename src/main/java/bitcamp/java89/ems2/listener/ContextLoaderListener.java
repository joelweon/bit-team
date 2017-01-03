

package bitcamp.java89.ems2.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

//@WebListener  <--- 이 예제에서는 애노테이션 대신 web.xml에 리스너를 등록하였다.
public class ContextLoaderListener implements ServletContextListener {
  public static ApplicationContext applicationContext;
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    try {
      applicationContext = new FileSystemXmlApplicationContext(
          "file:" + sce.getServletContext().getRealPath("/WEB-INF/conf/application-context.xml"));
      
      System.out.println("ContextLoaderListener.init() 실행 완료!");
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }

}
