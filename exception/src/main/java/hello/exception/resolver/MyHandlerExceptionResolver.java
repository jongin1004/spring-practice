package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgsException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView(); // 빈 객체를 전달하게되면, 뷰를 랜더링하지 않고 was로 그대로 넘어간다.
            }

        } catch (IOException e) {
            log.error("resolver ex", e);
//            throw new RuntimeException(e);
        }

        // null을 반환하게되면, 결국 was까지 exception이 넘어가서 was는 500을 반환한다.
        return null;
    }
}
