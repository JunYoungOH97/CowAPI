package server.server.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import server.server.AwsS3.AwsS3Uploader;
import server.server.Domain.Dto.UsersDto;
import server.server.Domain.ResposneDto.VggResponseDto;
import server.server.Service.AiApiService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AiApiController {
    private final AwsS3Uploader awsS3Uploader;
    private final AiApiService aiApiService;

    @PostMapping("/ai/category")
    public Mono<VggResponseDto> vggResponse(@RequestParam("email") String email,
                                            @RequestParam("secretKey") String secretKey,
                                            @RequestParam("images") MultipartFile multipartFile) throws IOException {
//        secretKet(검증)
        aiApiService.isValidUser(UsersDto.builder().email(email).secretKey(secretKey).build());

        return aiApiService.vggResponse(awsS3Uploader.uploadFiles(multipartFile, "cowApi"));
    }
}
