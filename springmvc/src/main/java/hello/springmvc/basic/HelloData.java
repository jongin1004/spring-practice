package hello.springmvc.basic;

import lombok.Data;

// @Data는 @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequriedArgsConstructor를 자동으로 만들어
@Data
public class HelloData {

    private String username;
    private int age;
}
