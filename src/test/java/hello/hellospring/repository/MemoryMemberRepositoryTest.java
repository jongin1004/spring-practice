package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

// 여러개의 테스트를 실행했을 경우에, 테스트를 작성한 순서대로 실행되지 않는다.
// 따라서, 서로 순서 종속이 되어서는 안된다. 각각이 별도의 테스트로써 동작해야함
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

//    @AfterEaech는 각 테스트가 끝난 후, 해당 메소드가 실행되도록 한다.
//    왜? 이전 테스트에서 변경한 값이 그대로 남아있기 때문에, 초기화 해주지 않으면 다음 테스트에 영향을 끼친다.
    @AfterEach
    public void afterEach() {
//        store에 저장된 값을 리셋한다.
        repository.clearStore();
    }
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        // get() : Optional 타입에서 값을 꺼내기위해 사용
        Member result = repository.findById(member.getId()).get();

        assertThat(member).isEqualTo(result);
//        System.out.println("result = " + (result == member));
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }
    
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
