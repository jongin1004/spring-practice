package hello.hellospring.order;

import hello.hellospring.discount.DiscountPolicy;
import hello.hellospring.discount.FixDiscountPolicy;
import hello.hellospring.member.Member;
import hello.hellospring.member.MemberRepository;
import hello.hellospring.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member findMember = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
