package com.wiki.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WikiDto {
  @JsonIgnore
  private Long id;
  @JsonProperty("requestWiki")
  private String requestWiki;
  @JsonProperty("answerWiki")
  private String answerWiki;
  @JsonProperty("commentDtoList")
  private List<CommentDto> commentDtoList;
}
