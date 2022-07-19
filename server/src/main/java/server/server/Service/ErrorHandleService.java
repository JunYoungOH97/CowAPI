package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import server.server.Domain.Dto.ErrorDto;

import java.io.IOException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ErrorHandleService {
    public ResponseEntity<ErrorDto> errorHandling(ResponseStatusException e) {
        ErrorDto errorDto = ErrorDto.builder()
                .code(e.getRawStatusCode())
                .message(e.getReason())
                .httpStatus(e.getStatus())
                .build();

        return new ResponseEntity<>(errorDto, errorDto.getHttpStatus());
    }

    public ResponseEntity<ErrorDto> errorHandling(IOException e) {
        ErrorDto errorDto = ErrorDto.builder()
                .build();
        System.out.println("message = " + e.getMessage() + " getLocalizedMessage : " + e.getLocalizedMessage() + " getCause : " + e.getCause() + " getSuppressed : " + Arrays.toString(e.getSuppressed()) + " getStackTrace : " + Arrays.toString(e.getStackTrace()));
        return new ResponseEntity<>(errorDto, errorDto.getHttpStatus());
    }
}
