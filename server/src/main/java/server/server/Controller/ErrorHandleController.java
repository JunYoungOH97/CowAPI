package server.server.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import server.server.Domain.Dto.ErrorDto;
import server.server.Service.ErrorHandleService;

@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorHandleController {
    private final ErrorHandleService errorService;

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<ErrorDto> ErrorHandler(ResponseStatusException e) {
        return errorService.errorHandling(e);
    }
}
