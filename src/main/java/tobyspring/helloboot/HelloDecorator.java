package tobyspring.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service // 둘다 뭘 붙여도 상관없임. 그냥 빈등록 대상 컴포넌트로서 등록하면 됨.
@Primary // @Primary: "우선적으로 사용될 Bean 이다!" : 스프링컨테이너가 Bean들간 의존관계 연결을 해줄 때, 어떤 Bean 에 주입될 수 있는 Bean 이 2개 이상일 경우 "다른 거 말고 얘를 우선적으로 주입해줘라" 라고 하는 것.
public class HelloDecorator implements HelloService {
    // HelloService에 대한 Decorator 클래스 HelloDecorator 는
    // HelloService 인터페이스를 구현한 클래스이면서
    // 동시에 HelloService를 구현한 또 다른 오브젝트를 의존해야 한다.

    private final HelloService helloService;

    // 스프링컨테이너가 봤을 때, 여기 HelloDecorator의 생성자에서 필요로 하는 HelloService 타입 빈은 (여기 HelloDecorator 자체를 제외하곤) SimpleHelloService 빈 하나만 남으므로 그걸 주입해넣게 된다.
    public HelloDecorator(HelloService helloService) { // 여기 데코레이터 클래스 생성자에 주입될 수 있는 helloService 는 원래 2가지(SimpleHelloService, HelloDecorator 자신.) 였는데, HelloDecorator 가 HelloService 구현 클래스들 중 Primary 빈이 되면서, 남은 후보로 SimpleHelloService 빈 하나만 남게 되며 SimpleHelloService 빈이 주입되게 된다.
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return "*" + helloService.sayHello(name) + "*"; // 꾸며주기(Decorate)
    }
}
