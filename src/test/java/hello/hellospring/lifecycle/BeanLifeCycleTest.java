package hello.hellospring.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {

        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // ApplicationContext를 닫아준다.
    }

    @Configuration
    static class LifeCycleConfig {

        // destroyMethod의 초기값은 inferred 인데, 추론이라는 의미로 보통 라이브러리의 종료 메서드인 close나 shutdown 메소드를 자동적으로 호출하게 되어있다.
        // 보통은 @PostConstruct, @PreDestroy를 사용하지만, 외부 라이브러리를 사용하는 경우에는 사용 불가이기 때문에 @Bean(...) 방법을 사용해야함
        // @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
