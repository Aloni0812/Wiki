/*package com.wiki.service;

import com.wiki.cache.DataCache;
import com.wiki.dto.WikiDto;
import com.wiki.exceptions.ExceptionsHandler;
import com.wiki.model.Comment;
import com.wiki.model.Wiki;
import com.wiki.repository.CommentRepository;
import com.wiki.repository.WikiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class WikiServiceTest {
  @Mock
  private ExceptionsHandler exceptionsHandler;
  @Mock
  private CommentRepository commentRepository;
  @Mock
  private WikiRepository wikiRepository;
  @Mock
  private DataCache wikiCache;

  private WikiService wikiService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    wikiService = new WikiService(exceptionsHandler, commentRepository, wikiRepository, wikiCache);
  }

  @Test
  void findAllWiki_shouldReturnListOfWiki() {
    List<Wiki> wikiList = new ArrayList<>();
    when(wikiRepository.findAll()).thenReturn(wikiList);

    wikiService.findAllWiki();

    verify(wikiCache, times(2)).get("all");
    verify(wikiRepository).findAll();
    verify(wikiCache).put("all", wikiList);
  }

  @Test
  void findByRequest_shouldReturnWikiWithGivenRequest() {
    String requestWiki = "testRequest";
    Wiki wiki = new Wiki();
    when(wikiCache.get(requestWiki)).thenReturn(null);
    when(wikiRepository.findWikiByRequestWiki(requestWiki)).thenReturn(wiki);

    wikiService.findByRequest(requestWiki);

    verify(wikiCache).get(requestWiki);
    verify(wikiRepository).findWikiByRequestWiki(requestWiki);
    verify(wikiCache).put(requestWiki, wiki);
  }

  @Test
  void deleteWiki_shouldDeleteWikiAndComments() {
    String requestWiki = "testRequest";
    Wiki wiki = new Wiki();
    List<Comment> commentList = new ArrayList<>();
    wiki.setCommentList(commentList);
    when(wikiRepository.findWikiByRequestWiki(requestWiki)).thenReturn(wiki);

    wikiService.deleteWiki(requestWiki);

    verify(commentRepository).deleteAll(commentList);
    verify(wikiRepository).delete(wiki);
    verify(wikiCache).remove(requestWiki);
  }

  @Test
  void updateWiki_shouldUpdateWikiAndCache() {
    String requestWiki = "testRequest";
    WikiDto wikiDto = new WikiDto();
    Wiki wiki = new Wiki();
    when(wikiRepository.findWikiByRequestWiki(requestWiki)).thenReturn(wiki);

    wikiService.updateWiki(wikiDto);

    verify(wikiRepository, times(2)).findWikiByRequestWiki(requestWiki);
    verify(wikiRepository).save(wiki);
    verify(wikiCache).remove(requestWiki);
    verify(wikiCache).put(requestWiki, wiki);
  }

  @Test
  void saveWiki_shouldSaveWikiAndPutInCache() {
    WikiDto wikiDto = new WikiDto();
    Wiki wiki = new Wiki();

    when(wikiRepository.save(any(Wiki.class))).thenReturn(wiki);

    wikiService.saveWiki(wikiDto);

    verify(wikiCache).put(eq("all"), any(Wiki.class));
    verify(wikiRepository).save(any(Wiki.class));
  }

  @Test
  void findByRequestWikiAndAuthor_shouldReturnWikiWithGivenRequestAndAuthor() {
    String requestWiki = "testRequest";
    String author = "testAuthor";
    String cacheKey = requestWiki + "_" + author;
    Wiki wiki = new Wiki();

    when(wikiCache.get(cacheKey)).thenReturn(null);
    when(wikiRepository.findWikiByRequestWikiAndAuthor(requestWiki, author)).thenReturn(wiki);

    wikiService.findByRequestWikiAndAuthor(requestWiki, author);

    verify(wikiCache).get(cacheKey);
    verify(wikiRepository).findWikiByRequestWikiAndAuthor(requestWiki, author);
    verify(wikiCache).put(cacheKey, wiki);
  }
}*/