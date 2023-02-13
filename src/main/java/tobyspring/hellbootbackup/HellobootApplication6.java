package tobyspring.hellbootbackup;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


//@Configuration
////@ComponentScan
public class HellobootApplication6 {

    // 팩토리 메서드 방식으로 빈 생성기 메서드를 만들어 빈 등록을 하도록 한다.
    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }
    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();  // DispatcherServlet 생성 시 스프링컨테이너를 생성자에 투입해줘야 함
    }

    public static void main(String[] args) {

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

//                ServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
                ServletWebServerFactory tomcatServletWebServerFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
//                dispatcherServlet.setApplicationContext(this); // DispatcherServlet에 스프링컨테이너를 주입해넣음.

                WebServer webServer = tomcatServletWebServerFactory.getWebServer(
                        servletContext -> {
                            servletContext.addServlet("dispatcherServlet", dispatcherServlet)
                                            .addMapping("/*");
                        }
                );
                webServer.start();
            }
        };
        applicationContext.register(HellobootApplication6.class);
        applicationContext.refresh();

    }// main

}