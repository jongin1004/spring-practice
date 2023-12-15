package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Controller
@ResponseBody // 클래스 레벨에 선언해도, 각 메서드에 적용되게 된다. (그래서 @Controller 와 @ResponseBody를 합친게 @RestController)
@RequestMapping("/response-body-json")
public class ResponseBodyController {


    @GetMapping("v1")
    public ResponseEntity<HelloData> responseBodyV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("jongin");
        helloData.setAge(29);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    /**
     * @ResponseBody 애너테이션을 이용하는 경우에는, @ResponseStatus 애너테이션을 따로 이용해서 상태코드 지정이 가능하다.
     * 단, 애너테이션은 동적으로 상태코드를 변경하는 것이 불가능하기 때문에, 분기에 따라 다른 상태코드를 사용하고 싶은 경우에는
     * ResponseEntitfy를 이용한다.
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
    @GetMapping("v2")
    public HelloData responseBodyV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("jongin");
        helloData.setAge(29);

        return helloData;
    }

}
