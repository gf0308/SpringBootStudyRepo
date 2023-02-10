package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloControllerTest {

    @Test
    void helloController() {
        HelloController helloController = new HelloController((HelloService) name -> name);

        String ret = helloController.hello("Test");

        Assertions.assertThat(ret).isEqualTo("Test");
    }

    @Test
    void failsHelloController() {
        HelloController helloController = new HelloController((HelloService) name -> name);
        Assertions.assertThatThrownBy(() -> {
            // 아마 예외가 터질것 같은 코드
            helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello("     ");
        }).isInstanceOf(IllegalArgumentException.class);
    }
    // 테스트 성공.
    // 이렇게 한 결과 HelloController 클래스에 대해 '고립된' 총 3개의 테스트를 수행해보았다.
    // 이런 테스트를 => '유닛 테스트(단위 테스트)' 라고 한다.

}

/***ㄹ
 * Dependency Injection 이란
 * 의존관계가 있는 존재(오브젝트)들을
 * 제 3의 존재('Assembler' [스프링 컨테이너(ApplicationContext)] 가
 * 런타임시에 이들 간에 관계를 맺어주는 것(주입을 해주는 것) 이다.
 */