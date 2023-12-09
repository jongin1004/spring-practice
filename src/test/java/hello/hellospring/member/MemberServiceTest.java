package hello.hellospring.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static hello.hellospring.member.Grade.*;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {

        // given
        Member member = new Member(1L, "memberA", VIP);
        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
