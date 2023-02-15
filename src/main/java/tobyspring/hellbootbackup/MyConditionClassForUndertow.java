package tobyspring.hellbootbackup;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

import java.util.Map;

public class MyConditionClassForUndertow implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attr = metadata.getAnnotationAttributes(ConditionalMyUndertow.class.getName());
        String value = (String) attr.get("value");
        return ClassUtils.isPresent(value, context.getClassLoader());
    }
}
