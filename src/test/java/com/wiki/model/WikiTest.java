package com.wiki.model;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WikiTest {
  @Test
  void testWikiConstructor() {
    Long id = 1L;
    String requestWiki = "Cats";
    String answerWiki = "Test";
    List<Comment> commentList = new ArrayList<>();
    Wiki wiki = new Wiki(id, requestWiki, answerWiki, commentList);
    assertNotNull(wiki);
    assertEquals(id, wiki.getId());
    assertEquals(requestWiki, wiki.getRequestWiki());
    assertEquals(answerWiki, wiki.getAnswerWiki());
    assertEquals(commentList, wiki.getCommentList());
  }

  @Test
  void testWikiGettersAndSetters() {
    Wiki wiki = new Wiki();
    Long id = 1L;
    String requestWiki = "Cats";
    String answerWiki = "Test";
    List<Comment> commentList = new ArrayList<>();
    wiki.setId(id);
    wiki.setRequestWiki(requestWiki);
    wiki.setAnswerWiki(answerWiki);
    wiki.setCommentList(commentList);
    assertEquals(id, wiki.getId());
    assertEquals(requestWiki, wiki.getRequestWiki());
    assertEquals(answerWiki, wiki.getAnswerWiki());
    assertEquals(commentList, wiki.getCommentList());
  }
}
