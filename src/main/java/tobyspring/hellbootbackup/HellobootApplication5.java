package tobyspring.hellbootbackup;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


@Configuration // "이 클래스는 : 스프링 컨테이너가 사용할 Bean을 생성해 리턴하는 메서드를 가진(팩토리 메서드를 가진) 클래스다" (그것도 구성(configuration)용 빈들을 생성해 토해내는 메서드를 가진 클래스다) : @Configuration
public class HellobootApplication5 {
/*
    @Bean // 스프링컨테이너의 Bean으로 사용될 것이다.
    public HelloController helloController(HelloService helloService) {
        return new HelloController(helloService);
    }

    @Bean
    public HelloService helloService() {
        return new SimpleHelloService();
    }

    public static void main(String[] args) {
        // 스프링 컨테이너 만들기 작업
        //  스프링 컨테이너 ApplicationContext를 생성하는데, DispatcherServlet이 이용할 수 있도록 웹기술과 관련된 타입인 GenericWebApplicatioNContext 타입으로 컨테이너를 생성한다.
        // 스프링컨테이너가 생성되고 구동될 때 서블릿컨테이너 생성 및 구동도 이뤄지도록 하는 구조로 변경한다.
//        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
                WebServer webServer = tomcatServletWebServerFactory.getWebServer(
                        servletContext -> {
                            servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this))
                                            .addMapping("/*");
                        }
                );
                webServer.start(); // 웹컨테이너 Tomcat을 구동한다.
            }
        };
        applicationContext.register(HellobootApplication.class); // (스프링컨테이너의 Bean들간의 의존관계를 구성해 정의하고 있는) 구성정보 클래스(@Configuration 클래스)를 스프링컨테이너에 주입한다.
        applicationContext.refresh();
    }

*/
}