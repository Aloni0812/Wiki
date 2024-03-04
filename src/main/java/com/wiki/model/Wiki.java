package com.wiki.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Wiki {
    private String requestWiki;
    private String answerWiki;
}
