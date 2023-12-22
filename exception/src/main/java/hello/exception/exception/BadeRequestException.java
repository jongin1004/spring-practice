package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// ResponseStatusExceptionResolver가 상태코드를 가지고, 알아서 처리를 한다.
// 내부에서 response.setError(code, reason)으로 반환함
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "bad request")
public class BadeRequestException extends RuntimeException {
}
