# @Profile 애노테이션

# @Profile

@Profile 애노테이션은 스프링 애플리케이션을 구동할 때 무슨 런타임환경을 선택했느냐에 따라 **해당 런타임용으로 만들어놓은 @Configuration 클래스**(빈 구성 클래스)가 **스프링 컨테이너의 빈 구성에 사용되도록** 하는 기능이다.

### 사용 방식

```java
@Profile("prod")
@Configuration
public class ProdConfiguration {
    @Bean
    public String hello(){
        return "hello production";
    }
}
// 위처럼 @Configuration 클래스에 적용하는 클래스레벨 애노테이션 이다.
```

### 상세

@Profile 애노테이션의 상세 코드를 보면 다음과 같다.

```java
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ProfileCondition.class) // => 이에 보듯 @Conditional을 메타애노테이션으로 갖고 있는 애노테이션이다.**
public @interface Profile {

	/**
	 * The set of profiles for which the annotated component should be registered.
	 */
	String[] value();

}
```

### @Profile 애노테이션을 사용하는 이유

스프링 애플리케이션을 실행환경마다(런타임 환경 별로) 다르게 구성해서 실행시키는 것을 좀 더 편하게 구현할 수 있게 해준다.

즉 런타임환경별로 선택될 @Configuration 클래스 빈을 애노테이션이란 간편한 방식으로 선택할 수 있게 해준다.

### 예시
```java
// 스프링 애플리케이션 구성에 관한 메타정보를 아래처럼 
// 런타임환경종류별로(prod/dev/test .. 등) 두고 이용할 수가 있다.

// properties 정보
// prod 용 application-properties
# application-prod.properties
hi.name=hi prod
......

// dev용 application-properties
# application-dev.properties
hi.name=hi dev
......

// test용 application-properties
# application-test.properties
hi.name=hi test
......

// ==============================================================================
// 그리고 각 Configuration 클래스들이 (런타임환경종류별로) 여러개 존재할 수 있다.

@Profile("prod")
@Configuration
public class ProdConfiguration {
    @Bean
    public String hello(){
        return "hello production";
    }
}

@Profile("dev")dn
@Configuration
public class DevConfiguration {
    @Bean
    public String hello(){
        return "hello dev";
    }
}

@Profile("test")
@Configuration
public class TestConfiguration {
    @Bean
    public String hello(){
        return "hello test";
    }
}

// ==============================================================================
// 이럴 때 각 Configuration 클래스별로 @Profile() 애노테이션을 적용해두고
// 스프링 애플리케이션을 구동할 때 런타임환경명을 입력해넣어주면
// 그 해당 런타임용으로 구성해놓은 Configuration 클래스가 빈으로 등록되며
// 스프링컨테이너 구성이 이루어진다.
```