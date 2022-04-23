package toyspringboot.server.Domain.Dto;

import lombok.*;
import toyspringboot.server.Domain.Entity.Messages;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    private Long id;
    private String message;

    public static MessageDto of(Messages messagesEntity) {
        return MessageDto.builder()
                .id(messagesEntity.getId())
                .message(messagesEntity.getMessage())
                .build();
    }
}
