package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

// 스프링빈에 자동으로 등록하고 의존성 주입을 위해서
@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    @DisplayName("get message by code")
    void helloMessage() {
        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    @DisplayName("not found message code")
    void notFoundMessageCode() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    @DisplayName("default message")
    void notFoundMessageCodeDefaultMessage() {
        String result = ms.getMessage("no_code", null, "기본 메세지", null);
        assertThat(result).isEqualTo("기본 메세지");
    }

    @Test
    @DisplayName("get message with argument")
    void argumentMessage() {
        String result = ms.getMessage("hello.name", new Object[]{"string"}, null);
        assertThat(result).isEqualTo("안녕 string");
    }

    @Test
    @DisplayName("get message with locale")
    void enLocale() {
        String result = ms.getMessage("hello.name", new Object[]{"string"}, Locale.ENGLISH);
        assertThat(result).isEqualTo("hello string");
    }
}
