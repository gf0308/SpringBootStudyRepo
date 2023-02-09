package tobyspring.helloboot;


import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication3 {

    public static void main(String[] args) {
        /**
         * 프론트컨트롤러로 만든 소스; 이제 스프링컨테이너를 사용하는 방식으로 개선하기 전에 원본보존용으로 백업해놓은 코드임
         */
        // 스프링부트에서 내장 톰캣을 직접 만들어 띄우는 방법 : TomcatServletWebServerFactory 객체 생성해서 WebServer 인스턴스를 얻고, WebServer인스턴스.start() 를 실행하는 방법
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        // 인텔리제이 단축키: '익명클래스를 람다식으로 전환' : alt + enter
        WebServer webServer = tomcatServletWebServerFactory.getWebServer(servletContext -> {
//            HelloController helloController = new HelloController(helloService);

            servletContext.addServlet("frontcontroller", new HttpServlet() {
                // 인텔리제이 단축키: '클래스 선언문 내에서 override해서 쓸 수 있는 후보대상 메서드군들 살펴보는 단축키' : ctrl + o
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 프론트 컨트롤러로서 맡아서 처리해야 할 (모든 서블릿마다 공통적으로 필요로 할) 공통적인 처리들(ex: 인증, 보안, 다국어 처리, 공통 기능)

                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");
//                        String ret = helloController.hello(name); // 바인딩

                        // 상태코드
                        resp.setStatus(HttpStatus.OK.value()); // 만약 이 코드가 정상적으로 처리가 이뤄지는 경우라면 사실 "resp.setStatus(HttpStatus.OK.value())" 처리는 필요가 없는데, 왜냐하면 여기서 특별히 에러를 내지 않으면 서블릿에서 자동으로 200 ok 값을 남겨주기 때문이다. 하지만 일단 명시적으로 남겨두도록 하겠다.
                        // Headers 내용: 그중에서 특히 바디의 내용이 어떤 타입인지: 즉 contentType 만들기
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                        // Body 부분 만들기
//                        resp.getWriter().println(ret);
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
