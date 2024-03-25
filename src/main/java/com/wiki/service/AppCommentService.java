package com.wiki.service;

import com.wiki.dto.AppCommentDto;
import com.wiki.mapper.AppCommentMapper;
import com.wiki.model.AppComment;
import com.wiki.model.Wiki;
import com.wiki.repository.AppCommentRepository;
import com.wiki.repository.AppUserRepository;
import com.wiki.repository.WikiRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class AppCommentService {
    private final AppCommentRepository appCommentRepository;
    private final AppUserRepository appUserRepository;
    private final WikiRepository wikiRepository;

    private final List<AppComment> appCommentList = new ArrayList<>();

    public List<AppComment> findAllAppComment() {
        return appCommentList;
    }

    public AppComment findAppComment(Long id) {
        AppComment appComment = appCommentRepository.findAppCommentById(id);
        return appComment;
    }

    public void deleteAppComment(Long id) {
        AppComment appComment = findAppComment(id);
        if (appComment != null)
            appCommentList.remove(appComment);
    }


    public AppComment updateAppComment(AppComment appComment) {
        int appCommentIndex = -1;
        for (int i = 0; i < appCommentList.size(); i++) {
            if (appCommentList.get(i).getId().equals(appComment.getId())) {
                appCommentIndex = i;
                break;
            }
        }
        if (appCommentIndex > -1) {
            appCommentList.set(appCommentIndex, appComment);
            return appComment;
        }
        return null;
    }

    public AppComment saveAppComment(AppCommentDto appCommentDto) {
        return appCommentRepository.save(Objects.requireNonNull(AppCommentMapper.toEntity(appCommentDto)));
    }
}
