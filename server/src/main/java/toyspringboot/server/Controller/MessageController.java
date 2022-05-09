package toyspringboot.server.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.Dto.MessageDto;
import toyspringboot.server.Service.MessageService;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;


    @PostMapping("/messages/message")
    public MessageDto createMessage(@RequestBody MessageDto messageDto) {
        return messageService.createMessage(messageDto);
    }

    @GetMapping("/messages/{id}")
    public MessageDto searchMessage(@PathVariable(value = "id") Long id) {
        return messageService.searchMessage(id);
    }

    @PutMapping("/messages/{id}")
    public MessageDto updateMessage(@PathVariable(value = "id") Long id,
                               @RequestBody String message) {
        return messageService.updateMessage(id, message);
    }

    @DeleteMapping("/messages/{id}")
    public boolean deleteMessage(@PathVariable(value = "id") Long id) {
        return messageService.deleteMessage(id);
    }
}

