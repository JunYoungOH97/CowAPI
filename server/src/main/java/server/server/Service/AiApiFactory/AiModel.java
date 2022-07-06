package server.server.Service.AiApiFactory;

import server.server.Domain.Dto.UsersDto;

public interface AiModel {
    void isValidUser(UsersDto usersDto);
    AiResponse response(String s3Path);
}
