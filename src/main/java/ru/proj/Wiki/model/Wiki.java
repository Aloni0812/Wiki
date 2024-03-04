package ru.proj.Wiki.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Wiki {
    private String requestWiki;
    private String answerWiki;
}
