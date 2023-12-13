package hello.hellospring.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private MemberRepository memberRepository;

    // 스프링 컨테이너가 자동으로 해당 스프링빈을 찾아서 주입한다.
    // MemberRepository와 타입이 같은 빈을 찾아서 주입 (타입이 안맞는 경우나, 중복이 발생하는 경우의 해결방법도 있다.)
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}