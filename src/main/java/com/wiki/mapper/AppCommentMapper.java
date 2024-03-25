package com.wiki.mapper;

import com.wiki.dto.AppCommentDto;
import com.wiki.model.AppComment;

public class AppCommentMapper {
    private AppCommentMapper(){}
    public static AppCommentDto toDto(final AppComment appComment){
        if (appComment !=null){
            AppCommentDto appCommentDto = new AppCommentDto();
            appCommentDto.setAuthor(appComment.getAuthor());
            appCommentDto.setText(appComment.getText());
            appCommentDto.setTime(appComment.getTime());
            return appCommentDto;
        }
        return null;
    }

    public static AppComment toEntity(final AppCommentDto appCommentDto) {
        if (appCommentDto != null) {
            AppComment appComment = new AppComment();
            appComment.setAuthor(appCommentDto.getAuthor());
            appComment.setText(appCommentDto.getText());
            appComment.setTime(appCommentDto.getTime());
            return appComment;
        }
        return null;
    }
}
