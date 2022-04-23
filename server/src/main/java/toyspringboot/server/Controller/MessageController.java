package toyspringboot.server.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.Dto.MessageDto;
import toyspringboot.server.Service.MessageService;

@RestController
@RequiredArgsConstructor
public class MessageController {
//    private final MessageService helloService;
//
//    @GetMapping("/messages/{id}")
//    public MessageDto getHello(@PathVariable(value = "id") Long id) {
//        return helloService.getMessage(id);
//    }
//
//    @PostMapping("/messages/message")
//    public boolean postHello(@RequestBody MessageDto helloDto) {
//        return helloService.postMessage(helloDto);
//    }
//
//    @PutMapping("/messages/{id}")
//    public boolean putMessage(@PathVariable(value = "id") Long id,
//                               @RequestBody String message) {
//        return helloService.putMessage(id, message);
//    }
//
//    @DeleteMapping("/messages/{id}")
//    public boolean deleteMessage(@PathVariable(value = "id") Long id) {
//        return helloService.deleteMessage(id);
//    }
}

