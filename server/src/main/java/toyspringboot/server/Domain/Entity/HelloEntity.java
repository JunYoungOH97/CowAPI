package toyspringboot.server.Domain.Entity;

import lombok.*;
import toyspringboot.server.Domain.Dto.HelloDto;

//import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Entity
public class HelloEntity {
    List<HelloDto> helloList;
}
