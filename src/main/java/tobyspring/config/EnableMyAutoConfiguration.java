package tobyspring.config;

import org.springframework.context.annotation.Import;
import tobyspring.hellbootbackup.Config;
import tobyspring.config.autoconfig.DispatcherServletConfig;
import tobyspring.config.autoconfig.TomcatWebServerConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({ Config.class, TomcatWebServerConfig.class, DispatcherServletConfig.class }) // @Import 애노테이션을 @EnableMyAutoConfiguration 의 메타 애노테이션으로 적용했다. @Import의 타깃은 "TYPE"인데도 이 @Import를 적용할 수 있었던 이유는, "애노테이션'이란 것 역시 시스템에선 인터페이스의 일종"이라고 간주하기 떄문이다('@interface' 란 키워드에서 알 수 있듯)
public @interface EnableMyAutoConfiguration {
}
