package hello.hellospring.beanfind;

import hello.hellospring.discount.DiscountPolicy;
import hello.hellospring.discount.FixDiscountPolicy;
import hello.hellospring.discount.RateDiscountPolicy;
import hello.hellospring.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig2.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식 타입의 bean도 모두 조회된다.")
    void findBeansByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String s : beansOfType.keySet()) {
            DiscountPolicy memberService = beansOfType.get(s);
            System.out.println("key = " + s + ", value = " + memberService);
        }

        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class AppConfig2 {

        @Bean
        public RateDiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public FixDiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
