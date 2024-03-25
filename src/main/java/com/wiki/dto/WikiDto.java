package com.wiki.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WikiDto {
    @JsonProperty("requestWiki")
    private String requestWiki;
    @JsonProperty("answerWiki")
    private String answerWiki;
    @JsonProperty("commentDtoList")
    private List<AppCommentDto> commentDtoList;
    public List<AppCommentDto> getAppComment(){return commentDtoList;}
    public void setAppComment(List<AppCommentDto> commentDtoInput)
    {
        this.commentDtoList=commentDtoInput;
    }
}
