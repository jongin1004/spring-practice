package hello.hellospring.scan;

import hello.hellospring.AutoAppConfig;
import hello.hellospring.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        // ComponentScan을 사용하는 경우의 빈 이름은, 클래스의 이름인데 맨 앞이 소문자로 변경되게 된다.
        // 예: class이름이 MemberServiceImpl -> memberServiceImpl
        MemberService memberService = ac.getBean("memberServiceImpl", MemberService.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
