package server.server.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import server.server.AwsS3.AwsS3Uploader;
import server.server.Domain.Dto.UsersDto;
import server.server.Service.AiApiFactory.AiFactory;
import server.server.Service.AiApiFactory.AiModel;
import server.server.Service.AiApiFactory.AiResponse;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AiApiController {
    private final AwsS3Uploader awsS3Uploader;
    private final AiFactory aiFactory;



    @PostMapping("/ai/{name}")
    public AiResponse response(@RequestParam(value = "email") String email,
                                  @RequestParam(value = "secretKey") String secretKey,
                                  @PathVariable(value = "name") String name,
                                  @RequestParam(value = "images") MultipartFile multipartFile) throws IOException {

        AiModel aiModel = aiFactory.getModel(name);

        aiModel.isValidUser(UsersDto.builder().email(email).secretKey(secretKey).build());

        return aiModel.response(awsS3Uploader.uploadFiles(multipartFile, "cowApi"));
    }
}
