package com.wiki.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
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
    @Nullable
    @JsonProperty("time")
    private LocalDateTime time = LocalDateTime.now();
}
