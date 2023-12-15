package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller 인 경우에는 return 이 뷰이름으로 인식하여 뷰를찾고 뷰를 랜더링한다.
// @RestController는 문자를 return 하면, string이 그대로 반환이된다. (RestAPI를 구현할 때 핵심적인 어노테이션)
@RestController
@Slf4j
public class LogTestController {

    // @Slf4j를 추가하면 생략가능
    // private final Logger log = LoggerFactory.getLogger(getClass());

   @GetMapping("/log-test")
   public String logTest() {
       String name = "Spring";

       System.out.println("---logging---");

       // 2023-12-15T11:04:50.495+09:00  INFO 70935 --- [nio-8080-exec-1] h.springmvc.basic.LogTestController :  inof log = Spring
       // 시간 로그레벨 PID --- [스레드] 컨트롤 이름 : 메세지
       log.trace("trace log = {}", name);
       log.debug("debug log = {}", name);
       log.info("info log = {}", name);
       log.warn("warn log = {}", name);
       log.error("error log = {}", name);

       // 로깅 메세지를 적을 때, ("log={}", name) 이런식으로 사용하는 이유?
       // "log="+name 으로하게 되면, 출력하지 않는 로그도 모두 연산이 일어나게 되기 때문이다.
       // 예를들어 trace 로깅은 info 레벨에서는 출력이 되지 않는데도, + 연산이 일어나서 메모리와 cpu에 붎필요한 사용이 발생한다.


       return "ok";
   }
}
