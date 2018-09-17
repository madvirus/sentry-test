package sentrytest.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sentrytest.excol.ExceptionCaptor;
import sentrytest.excol.ExceptionMessage;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionAdvice {
    private ExceptionCaptor exceptionCaptor;

    @Autowired
    public ExceptionAdvice(ExceptionCaptor exceptionCaptor) {
        this.exceptionCaptor = exceptionCaptor;
    }

    @ExceptionHandler
    public ResponseEntity<?> handle(Exception ex, HttpServletRequest request) {
        ExceptionMessage message = new ExceptionMessage.ExceptionMessageBuilder()
                .addTagIfHasText("method", request.getMethod().toUpperCase())
                .addExtra("uri", request.getRequestURI())
                .addExtraIfHasText("querystring", request.getQueryString())
                .exception(ex)
                .build();
        exceptionCaptor.capture(message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
