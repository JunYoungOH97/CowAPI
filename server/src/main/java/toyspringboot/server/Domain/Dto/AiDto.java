package toyspringboot.server.Domain.Dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiDto {
    private String category;
    private String score;
}
