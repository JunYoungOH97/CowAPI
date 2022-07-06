package server.server.Service.AiApiFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import server.server.Service.AiApiService;

@Component
@RequiredArgsConstructor
public class AiFactory {
    private final AiApiService aiApiService;

    public AiModel getModel(String name) {
        if(name.equals("vgg")) {
            return new VggModel(aiApiService);
        }

        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 서비스입니다.");
        }
    }

}
