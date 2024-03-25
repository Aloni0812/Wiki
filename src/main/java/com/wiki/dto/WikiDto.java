package com.wiki.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WikiDto {
    private Long id;
    private String requestWiki;
    private String answerWiki;
    private List<AppCommentDto> commentDtoList = new ArrayList<>();
    public List<AppCommentDto> getAppComment(){return commentDtoList;}
    public void setAppComment(List<AppCommentDto> commentDtoInput)
    {
        this.commentDtoList=commentDtoInput;
    }
}
