package server.server.Controller;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import server.server.Domain.ResposneDto.AiListResponseDto;
import server.server.Domain.ResposneDto.AiResponseDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AiPageController {
    @GetMapping("/ai/{name}")
    public ResponseEntity<AiResponseDto> aiOnePage(@RequestHeader("Authorization") String userToken,
                                                   @PathVariable(value = "name") String name) {


        JsonObject res =new JsonObject();

        JsonObject header =new JsonObject();
        header.addProperty("content-type", "type");
        header.addProperty("accessKey", "key");

        JsonObject body =new JsonObject();
        body.addProperty("images", "from-data");

        res.add("header", header);
        res.add("body", body);

        JsonObject req = new JsonObject();

        JsonObject header1 =new JsonObject();
        JsonObject body2 =new JsonObject();
        body2.addProperty("category", "String");
        body2.addProperty("accuracy", "Double");

        req.add("header", header1);
        req.add("body", body2);


        AiResponseDto aiResponseDto = AiResponseDto.builder()
                .name(name)
                .responseTime(0.1)
                .accuracy(1.0)
                .requestURI("/test/api")
                .method("GET")
                .req(req.toString())
                .res(res.toString())
                .build();

        return ResponseEntity.ok()
                .body(aiResponseDto);
    }

    @GetMapping("ais")
    public ResponseEntity<AiListResponseDto> aiOnePage(@RequestHeader("Authorization") String userToken) {

        AiResponseDto aiResponseDto = AiResponseDto.builder()
                .field("vision")
                .name("vgg")
                .responseTime(0.1)
                .accuracy(1.0)
                .build();

        AiResponseDto aiResponseDto2 = AiResponseDto.builder()
                .field("nlp")
                .name("language")
                .responseTime(0.5)
                .accuracy(1.0)
                .build();

        List<AiResponseDto> aiResponseDtoList = new ArrayList<>();
        aiResponseDtoList.add(aiResponseDto);
        aiResponseDtoList.add(aiResponseDto2);


        AiListResponseDto aiListResponseDto = AiListResponseDto.builder()
                .aiList(aiResponseDtoList)
                .build();

        return ResponseEntity.ok()
                .body(aiListResponseDto);
    }
}
