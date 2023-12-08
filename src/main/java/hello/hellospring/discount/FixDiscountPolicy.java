package hello.hellospring.discount;

import hello.hellospring.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private final int DISCOUNT_FIX_AMOUNT = 1000;

    @Override
    public int discount(Member member, int price) {
        // String 비교와 다르게, enum 인 경우에는 "==" 비교
        if (member.getGrade() == Grade.VIP) {
            return DISCOUNT_FIX_AMOUNT;
        } else {
            return 0;
        }
    }
}
