package server.server.Domain.Dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    private Integer code;
    private HttpStatus httpStatus;
    private String message;
}
