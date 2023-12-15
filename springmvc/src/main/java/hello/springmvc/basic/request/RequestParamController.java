package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/request-param")
public class RequestParamController {

    @RequestMapping("/v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);
        writeMessage(response, "ok");
    }

    /**
     * @RequestParam을 이용해서, 파라미터를 가져와 변수에 지정할 수 있다.
     * request.getParameter()와 동일함,
     * @param userName
     * @param memberAge
     * @return
     */
    @ResponseBody
    @RequestMapping("/v2")
    public String requestParamV2(
            @RequestParam("username") String userName,
            @RequestParam("age") int memberAge ) {

        log.info("username={}, age={}", userName, memberAge);
        return "ok";
    }

    /**
     * 변수명과, 쿼리스트링의 key값과 이름이 동일하면, @RequestParam의 매개변수 생략가능
     * @param username
     * @param age
     * @return
     */
    @ResponseBody
    @RequestMapping("/v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age ) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * 변수명과, 쿼리스트링의 key값이 동일하면서, String, int 같은 단순타입인 경우에는 @RequestParam도 생략가
     * 근데, 너무 생략하면 직관적이지 않기 때문에 애노테이션을 추가하는 것이 좋은 경우가 있다.
     * @param username
     * @param age
     * @return
     */
    @ResponseBody
    @RequestMapping("/v4")
    public String requestParamV4(String username, int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * required속성을 이용해서 필수가 아니게 할 수 있음 (필수가 default)
     * @param username
     * @param age
     * @return
     */
    @ResponseBody
    @RequestMapping("/v5")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * 파라미터의 값이 없는 경우에 defaultValue를 할
     * 빈문자인 경우에도 defaultValue가 할당된다.
     * @param username
     * @param age
     * @return
     */
    @ResponseBody
    @RequestMapping("/v6")
    public String requestParamDefault (
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * Map이나 MultiValueMap으로 받을 수 있다.
     * 동일한 key의 파라미터값이 존재하는 경우에는 MultiValueMap을 사용해야지 모든 값을 받아올 수 있음
     * @param paramMap
     * @return
     */
    @ResponseBody
    @RequestMapping("/v7")
    public String requestParamMap (@RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model/v1")
    public String modelAttributeV1(
            @RequestParam String username,
            @RequestParam int age
    ) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);
        log.info("info helloData={}", helloData);
        return "ok";
    }

    /**
     * @ModelAttribute가 있으면, 우선 HelloData 객체를 생성하고,
     * 요청 파라미터의 이름으로 HelloData 객체의 프로퍼리를 찾고 Setter를 호출해서 파라미터의 값을 바인딩한다.
     * @param helloData
     * @return
     */
    @ResponseBody
    @RequestMapping("/model/v2")
    public String modelAttributeV2(@ModelAttribute HelloData helloData) {

        log.info("info helloData={}", helloData);
        return "ok";
    }

    /**
     * @ModelAttribute는 생략이 가능하다.
     * @param helloData
     * @return
     */
    @ResponseBody
    @RequestMapping("/model/v3")
    public String modelAttributeV3(HelloData helloData) {

        log.info("info helloData={}", helloData);
        return "ok";
    }

    private static void writeMessage(HttpServletResponse response, String message) throws IOException {
        PrintWriter w = response.getWriter();
        w.write(message);
    }
}
