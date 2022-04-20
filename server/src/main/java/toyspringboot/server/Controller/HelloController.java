package toyspringboot.server.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import toyspringboot.server.Domain.Dto.HelloDto;
import toyspringboot.server.Service.HelloService;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final HelloService helloService;

    @GetMapping("/hello")
    public HelloDto showHello() {
        String m = "Hello World!";
        return helloService.showHello(m);
    }

}
