package server.server.Domain.Entity;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AiList {
    List<Ai> aiList;
}
