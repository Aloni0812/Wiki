package com.wiki.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WikiDto {
    @JsonProperty("requestWiki")
    private String requestWiki;
    @JsonProperty("answerWiki")
    private String answerWiki;
    @JsonProperty("commentDtoList")
    private List<AppCommentDto> commentDtoList;
}
