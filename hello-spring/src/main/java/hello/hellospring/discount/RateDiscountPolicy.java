package hello.hellospring.discount;

import hello.hellospring.member.Grade;
import hello.hellospring.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy{

    private final int DISCOUNT_RATE = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * DISCOUNT_RATE / 100;
        } else {
            return 0;
        }
    }
}
