package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest {
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD }) // 해당 애노테이션을 다른 애노테이션의 메타애노테이션으로도 될 수 있도록 허용하려면, Target의 범위를 ANNOTATION_TYPE 까지도 포함하도록 해야 한다.
@Test
@interface UnitTest {
}



class HelloControllerTest {

    @Test
//    @FastUnitTest
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

/***
 * Dependency Injection 이란
 * 의존관계가 있는 존재(오브젝트)들을
 * 제 3의 존재('Assembler' [스프링 컨테이너(ApplicationContext)] 가
 * 런타임시에 이들 간에 관계를 맺어주는 것(주입을 해주는 것) 이다.
 */