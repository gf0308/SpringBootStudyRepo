package tobyspring.helloboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
public class HelloController {
    private final HelloService helloService;
    private final ApplicationContext applicationContext;

    // 굳이 빈 라이프사이클 메서드 setApplication() 를 사용해 applicationContext 필드를 초기화 하지 않고도,
    // 생성자에서 applicationContext 필드를 초기화하도록 할 수 있다. => 이게 좀더 현대적인 스프링 방식임.
    public HelloController(HelloService helloService, ApplicationContext applicationContext) {
        this.helloService = helloService;
        this.applicationContext = applicationContext;
        System.out.println(applicationContext);
    }

    @GetMapping("/hello")
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }

}