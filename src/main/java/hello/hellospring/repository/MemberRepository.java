package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

// Member 저장은, 어떻게 구현할지 정해지지 않았다. => 메모리에서 할지, 내부 DB를 사용하지, 외부 시스템을 사용할지등
// 따라서, 인터페이스로 역할을 만들어 놓고, 나중에 결졍되었을때 알맞는 구현체를 만들면 된다.
public interface MemberRepository {

    Member save(Member member);
//  Optional은 결과가 Null이 나왔을 경우 에러가 발생하는 것을 막기위해 사용한다.
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
