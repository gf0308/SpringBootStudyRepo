# @Profile 애노테이션

# @Profile

@Profile 애노테이션은 스프링 애플리케이션을 구동할 때 무슨 런타임환경을 선택했느냐에 따라 **해당 런타임용으로 만들어놓은 @Configuration 클래스**(빈 구성 클래스)가 **스프링 컨테이너의 빈 구성에 사용되도록** 하는 기능이다.

### 사용 방식

```java
**@Profile("prod")**
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
**@Conditional(ProfileCondition.class) // => 이에 보듯 @Conditional을 메타애노테이션으로 갖고 있는 애노테이션이다.**
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