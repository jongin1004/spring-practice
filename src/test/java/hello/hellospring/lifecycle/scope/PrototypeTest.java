package hello.hellospring.lifecycle.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // prototype의 경우는 컨테이너를 종료해도(ac.close()), close 메소드가 호출되지 않는다
        // 왜? 프로토타입은 생성하고 의존을 주입하고 init 메서드를 호출하는데 까지만 관리하고 그 다음에는 클라이언트로 넘긴뒤에 관리를 하지 않기 때문
        // close 를 호출할려면 클라이언트 코드에서 직접적으로 호출해야함
        // ac.close();
        prototypeBean1.close();
        prototypeBean2.close();

    }

    @Scope("prototype") // default
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("init");
        }

        @PreDestroy
        public void close() {
            System.out.println("close");
        }
    }
}
