package cn.shiyang.tflz.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleInternalException(RuntimeException e) throws IOException {
        log.error(this.getClass().getName(), e.getMessage());
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
