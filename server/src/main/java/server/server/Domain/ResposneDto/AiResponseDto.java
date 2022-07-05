package server.server.Domain.ResposneDto;

import lombok.*;

import java.sql.Timestamp;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiResponseDto {
    String name;
    String field;
    Double responseTime;
    Double accuracy;

    String requestURI ;
    String method;
    String req;
    String res;
    Timestamp updatedAt;
}
