package toyspringboot.server.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.Dto.HelloDto;
import toyspringboot.server.Service.HelloService;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final HelloService helloService;

    @GetMapping("/get/{id}")
    public HelloDto getHello(@PathVariable(value = "id") Long id) {
        return helloService.getHello(id);
    }

    @PostMapping("/post")
    public boolean postHello(@RequestBody HelloDto helloDto) {
        return helloService.postHello(helloDto);
    }

    @PutMapping("/put/{id}")
    public boolean putMessage(@PathVariable(value = "id") Long id,
                               @RequestBody String message) {
        return helloService.putMessage(id, message);
    }
    @DeleteMapping("/delete/{id}")
    public boolean deleteMessage(@PathVariable(value = "id") Long id) {
        return helloService.deleteMessage(id);
    }
}

