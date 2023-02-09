package tobyspring.helloboot;


import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication2 {

    public static void main(String[] args) {
        /**
         * 아래에 기본 예제로 만든 서블릿인 'hello'서블릿 코드 예제를 보전하기 위해 복사본으로 만들어놓은 클래스 소스
         */
        // 스프링부트에서 내장 톰캣을 직접 만들어 띄우는 방법 : TomcatServletWebServerFactory 객체 생성해서 WebServer 인스턴스를 얻고, WebServer인스턴스.start() 를 실행하는 방법
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        // 인텔리제이 단축키: '익명클래스를 람다식으로 전환' : alt + enter
        WebServer webServer = tomcatServletWebServerFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new HttpServlet() {
                // 인텔리제이 단축키: '클래스 선언문 내에서 override해서 쓸 수 있는 후보대상 메서드군들 살펴보는 단축키' : ctrl + o
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    String name = req.getParameter("name");

                    // 상태코드
                    resp.setStatus(HttpStatus.OK.value());
                    // Headers 내용: 그중에서 특히 바디의 내용이 어떤 타입인지: 즉 contentType 만들기
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE); // 404 메시지 내용 리턴
                    // Body 부분 만들기
                    resp.getWriter().println("Hello " + name);
                }
            }).addMapping("/hello");

        });



        webServer.start();

        // 위와 같이 내장톰캣을 직접 만들어서, 즉 서블릿 컨테이너를 만들어 띄웠으니, 이 안에 넣어줄 서블릿을 만들어 넣을 것이다.


    }

}

// 서블릿이란 것은 자바의 표준 기술(즉, 표준 명세가 있다)이고 이 표준 기술을 구현한 컨테이너 제품들이 많이 나와 있다.
// 그 중 대표적인 서블릿 컨테이너 제품이 '톰캣'이다

// 내장형 톰캣이란 라이브러리도 제공해주는게 있다
