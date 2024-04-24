package com.wiki.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class WikiDtoTest {
  @Test
  public void testGetterAndSetter() {
    String requestWiki = "Cats";
    String answerWiki = "cat is not dog";
    WikiDto wikiDto = new WikiDto();
    wikiDto.setRequestWiki(requestWiki);
    wikiDto.setAnswerWiki(answerWiki);
    Assertions.assertEquals(requestWiki, wikiDto.getRequestWiki());
    Assertions.assertEquals(answerWiki, wikiDto.getAnswerWiki());
  }

  @Test
  public void testNoArgsConstructor() {
    WikiDto wikiDto = new WikiDto();
    Assertions.assertNull(wikiDto.getRequestWiki());
    Assertions.assertNull(wikiDto.getAnswerWiki());
    Assertions.assertNull(wikiDto.getCommentDtoList());
  }

  @Test
  public void testAllArgsConstructor() {
    String requestWiki = "Cats";
    String answerWiki = "Cat is not dog";
    List<CommentDto> commentDtoList = Arrays.asList(new CommentDto( 1l,"John", "Great article!", LocalDateTime.now()));
    WikiDto wikiDto = new WikiDto(requestWiki, answerWiki, commentDtoList);
    Assertions.assertEquals(requestWiki, wikiDto.getRequestWiki());
    Assertions.assertEquals(answerWiki, wikiDto.getAnswerWiki());
    Assertions.assertEquals(commentDtoList, wikiDto.getCommentDtoList());
  }
}
