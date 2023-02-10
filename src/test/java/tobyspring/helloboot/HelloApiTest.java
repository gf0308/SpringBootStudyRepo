package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

public class HelloApiTest {

    @DisplayName("HTTP API 호출 결과 응답 내용을 출력하는 메서드")
    static void printResponse(ResponseEntity<String> res) {
        System.out.println("==================================API Call Result==================================");
        System.out.println("StatusCode: " + res.getStatusCode());
        System.out.println("Headers: " + res.getHeaders());
        System.out.println("Body: " + res.getBody());
        System.out.println("===================================================================================");
    }

    @Test
    void helloApi() {
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");

        assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getBody()).isEqualTo("*Hello Spring*");
    }

    @Test
    @DisplayName("helloApi 요청 호출이 실패하는 테스트")
    void failsHelloApi() {
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:8080/hello?name=", String.class);

        printResponse(res);

        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR); // 'INTERNAL_SERVER_ERROR' : 서버내부에러(서버 내부에서 Exception을 던진 경우) : 500 에러
    }

}
