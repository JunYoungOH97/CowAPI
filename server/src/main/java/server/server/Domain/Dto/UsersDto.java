package server.server.Domain.Dto;

import lombok.*;
import server.server.Domain.Entity.Users;
import server.server.Domain.ResposneDto.UserLoginResponseDto;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDto {
    private String email;
    private String password;
    private String secretKey;
    private Boolean isAdmin;
    private Boolean isDeleted;
    private String updater;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    private TokenDto userToken;

    public UserLoginResponseDto toResponse() {
        return UserLoginResponseDto.builder()
                .authorization(userToken.getAccessToken())
                .isAdmin(isAdmin)
                .build();
    }

    public void setCreatedUser() {
        String secretKey = new BigInteger(130, new SecureRandom()).toString(32);
        this.setIsAdmin(false);
        this.setIsDeleted(false);
        this.setUpdater("API");

        Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        this.setUpdatedAt(now);
        this.setCreatedAt(now);
        this.setSecretKey(secretKey);
    }

    public static UsersDto of(Users usersEntity) {
        return UsersDto.builder()
                .email(usersEntity.getEmail())
                .password(usersEntity.getPassword())
                .secretKey(usersEntity.getSecretKey())
                .isAdmin(usersEntity.getIsAdmin())
                .isDeleted(usersEntity.getIsDeleted())
                .createdAt(usersEntity.getCreatedAt())
                .updatedAt(usersEntity.getUpdatedAt())
                .deletedAt(usersEntity.getDeletedAt())
                .updater(usersEntity.getUpdater())
                .build();

    }

    public static Users toEntity(UsersDto usersDto) {
        return Users.builder()
                .email(usersDto.getEmail())
                .password(usersDto.getPassword())
                .secretKey(usersDto.getSecretKey())
                .isAdmin(usersDto.getIsAdmin())
                .isDeleted(usersDto.getIsDeleted())
                .createdAt(usersDto.getCreatedAt())
                .updatedAt(usersDto.getUpdatedAt())
                .deletedAt(usersDto.getDeletedAt())
                .updater(usersDto.getUpdater())
                .build();
    }
}
