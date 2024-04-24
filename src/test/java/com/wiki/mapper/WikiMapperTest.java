package com.wiki.mapper;

import com.wiki.dto.CommentDto;
import com.wiki.dto.WikiDto;
import com.wiki.model.Comment;
import com.wiki.model.Wiki;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class WikiMapperTest {
  @Test
  void testToDto() {
    String requestWiki = "Cats";
    String answerWiki = "Cat is not dog";
    Wiki wiki = new Wiki();
    wiki.setRequestWiki(requestWiki);
    wiki.setAnswerWiki(answerWiki);
    WikiDto wikiDto = WikiMapper.toDto(wiki);
    Assertions.assertNotNull(wikiDto);
    Assertions.assertEquals(requestWiki, wikiDto.getRequestWiki());
    Assertions.assertEquals(answerWiki, wikiDto.getAnswerWiki());
  }
  @Test
  void testToDto_NullWiki() {
    WikiDto wikiDto = WikiMapper.toDto(null);
    Assertions.assertNull(wikiDto);
  }

  @Test
  void testToEntity() {
    String requestWiki = "Cats";
    String answerWiki = "Cat is not dog";
    List<CommentDto> commentDtoList = new ArrayList<>();
    CommentDto commentDto = new CommentDto();
    commentDto.setAuthor("Cats");
    commentDto.setText("Cats test");
    commentDtoList.add(commentDto);
    WikiDto wikiDto = new WikiDto();
    wikiDto.setRequestWiki(requestWiki);
    wikiDto.setAnswerWiki(answerWiki);
    wikiDto.setCommentDtoList(commentDtoList);
    Wiki wiki = WikiMapper.toEntity(wikiDto);
    Assertions.assertNotNull(wiki);
    Assertions.assertEquals(requestWiki, wiki.getRequestWiki());
    Assertions.assertEquals(answerWiki, wiki.getAnswerWiki());
    Assertions.assertNotNull(wiki.getCommentList());
    Assertions.assertEquals(commentDtoList.size(), wiki.getCommentList().size());
    Comment comment = wiki.getCommentList().get(0);
    Assertions.assertEquals(commentDto.getAuthor(), comment.getAuthor());
    Assertions.assertEquals(commentDto.getText(), comment.getText());
  }

  @Test
  void testToEntity_NullWikiDto() {
    Wiki wiki = WikiMapper.toEntity(null);
    Assertions.assertNull(wiki);
  }
}
