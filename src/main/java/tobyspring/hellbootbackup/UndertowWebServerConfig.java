package tobyspring.hellbootbackup;

/**
 * 내 테스트 스프링부트플젝의 사용 웹컨테이너를
 * Undertow로 사용하도록 설정하기
 * -> 만약 시스템 내 Undertow 가 있으면 Undertow를 사용해 웹컨테이너를 띄우도록 조건부 빈 설정을 만들고 적용하기
 * */

import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

//@MyAutoConfiguration
//@ConditionalMyUndertow(value = "io.undertow.Undertow")
public class UndertowWebServerConfig {

    @Bean("undertowServletWebServerFactory")
    public ServletWebServerFactory undertowServletWebServerFactory() {
        return new UndertowServletWebServerFactory();
    }

}


/**
 * 시스템에 Undertow 가 존재할 경우 이를 등록가능빈으로 스프링컨테이너에게 알려줄 Condition 인터페이스 구현 클래스 생성 : MyConditionClassForUndertow
 *
 * 위 기능을 달고 다닐 운반자가 될 전용 애노테이션 만들기 : @ConditionalMyUndertow
 *
 * 위 애노테이션을 적용하여 조건부 빈 등록 처리시 수행내용을 갖고 있을 Configuration 역할 클래스 작성 : UndertowWebServerConfig
 *
 * */