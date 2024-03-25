package com.wiki.mapper;

import com.wiki.dto.AppUserDto;
import com.wiki.model.AppUser;

public class AppUserMapper {
    private AppUserMapper() {
    }

    public static AppUserDto toDto(final AppUser appUser) {
        if (appUser != null) {
            AppUserDto appUserDto = new AppUserDto();
            appUserDto.setId(appUser.getId());
            appUserDto.setName(appUser.getName());
            appUserDto.setAppMail(appUser.getAppMail());
            appUserDto.setPassword(appUser.getPassword());
            return appUserDto;
        }
        return null;
    }

    public static AppUser toEntity(final AppUserDto appUserDto) {
        if (appUserDto != null) {
            AppUser appUser = new AppUser();
            appUser.setId(appUserDto.getId());
            appUser.setAppMail(appUserDto.getAppMail());
            appUser.setName(appUserDto.getName());
            appUser.setPassword(appUserDto.getPassword());
            return appUser;
        }
        return null;
    }
}
