package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.assertj.AssertableApplicationContext;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.test.context.runner.ContextConsumer;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ConditionalTest {

    @Test
    void conditional() {
        // [true 경우]
                //        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
                //        ac.register(Config1.class);
                //        ac.refresh();
                //        MyBean bean = ac.getBean(MyBean.class);
        // 테스트 전용 애플리케이션컨텍스트(ApplicationContext)(즉, 테스트 전용 스프링컨테이너 오브젝트)로 쓸 수 있게 '스프링부트에서' 만들어서 제공해주는게 있다. : ApplicationContextRunner
        ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(Config1.class)
                .run(
//                        new ContextConsumer<AssertableApplicationContext>() {
//                            @Override
//                            public void accept(AssertableApplicationContext context) throws Throwable {
//                                assertThat(context).hasSingleBean(MyBean.class);
//                                assertThat(context).hasSingleBean(Config1.class);
//                            }
//                        }
                        context -> { // 여기서 context를 사용하는 consumer타입의 람다식을 사용할 수 있다.
                            assertThat(context).hasSingleBean(MyBean.class);
                            assertThat(context).hasSingleBean(Config1.class);
                        }
                );

        // [false 경우]
                //        AnnotationConfigWebApplicationContext ac2 = new AnnotationConfigWebApplicationContext();
                //        ac2.register(Config2.class);
                //        ac2.refresh();
                //        MyBean bean2 = ac2.getBean(MyBean.class);
        new ApplicationContextRunner().withUserConfiguration(Config2.class)
                .run(context -> { // 여기서 context를 사용하는 consumer타입의 람다식을 사용할 수 있다.
                    assertThat(context).doesNotHaveBean(MyBean.class);
                    assertThat(context).doesNotHaveBean(Config2.class);
                });
    }

    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> attrs = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            Boolean value = (Boolean) attrs.get("value");
            return value;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional {
        boolean value();
    }

//    @Retention(RetentionPolicy.RUNTIME)
//    @Target(ElementType.TYPE)
//    @Conditional(TrueCondition.class)
//    @interface TrueConditional {}
//
//    @Retention(RetentionPolicy.RUNTIME)
//    @Target(ElementType.TYPE)
//    @Conditional(FalseCondition.class)
//    @interface FalseConditional {}


    // class
    @Configuration
//    @Conditional(TrueCondition.class)
//    @TrueConditional
    @BooleanConditional(true)
    static class Config1 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }

    }

    @Configuration
//    @Conditional(FalseCondition.class)
//    @FalseConditional
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    static class MyBean {}

    static class TrueCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class FalseCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }


}
