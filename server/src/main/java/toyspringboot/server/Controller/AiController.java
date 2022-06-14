package toyspringboot.server.Controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import toyspringboot.server.AwsS3.AwsS3Uploader;
import toyspringboot.server.Domain.Dto.AiDto;
import toyspringboot.server.Domain.Dto.DashboardDto;
import toyspringboot.server.Service.AiService;
import toyspringboot.server.Service.RedisService;

import java.io.IOException;

@Api(tags = {"AI api"})
@RestController
@RequiredArgsConstructor
public class AiController {
    private final AiService aiService;
    private final AwsS3Uploader s3Uploader;


    @PostMapping("/{userId}/ai/category")
    public ResponseEntity<AiDto> aiResponse(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        System.out.println("multipartFile = " + multipartFile.getOriginalFilename());
        String s3Path = s3Uploader.uploadFiles(multipartFile, "Spring");
        AiDto aiDto = aiService.responseAiResult(s3Path);
        System.out.println("multipartFile = " + aiDto.getCategory());

        return new ResponseEntity<>(aiService.responseAiResult(s3Path), HttpStatus.OK);
    }
}
