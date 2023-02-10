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

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(String name) {
        if(name == null || name.trim().length() == 0) throw new IllegalArgumentException(); // 'IllegalArgumentException' : 파라미터로 illegal 하거나 적절치않은 argument를 받았을 때 주로 발생시키는 Exception (500 대 에러임;  )

        return helloService.sayHello(name);
//        return helloService.sayHello(Objects.requireNonNull(name)); // Objects.requireNonNull(obj) : obj 가 null 일 경우 'NullPointerException' 을 던짐. null 이 아니면 그 obj값을 리턴함
    }

}