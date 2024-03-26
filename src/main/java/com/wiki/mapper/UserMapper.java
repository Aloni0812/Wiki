package com.wiki.mapper;

import com.wiki.dto.UserDto;
import com.wiki.model.User;

public class UserMapper {
    private UserMapper() {
    }

    public static UserDto toDto(final User user) {
        if (user != null) {
            UserDto userDto = new UserDto();
            userDto.setName(user.getName());
            userDto.setAppMail(user.getAppMail());
            userDto.setPassword(user.getPassword());
            return userDto;
        }
        return null;
    }

    public static User toEntity(final UserDto userDto) {
        if (userDto != null) {
            User user = new User();
            user.setAppMail(userDto.getAppMail());
            user.setName(userDto.getName());
            user.setPassword(userDto.getPassword());
            return user;
        }
        return null;
    }
}
