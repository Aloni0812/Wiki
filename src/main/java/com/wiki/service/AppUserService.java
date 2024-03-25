package com.wiki.service;

import com.wiki.dto.AppUserDto;
import com.wiki.model.AppUser;
import com.wiki.repository.AppCommentRepository;
import com.wiki.repository.AppUserRepository;
import com.wiki.repository.WikiRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AppUserService {
    private final AppCommentRepository AppCommentRepository;
    private final AppUserRepository AppUserRepository;
    private final WikiRepository wikiRepository;

    private final List<AppUser> appUserList = new ArrayList<>();

    public List<AppUser> findAllAppUser() {
        return appUserList;
    }

    public AppUser saveAppUser(AppUser appUser) {
        appUserList.add(appUser);
        return appUser;
    }

    public AppUser findByAppMail(String appMail) {
        for (AppUser element : appUserList) {
            if (element.getAppMail().equals(appMail)) {
                return element;
            }
        }
        return null;

    }

    public AppUser updateAppUser(AppUser appUser) {
        int appUserIndex = -1;
        for (int i = 0; i < appUserList.size(); i++) {
            if (appUserList.get(i).getAppMail().equals(appUser.getAppMail())) {
                appUserIndex = i;
                break;
            }
        }
        if (appUserIndex > -1) {
            appUserList.set(appUserIndex, appUser);
            return appUser;
        }
        return null;
    }

    public void deleteAppUser(String appMail) {
        var appUser = findByAppMail(appMail);
        if (appUser != null) {
            appUserList.remove(appUser);
        }
    }
}
