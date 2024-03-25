package com.wiki.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppCommentDto {
    private Long id;
    private String author;
    private String text;
    private LocalDateTime time;
    private WikiDto wikiDto;
}
