package com.wiki.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Comment dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
  private Long id;
  @JsonProperty("author")
  private String author;
  @JsonProperty("text")
  private String text;
  @Nullable
  @JsonProperty("time")
  private LocalDateTime time = LocalDateTime.now();
}
