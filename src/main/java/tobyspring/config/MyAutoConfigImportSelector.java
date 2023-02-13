package tobyspring.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class MyAutoConfigImportSelector implements DeferredImportSelector { // ImportSelector 를 구현한 구체클래스 중 하나인 'DeferredImportSelector'

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
//        return new String[] {
//                "tobyspring.config.autoconfig.TomcatWebServerConfig",
//                "tobyspring.config.autoconfig.DispatcherServletConfig"
//        };


//        Iterable<String> candidates = ImportCandidates.load(myAutoConfiguration.class, classLoader);
//        return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);

//        List<String> autoConfigs = new ArrayList<>();
        // ImportCandidates 클래스는 'Iterable<String>' 인터페이스를 구현하고 있으므로, .load() 결과를 Iterable<String> 로 간주해도 작동한다.
//        for (String candidate : ImportCandidates.load(myAutoConfiguration.class, classLoader)) {
//            autoConfigs.add(candidate);
//        }
        // 위는 자바5 시절 스타일이니까, 자바8 스타일로 바꿔보면 아래와 같다.
//        List<String> autoConfigs = new ArrayList<>();
//        ImportCandidates.load(myAutoConfiguration.class, classLoader).forEach(candidate -> autoConfigs.add(candidate));
        // 이를 '메서드 레퍼런스 표식(::)'을 사용하면 더 간결하게 줄일 수 있다.
        List<String> autoConfigs = new ArrayList<>();
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add); // "forEach 로 나오는 매 요소를, autoConfigs 오브젝트 안의 add란 메서드에 대해 적용을 해줘라"

        return autoConfigs.toArray(new String[0]);
    }

}
