package tobyspring.hellbootbackup;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 애노테이션이 어느 시점까지 살아있을지 (ex: '런타임 동안 살아있게 한다' => 'RetentionPolicy.RUNTIME')
@Target(ElementType.TYPE) // 애노테이션을 적용할 대상을 지정 (ex: 클래스에 붙는 애노테이션인지, 아니면 다른 것에 붙는 애노테이션인건지. 등) / ElementType.TYPE : 클래스나 인터페이스같은 '타입'에 붙는 것; 즉 클래스 or 인터페이스에 붙는거면 'ElementType.TYPE' 임
@Component // 메타 애노테이션 으로서 애노테이션 @Component를 넣음.
public @interface MyComponent {
}
