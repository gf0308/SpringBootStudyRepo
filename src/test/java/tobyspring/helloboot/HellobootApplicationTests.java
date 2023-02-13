package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 @SpringBootTest : "이 테스트 클래스 내에서 수행되는 테스트케이스들은, 스프링부트 기반 애플리케이션의 기능에 대한 테스트를 수행하는 것이다" 라는 의미
  -> 때문에 테스트하는 애플리케이션이 스프링부트애플리케이션이 아닌 상태인데 테스트클래스에 @SpringBootTest를 붙여놓는다면, 해당 애플리케이션이 스프링부트 애플리케이션이 아니기 때문에 발생하는 에러가 발생할 것이다.
     ex) "Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test"
        위처럼 테스트 대상이 될 스프링부트애플리케이션을 구동하기 위해 애플리케이션 구성정보를 지닌 Configuration 단을 찾을 것인데,
        없기 때문에 "Unalbe to find a @SpirngBootConfiguration.." 에러를 리턴한다.
 * */

//@SpringBootTest // @SpringBootTest : "이 테스트 클래스 내에서 수행되는 테스트케이스들은, 스프링부트 기반 애플리케이션의 기능에 대한 테스트를 수행하는 것이다" 라는 의미
class HellobootApplicationTests {

    @Test
    void test() {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Integer[] intArr = new Integer[10];
        Integer[] integers = list.toArray(intArr);
//        Integer[] integers = list.toArray(new Integer[0]);

        System.out.println("=====================================");
//        System.out.println("strings.length : " + strings.length);
//        System.out.println("strings : " + strings.length);
//        System.out.println("strings.length : " + strings.length);
//        System.out.println("strings.length : " + strings.length);
        System.out.println("=====================================");
        // toArray() -> Params: a – the array into which the elements of this list are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose.










        // HTTP API 요청 테스트를 수행하는데 활용할 수 있는 (스프링프레임워크가 제공하는) 클래스 : RestTemplate
        // HTTP API 요청 테스트를 수행하는데 좀더 편하게 활용할 수 있는 (스프링부트에서부터 제공하는) 클래스 :  TestRestTemplate
//        TestRestTemplate testRestTmplt = new TestRestTemplate();
//        // "주어진 Entity에 대해 GET 요청 호출을 수행" -> 처리결과로 ResponseEntity 를 리턴(응답에 대한 모든 정보가 담겨있음)
//        ResponseEntity<String> response = testRestTmplt.getForEntity("http://localhost:8080/hello?name={name}", String.class, "jbkim");
//
//        System.out.println("==================================API Call Result==================================");
//        System.out.println("StatusCode: " + response.getStatusCode());
//        System.out.println("Headers: " + response.getHeaders());
//        System.out.println("Body: " + response.getBody());
//        System.out.println("===================================================================================");
//
//        System.out.println("==================================TEST 시작==================================");
//        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); // 상태코드 200 인가?
//        Assertions.assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE); // Headers 의 Content-Type : 'text/plain' 이 맞는가?
//        Assertions.assertThat(response.getBody()).isEqualTo("Hello jbkim"); // body 내용에 "hello jbkim" 이 맞는가?

    }

}
