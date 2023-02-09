package tobyspring.helloboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

//@RestController
public class HelloController2 implements ApplicationContextAware {


    private final HelloService helloService;
    private ApplicationContext applicationContext;
    // final("처음 정의된 상태에서 변하지 않는다; 처음 세팅된 값 이후로 값의 변경이 허용되지 않는다.") 이 붙은 자바의 필드는 (아무리 늦어도) 생성자에서의 처리가 모두 완료되어 끝나기 전까진 초기화(initialize)가 완료되어야 한다.
    // 근데 위 'private ApplicationContext applicationContext;' 필드 같은 경우는 초기화를 생성자에서 해주는 게 아닌 아래의 (빈 라이프사이클 메서드) setApplication() 에서 수행해주고 있다.
    // 즉 생성자에서의 처리가 종료된지 한참 후에 초기화가 이뤄지는 필드기 때문에, final 을 설정할 수가 없다.

    public HelloController2(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }

    // 빈 라이프사이클 메서드 setApplicationContext() 를 오버라이딩 구현
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext);
        this.applicationContext = applicationContext;
    }

}