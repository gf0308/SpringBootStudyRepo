package tobyspring.hellbootbackup;

import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

//@MyAutoConfiguration
//@ConditionalMyOnClass("io.undertow.Undertow")
public class UndertowWebServerConfigTemp {

    @Bean("undertowServletWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new UndertowServletWebServerFactory();
    }

}