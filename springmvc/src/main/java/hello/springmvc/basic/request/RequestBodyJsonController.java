package hello.springmvc.basic.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
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
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
@RequestMapping("/request-body-json")
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();


    @ResponseBody
    @PostMapping("/v1")
    public String requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws JsonProcessingException {


        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    /**
     * @RequestBody에 json 요청이 오게되면, http 메세지 컨버터가 객체로 데이터를 바인딩해서 반환해준다.
     * 주의할점은, Content-Type가 application/json이어야함
     * @param helloData
     * @return
     */
    @ResponseBody
    @PostMapping("/v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    /**
     * HttpEntity로 처리해도 가능
     * @param httpEntity
     * @return
     */
    @ResponseBody
    @PostMapping("/v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {

        HelloData helloData = httpEntity.getBody();
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    /**
     * 요청뿐만아니라, 응답시에 객체를 넣어도, http메세지 컨버터에 의해서 json으로 변경되어 응답이된다.
     * client의 Accept헤더 값이 application/json이어야 json으로 변경됨
     * @param helloData
     * @return
     */
    @ResponseBody
    @PostMapping("/v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return helloData;
    }
}
