package tobyspring.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//@Import({ Config.class, TomcatWebServerConfig.class, DispatcherServletConfig.class }) // @Import 애노테이션을 @EnableMyAutoConfiguration 의 메타 애노테이션으로 적용했다. @Import의 타깃은 "TYPE"인데도 이 @Import를 적용할 수 있었던 이유는, "애노테이션'이란 것 역시 시스템에선 인터페이스의 일종"이라고 간주하기 떄문이다('@interface' 란 키워드에서 알 수 있듯)
@Import(MyAutoConfigImportSelector.class)
// @Import() 을 사용하면, 실행 시에 애노테이션 메타데이터(AnnotationMetadata: 애노테이션에 인자로 넘겨준 정보)를 'ImportSelector' 오브젝트가 받고 이를 해석해 스프링컨테이너에게 임포트대상클래스를 문자열배열로 리턴해준다. ex) String[] selectImports(AnnotationMetadata importingClassMetadata);
// 또한 Import 대상을 일일이 나열해주는게 아닌, 위처럼 따로 별도의 클래스를 만들고 임포트할 클래스는 그 안에 다 기술해주고서 그 클래스를 읽어 내용을 가져오도록 @Import를 사용할 수도 있다.
public @interface EnableMyAutoConfiguration {
}
