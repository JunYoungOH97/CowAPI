package toyspringboot.server.AwsS3;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = {"AWS S3"})
@RequiredArgsConstructor
@RestController
public class AwsS3Controller {
    private final AwsS3Uploader s3Uploader;

    @ApiOperation(value = "AWS S3", notes = "AWS S3에 이미지를 저장하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PostMapping("/{userId}/image")
    public void updateUserImage(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        s3Uploader.uploadFiles(multipartFile, "Spring");
    }
}