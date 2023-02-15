package tobyspring.hellbootbackup;

import org.springframework.context.annotation.Conditional;
import tobyspring.hellbootbackup.MyConditionClassForUndertow;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Conditional(MyConditionClassForUndertow.class)
public @interface ConditionalMyUndertow {
    String value();
}
