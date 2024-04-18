package com.wiki.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Wiki dto.
 */
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
  private List<CommentDto> commentDtoList;
}
