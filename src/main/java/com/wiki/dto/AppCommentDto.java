package com.wiki.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppCommentDto {
    @JsonProperty("author")
    private String author;
    @JsonProperty("text")
    private String text;
    @JsonProperty("time")
    private LocalDateTime time=LocalDateTime.now();
    @JsonProperty("wikiDto")
    private WikiDto wikiDto;
}
