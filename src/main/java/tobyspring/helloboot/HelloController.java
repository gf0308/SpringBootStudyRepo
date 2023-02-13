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
        if(name == null || name.trim().length() == 0) throw new IllegalArgumentException();
        // 'IllegalArgumentException' : 파라미터로 illegal 하거나 적절치않은 argument를 받았을 때 주로 발생시키는 Exception (500 대 에러)
        // 근데 실제로 이런 클라이언트로부터 넘어온 파라미터가 잘못된(illegal한) 케이스일 경우를 체크할 때는 이에 대한 적절한 Exception은 사실 400대 에러다.
        // 500대 에러는 서버에 아예 기능이 없거나, 로직이 있는데 버그가 있거나 해서 발생하는 심각한 수준의 서버 내부 에러일 때 발생시키는게 적절하다.

        return helloService.sayHello(name);
//        return helloService.sayHello(Objects.requireNonNull(name)); // Objects.requireNonNull(obj) : obj 가 null 일 경우 'NullPointerException' 을 던짐. null 이 아니면 그 obj값을 리턴함
    }

}