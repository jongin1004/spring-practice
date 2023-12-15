package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
@RequestMapping("/request-body-string")
public class RequestBodyStringController {

    @PostMapping("/v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", s);

        response.getWriter().write("ok");
    }

    @PostMapping("/v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", s);
        responseWriter.write("ok");
    }

    /**
     * HttpEntity는 Http header와 body의 정보를 편리하게 조회한다.
     * 요청 파라미터를 조회하는 기능과는 관계없이, 메세지 바디 정보를 직접 조회한다.
     * 또한, httpEntity는 응답에도 사용이 가능해서, 메세지 바디 정보를 직접 반환, 헤더 정보 포함이 가능하다.
     * @param httpEntity
     * @return
     * @throws IOException
     */
    @PostMapping("/v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
    }

    /**
     * HttpEntity를 애노테이션 형태로 사용이 가능하다.
     * 요청은 @RequestBody, 응답은 @ResponseBody
     * 헤더값이 필요한 경우에는 HttpEntity를 사용하거나 @RequestHeader를 사용한다.
     * @param messageBody
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);

        return "ok";
    }
}
