package toyspringboot.server.AwsS3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class AwsS3Controller {
    private final AwsS3Uploader s3Uploader;

    @PostMapping("/{userId}/image")
    public void updateUserImage(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        s3Uploader.uploadFiles(multipartFile, "Spring");
    }
}