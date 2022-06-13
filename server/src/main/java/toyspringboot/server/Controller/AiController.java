package toyspringboot.server.Controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
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


    @PostMapping("/{userId}/ai/result")
    public Mono<AiDto> aiResponse(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        String s3Path = s3Uploader.uploadFiles(multipartFile, "Spring");
        return aiService.responseAiResult(s3Path);
    }
}
