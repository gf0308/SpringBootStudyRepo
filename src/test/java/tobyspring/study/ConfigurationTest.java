package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTest {
//    어떤 기술을 정확하게 이해하고 싶을 때 테스트 코드를 학습목적으로 만들어서 사용할 수 있다.
//    새로운 기술을 익히고 나서 내가 아는대로 정말 그렇게 작동하는지 확인하면서 이해를 더 깊이 할 수 있는 기회가 될 수 있다.

    @Test
    void configuration() {
//        Common common = new Common();
//        Assertions.assertThat(common).isSameAs(common); // isSameAs : (메모리)주소값이 똑같은 오브젝트인가?

//        MyConfig myConfig = new MyConfig();
//        Bean1 bean1 = myConfig.bean1();
//        Bean2 bean2 = myConfig.bean2();

//        assertThat(bean1.common).isSameAs(bean2.common); // 다른 값이라고 나온다.

//    근데 신기한건, MyConfig란 클래스를 스프링컨테이너의 구성정보로 사용하게 되면, 이 동작방식이 달라진다.

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        assertThat(bean1.common).isSameAs(bean2.common); //  여기서 bean1 과 bean2 는 같다고 나온다 => 싱글톤일 수가 없는데 마법같이 싱글톤으로 작동할 수 있게 해줌 : 스프링컨테이너 안에서 MyConfig 를 대상으로 프록시 오브젝트를 하나 만들고 이걸 이용해 Common 객체를 마치 캐싱해서 이용하듯 활용되도록 하고 있기 때문에, 이런 마법같은 일이 일어나게 함
        System.out.println("bean1.common : " + bean1.common);
        System.out.println("bean2.common : " + bean2.common);
    }


    // Bean1 <-- Common
    // Bean2 <-- Common

    // 팩토리 메서드 패턴으로 빈을 생성해서 스프링 컨테이너에 등록해서 쓰는 건
    // 사실 스프링 컨테이너 빈 생성&관리의 기본패턴인 '싱글톤 패턴'을 지킬 수 없는 방식이다.

    @Test
    void proxyCommonMethod() {
        MyConfigProxy myConfigProxy = new MyConfigProxy();
        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        assertThat(bean1.common).isSameAs(bean2.common);
    }
    // 이건 스프링컨테이너의 도움을 받은건 아니지만, 프록시를 직접 만들어서 스프링컨테이너에서 일어나는 일을 비슷하게 흉내내본 것


    // 'MyConfig' 를 확장해서 타깃 오브젝트(Common 객체)에 대한 접근방식을 제어하는(없으면 상위클래스 기능을 호출해서 진짜 생성해 넣고, 있으면 있는거 그대로 반환해주고 하는, 마치 타깃객체에 캐싱하듯이 이용하는 식) 프록시를 하나 만들었다.
    static class MyConfigProxy extends MyConfig {
        private Common common;

        @Override
        Common common() {
            if(this.common == null) this.common = super.common();

            return this.common;
        }
    }


    @Configuration(proxyBeanMethods = true)
    // @Configuration 용 클래스(MyConfig 같은 클래스)가 'proxyBeanMethods = true' 상태로 스프링 컨테이너에 빈으로 등록이 되면
    // 이 클래스 자체가 직접 빈으로 등록되는게 아니라 그 앞에 프록시 오브젝트(Proxy Object)를 하나 두고 그게 대신 빈으로 등록이 된다. (프록시 패턴)
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common {
    }

}
