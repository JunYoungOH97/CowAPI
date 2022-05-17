package toyspringboot.server.Domain.Dto;


import lombok.*;
import toyspringboot.server.Domain.Entity.User;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String email;
    private String password;
    private Boolean admin;
    private Boolean isDeleted;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private Timestamp deletedDate;
    private String creator;
    private String updater;

    public static UserDto setCreatedUser(UserDto userDto) {
        userDto.setAdmin(false);
        userDto.setIsDeleted(false);
        userDto.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        userDto.setCreator("API");

        return userDto;
    }

    public static UserDto of(User userEntity) {
        return UserDto.builder()
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .admin(userEntity.getAdmin())
                .isDeleted(userEntity.getIsDeleted())
                .createdDate(userEntity.getCreatedDate())
                .updatedDate(userEntity.getUpdatedDate())
                .deletedDate(userEntity.getDeletedDate())
                .creator(userEntity.getCreator())
                .updater(userEntity.getUpdater())
                .build();

    }
}
