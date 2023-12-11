package hello.hellospring.beanfind;

import hello.hellospring.AppConfig;
import hello.hellospring.member.MemberService;
import hello.hellospring.member.MemberServiceImpl;
import hello.hellospring.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig2.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 중복으로 존재하면, 오류가 발생한다.")
    void findBeanByTypeDuplicate() {
//        MemberService memberService = ac.getBean(MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean(MemberService.class));
    }

    @Test
    @DisplayName("빈 이름을 지정하면, 타입 중복이 있어도 괜찮다")
    void findBeanByNameDuplicate() {
        MemberService memberService = ac.getBean("memberService1", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("같은 타입이 존재해도 모든 빈을 가져온다.")
    void findBeansByTypeDuplicate() {
        Map<String, MemberService> beansOfType = ac.getBeansOfType(MemberService.class);
        for (String s : beansOfType.keySet()) {
            MemberService memberService = beansOfType.get(s);
            System.out.println("key = " + s + ", value = " + memberService);
        }

        assertThat(beansOfType.size()).isEqualTo(2);
    }




    @Configuration
    static class AppConfig2 {

        @Bean
        public MemberService memberService1() {
            return new MemberServiceImpl(new MemoryMemberRepository());
        }

        @Bean
        public MemberService memberService2() {
            return new MemberServiceImpl(new MemoryMemberRepository());
        }
    }
}
