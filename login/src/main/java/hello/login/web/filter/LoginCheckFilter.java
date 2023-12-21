package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};

    // init,destroy 메소드도 존재하지만, default가 들어가 있어서 오버라이딩 안해줘도 가능하다.
    // 원래 interface의 메소드는 전부 구현하는 것이 맞음
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 필터 시작 [{}]", requestURI);

            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 [{}]", requestURI);
                HttpSession session = httpRequest.getSession(false);

                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청 [{}]", requestURI);
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return;
                }
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e; // 톰켓까지 예외를 보내줘야함
        } finally {
            log.info("인증 체크 필터 종료 [{}]", requestURI);
        }
    }

    /**
     * 화이트 리스트의 경우 인증체크 x
     */
    private boolean isLoginCheckPath(String requestURI) {
        return ! PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
