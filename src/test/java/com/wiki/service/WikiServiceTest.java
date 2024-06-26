package com.wiki.service;
import com.wiki.cache.DataCache;
import com.wiki.dto.WikiDto;
import com.wiki.mapper.WikiMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
class WikiServiceTest {
  private WikiService wikiService;

  @Mock
  private CommentRepository commentRepository;

  @Mock
  private WikiRepository wikiRepository;

  @Mock
  private DataCache wikiCache;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
    wikiService = new WikiService(commentRepository, wikiRepository, wikiCache);
  }

  @Test
   void testFindAllWiki_FromCache() {
    List<Wiki> wikiList = new ArrayList<>();
    wikiList.add(new Wiki());
    when(wikiCache.get("all")).thenReturn(wikiList);

    List<Wiki> result = wikiService.findAllWiki();

    assertEquals(wikiList, result);
  }

  @Test
  void testFindAllWiki_FromDatabase() {
    List<Wiki> wikiList = new ArrayList<>();
    wikiList.add(new Wiki());
    when(wikiRepository.findAll()).thenReturn(wikiList);
    when(wikiCache.get("all")).thenReturn(null);

    List<Wiki> result = wikiService.findAllWiki();

    assertEquals(wikiList, result);
    verify(wikiCache).put("all", wikiList);
  }

  @Test
  void testFindByRequest_FromCache() {
    String requestWiki = "test";
    Wiki wiki = new Wiki();
    when(wikiCache.get(requestWiki)).thenReturn(wiki);

    Wiki result = wikiService.findByRequest(requestWiki);

    assertEquals(wiki, result);
  }

  @Test
  void testFindByRequest_FromDatabase() {
    String requestWiki = "test";
    Wiki wiki = new Wiki();
    when(wikiRepository.findWikiByRequestWiki(requestWiki)).thenReturn(wiki);
    when(wikiCache.get(requestWiki)).thenReturn(null);
    Wiki result = wikiService.findByRequest(requestWiki);
    assertEquals(wiki, result);
    verify(wikiCache).put(requestWiki, wiki);
  }

  @Test
  void testDeleteWiki_WikiNotFound() {
    String requestWiki = "test";
    when(wikiRepository.findWikiByRequestWiki(requestWiki)).thenReturn(null);
    wikiService.deleteWiki(requestWiki);
    verify(wikiRepository).findWikiByRequestWiki(requestWiki);
  }

  @Test
  void testDeleteWiki_WikiFound() {
    String requestWiki = "test";
    Wiki wiki = new Wiki();
    List<Comment> commentList = new ArrayList<>();
    commentList.add(new Comment());
    wiki.setCommentList(commentList);
    when(wikiRepository.findWikiByRequestWiki(requestWiki)).thenReturn(wiki);
    wikiService.deleteWiki(requestWiki);
    verify(wikiRepository).findWikiByRequestWiki(requestWiki);
    verify(commentRepository).deleteAll(commentList);
    verify(wikiRepository).delete(wiki);
  }

  @Test
  void testUpdateWiki() {
    String requestWiki = "test";
    String updatedAnswer = "Updated";
    WikiDto wikiDto = new WikiDto();
    wikiDto.setRequestWiki(requestWiki);
    wikiDto.setAnswerWiki(updatedAnswer);
    Wiki wiki = new Wiki();
    when(wikiRepository.findWikiByRequestWiki(requestWiki)).thenReturn(wiki);
    Wiki result = wikiService.updateWiki(wikiDto);
    assertEquals(updatedAnswer, result.getAnswerWiki());
    verify(wikiCache).remove(requestWiki);
  }

  @Test
  void testSaveWiki() {
    WikiDto wikiDto = new WikiDto();
    Wiki wiki = WikiMapper.toEntity(wikiDto);
    when(wikiRepository.save(wiki)).thenReturn(wiki);
    Wiki result = wikiService.saveWiki(wikiDto);
    assertEquals(wikiDto.getAnswerWiki(), wikiDto.getAnswerWiki());
  }

  @Test
  void testFindByRequestWikiAndAuthor_FromCache() {
    String requestWiki = "test";
    String author = "Sasha";
    String cacheKey = requestWiki + "_" + author;
    Wiki wiki = new Wiki();
    when(wikiCache.get(cacheKey)).thenReturn(wiki);
    Wiki result = wikiService.findByRequestWikiAndAuthor(requestWiki, author);
    assertEquals(wiki, result);
  }

  @Test
  void testFindByRequestWikiAndAuthor_FromDatabase() {
    String requestWiki = "test";
    String author = "Sasha";
    String cacheKey = requestWiki + "_" + author;
    Wiki wiki = new Wiki();
    when(wikiRepository.findWikiByRequestWikiAndAuthor(requestWiki, author)).thenReturn(wiki);
    when(wikiCache.get(cacheKey)).thenReturn(null);
    Wiki result = wikiService.findByRequestWikiAndAuthor(requestWiki, author);
    assertEquals(wiki, result);
    verify(wikiCache).put(cacheKey, wiki);
  }

  @Test
  void testBulkSaveWiki() {
    ArrayList<WikiDto> wikiDtoList = new ArrayList<>();
    WikiDto wikiDto1 = new WikiDto();
    wikiDto1.setRequestWiki("Test 1");
    WikiDto wikiDto2 = new WikiDto();
    wikiDto2.setRequestWiki("Test 2");
    wikiDtoList.add(wikiDto1);
    wikiDtoList.add(wikiDto2);
    Wiki existingWiki = new Wiki();
    existingWiki.setRequestWiki("Test 1");
    when(wikiRepository.findWikiByRequestWiki("Test 1")).thenReturn(existingWiki);
    List<Wiki> result = wikiService.bulkSaveWiki(wikiDtoList);
    verify(wikiRepository, times(1)).findWikiByRequestWiki("Test 1");
    verify(wikiRepository, times(1)).saveAll(anyList());
    verify(wikiCache, times(1)).remove("Test 1");
    verify(wikiCache, times(1)).put("all", existingWiki);
  }
}