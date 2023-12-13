package hello.hellospring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// Component 어노테이션이 붙어있는 것을 다 등록을 하는데, excludeFilters를 이용해서 Configuration 어노테이션이 있는건 예외로한다.
// basePackages는 스캔의 기준이되는 패키지를 선언한다. (선언하지 않으면 라이브러리를 포함한 모든 자바 클래스를 탐색하기 때문에 비효율적임)
// basePackages를 지정하지 않으면, 현재 config 파일의 패키지 위치를 포함한 하위 패키지를 스캔한다.(그래서 config파일의 권장위치는 프로젝트 최상단)
@ComponentScan(
        basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
