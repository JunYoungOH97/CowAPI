package toyspringboot.server.Controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import toyspringboot.server.AwsS3.AwsS3Uploader;
import toyspringboot.server.Domain.ResponseDto.AiResponseDto;
import toyspringboot.server.Service.AiService;

import java.io.IOException;

@Api(tags = {"AI"})
@RestController
@RequiredArgsConstructor
public class AiController {
    private final AiService aiService;
    private final AwsS3Uploader s3Uploader;

    @ApiOperation(value = "AI 카테고리", notes = "AI 카테고리 기능을 사용하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PostMapping("/{userId}/ai/category")
    public ResponseEntity<AiResponseDto> aiResponse(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        String s3Path = s3Uploader.uploadFiles(multipartFile, "Spring");
        return new ResponseEntity<>(aiService.responseAiResult(s3Path).toResponse(), HttpStatus.OK);
    }
}
