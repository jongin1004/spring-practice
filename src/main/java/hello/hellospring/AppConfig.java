package hello.hellospring;

import hello.hellospring.discount.DiscountPolicy;
import hello.hellospring.discount.FixDiscountPolicy;
import hello.hellospring.discount.RateDiscountPolicy;
import hello.hellospring.member.MemberRepository;
import hello.hellospring.member.MemberService;
import hello.hellospring.member.MemberServiceImpl;
import hello.hellospring.member.MemoryMemberRepository;
import hello.hellospring.order.OrderService;
import hello.hellospring.order.OrderServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 설정정보를 담당하는 클래스라는 것을 알리는 어노테이션
//@Configuration
public class AppConfig {

    // @Bean이 붙은 것은 스프링 컨테이너에 등록이 된다.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public  OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
