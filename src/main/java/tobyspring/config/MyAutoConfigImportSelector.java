package tobyspring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector { // ImportSelector 를 구현한 구체클래스 중 하나인 'DeferredImportSelector'
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "tobyspring.config.autoconfig.TomcatWebServerConfig",
                "tobyspring.config.autoconfig.DispatcherServletConfig"
        };
    }
}
