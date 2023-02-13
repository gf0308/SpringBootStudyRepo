package tobyspring.helloboot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 커스텀 애노테이션을 만들 때 Retention 을 'Runtime'으로 설정해주는 이유
// : 원래 자바 애노테이션의 Retention의 디폴트값은 'CLASS' 다
//   즉 이 말은 애노테이션 정보가 '컴파일된 클래스 파일'까진 살아있지만, 애노테이션이 달린 클래스가 런타임의 메모리로 로딩될 때는 그 정보가 사라진다.
//   런타임의 메모리로 로딩했을 때도 애노테이션 정보가 계속 살아있어야 다른 스프링의 애노테이션들처럼 활용할 수가 있다.
//   때문에 커스텀 애노테이션을 만들 때 Retention을 'RUNTIME' 으로 설정해주는 것 이다.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // 애노테이션 적용대상(Target)을 "클래스, 인터페이스, 열거형(Enum)" 으로 할 때는 'ElementType.TYPE' 으로 주면 된다.
@Configuration
@ComponentScan
public @interface MySpringBootAnnotation {
}
