package tobyspring.helloboot;


import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication4 {

    public static void main(String[] args) {
        // 스프링 컨테이너를 대표하는 인터페이스: 'ApplicationContext' => 이 애플리케이션 컨텍스트가 '스프링컨테이너'가 된다.
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh(); // 스프링컨테이너 자기가 가지고 있는 구성정보를 이용해서 초기화하는 작업(bean들을 생성해주고 등록해주고 하는 것) : .refresh()

        ServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        WebServer webServer = tomcatServletWebServerFactory.getWebServer(servletContext -> {
            servletContext.addServlet("frontcontroller", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 프론트 컨트롤러로서 맡아서 처리해야 할 (모든 서블릿마다 공통적으로 필요로 할) 공통적인 처리들(ex: 인증, 보안, 다국어 처리, 공통 기능)
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");

                        HelloController helloController = applicationContext.getBean(HelloController.class);
                        String ret = helloController.hello(name); // 바인딩

                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println(ret);
                    }
                    else if (req.getRequestURI().equals("/user")) {
                        //
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }


                }
            }).addMapping("/*");  // "/*", 즉 슬래쉬 이하 들어오는 모든 요청들을 다 처리하겠다는, 즉 프론트컨트롤러가 되겠다는 것

        });

        webServer.start();
        // 위와 같이 내장톰캣을 직접 만들어서, 즉 서블릿 컨테이너를 만들어 띄웠으니, 이 안에 넣어줄 서블릿을 만들어 넣을 것이다.


    }

}

// 서블릿이란 것은 자바의 표준 기술(즉, 표준 명세가 있다)이고 이 표준 기술을 구현한 컨테이너 제품들이 많이 나와 있다.
// 그 중 대표적인 서블릿 컨테이너 제품이 '톰캣'이다

// 내장형 톰캣이란 라이브러리도 제공해주는게 있다
