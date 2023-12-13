package hello.hellospring.discount;

import hello.hellospring.member.Member;

public interface DiscountPolicy {

    /**
     **
     * @return 할인 대상 급액
     */
    int discount(Member member, int price);
}
