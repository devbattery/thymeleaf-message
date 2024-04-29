package hello.itemservice.message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

@SpringBootTest
class MessageSourceTest {

    @Autowired
    private MessageSource ms;

    @DisplayName("default message의 hello를 조회할 수 있다.")
    @Test
    void hello_message() {
        // given
        String result = ms.getMessage("hello", null, null);

        // when
        // then
        assertThat(result).isEqualTo("안녕");
    }

    @DisplayName("")
    @Test
    void not_found_message_code() {
        // given
        // when
        // then
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @DisplayName("")
    @Test
    void not_found_message_code_default_message() {
        // given
        String result = ms.getMessage("no_code", null, "기본 메시지", null);

        // when
        // then
        assertThat(result).isEqualTo("기본 메시지");
    }

    @DisplayName("")
    @Test
    void argument_message() {
        // given
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);

        // when
        // then
        assertThat(message).isEqualTo("안녕 Spring");
    }

    @DisplayName("")
    @Test
    void default_language() {
        // given
        // when
        // then
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @DisplayName("")
    @Test
    void en_language() {
        // given
        // when
        // then
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }

}
