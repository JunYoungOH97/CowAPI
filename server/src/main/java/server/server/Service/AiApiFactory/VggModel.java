package server.server.Service.AiApiFactory;

import lombok.RequiredArgsConstructor;
import server.server.Domain.Dto.UsersDto;
import server.server.Service.AiApiService;

@RequiredArgsConstructor
public class VggModel implements AiModel {
    private final AiApiService aiApiService;

    @Override
    public void isValidUser(UsersDto usersDto) {
        aiApiService.isValidUser(usersDto);
    }

    @Override
    public AiResponse response(String s3Path) {
        return aiApiService.vggResponse("vgg", s3Path);
    }
}
