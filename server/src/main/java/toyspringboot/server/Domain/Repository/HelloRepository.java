package toyspringboot.server.Domain.Repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyspringboot.server.Domain.Dto.HelloDto;
import toyspringboot.server.Domain.Entity.HelloEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HelloRepository {
    private final HelloEntity helloEntity = new HelloEntity();

    public void setHelloEntity() {
        HelloDto helloDto = HelloDto.builder().id(1L).message("Hello").build();
        this.helloEntity.setHelloList(new ArrayList<>());
        this.helloEntity.getHelloList().add(helloDto);
    }

    public boolean insert(HelloDto helloDto) {
        setHelloEntity();

        Long newId = -1L;
        for(HelloDto h : helloEntity.getHelloList()) {
            if(newId < h.getId()) {
                newId = h.getId();
            }
        }
        helloDto.setId(newId + 1L);

        helloEntity.getHelloList().add(helloDto);

        return true;
    }

    public HelloDto get(Long id) {
        setHelloEntity();

        HelloDto helloDto = HelloDto.builder().build();

        for(HelloDto h : helloEntity.getHelloList()) {
            if(h.getId().equals(id)) {
                helloDto.setId(h.getId());
                helloDto.setMessage(h.getMessage());
            }
        }
        return helloDto;
    }

    public boolean put(Long id, String message) {
        setHelloEntity();

        for(HelloDto h : helloEntity.getHelloList()) {
            if(h.getId().equals(id)) {
                h.setMessage(message);
            }
        }

        return true;
    }

    public boolean delete(Long id) {
        setHelloEntity();

        for(HelloDto h : helloEntity.getHelloList()) {
            if(h.getId().equals(id)) {
                helloEntity.getHelloList().remove(h);
            }
        }

        return true;
    }
}
