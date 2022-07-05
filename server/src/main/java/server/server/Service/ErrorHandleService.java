package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import server.server.Domain.Dto.ErrorDto;

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
}
