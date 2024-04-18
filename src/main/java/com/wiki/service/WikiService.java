package com.wiki.service;

import com.wiki.cache.DataCache;
import com.wiki.dto.WikiDto;
import com.wiki.exceptions.ExceptionsHandler;
import com.wiki.mapper.WikiMapper;
import com.wiki.model.Comment;
import com.wiki.model.Wiki;
import com.wiki.repository.CommentRepository;
import com.wiki.repository.WikiRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 * The type Wiki service.
 */
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class WikiService {
  private final ExceptionsHandler exceptionsHandler;
  private final CommentRepository commentRepository;
  private final WikiRepository wikiRepository;
  private final DataCache wikiCache;

  /**
   * Find all wiki list.
   *
   * @return the list
   */
  public List<Wiki> findAllWiki() {
    Object wikiObject = wikiCache.get("all");
    if (wikiObject instanceof List<?> list && list.get(0) instanceof Wiki && !list.isEmpty()) {
      log.info("Loading data from the cache");
      return (List<Wiki>) list;
    }
    wikiCache.put("all", wikiRepository.findAll());
    log.info("Loading data from databases");
    log.info("Loading Cache from databases");
    return wikiRepository.findAll();
  }

  /**
   * Find by request wiki.
   *
   * @param requestWiki the request wiki
   * @return the wiki
   */
  public Wiki findByRequest(String requestWiki) {
    Object cacheObject = wikiCache.get(requestWiki);
    if (cacheObject instanceof Wiki wikiObject) {
      return wikiObject;
    }
    Wiki wiki = wikiRepository.findWikiByRequestWiki(requestWiki);
    wikiCache.put(requestWiki, wiki);
    log.info("Found wiki by request {}", wiki.getRequestWiki());
    return wiki;
  }

  /**
   * Delete wiki.
   *
   * @param requestWiki the request wiki
   */
  public void deleteWiki(String requestWiki) {
    Wiki wikiDelete = wikiRepository.findWikiByRequestWiki(requestWiki);
    if (wikiDelete != null) {
      List<Comment> commentDelete = wikiDelete.getCommentList();
      if (commentDelete != null && !commentDelete.isEmpty()) {
        commentRepository.deleteAll(commentDelete);
      }
      log.info("Deleted wiki by request {}", wikiDelete.getRequestWiki());
      wikiRepository.delete(wikiDelete);
      wikiCache.remove(wikiDelete.getRequestWiki());
    }
  }

  /**
   * Update wiki wiki.
   *
   * @param wikidto the wikidto
   * @return the wiki
   */
  public Wiki updateWiki(WikiDto wikidto) {
    wikiRepository.findWikiByRequestWiki(wikidto.getRequestWiki())
            .setAnswerWiki(wikidto.getAnswerWiki());
    wikiRepository.findWikiByRequestWiki(wikidto.getRequestWiki())
            .setCommentList(WikiMapper.toEntity(wikidto).getCommentList());
    wikiCache.remove(wikidto.getRequestWiki());
    wikiCache.put(wikidto.getRequestWiki(), WikiMapper.toEntity(wikidto));
    log.info("Updated wiki with request {}", wikidto.getRequestWiki());
    return wikiRepository.findWikiByRequestWiki(wikidto.getRequestWiki());
  }

  /**
   * Save wiki wiki.
   *
   * @param wikiDto the wiki dto
   * @return the wiki
   */
  public Wiki saveWiki(final WikiDto wikiDto) {
    Wiki wiki = WikiMapper.toEntity(wikiDto);
    wikiCache.put("all", wiki);
    log.info("Saved wiki with request {}", wiki.getRequestWiki());
    return wikiRepository.save(wiki);
  }

  /**
   * Find by request wiki and author wiki.
   *
   * @param requestWiki the request wiki
   * @param author      the author
   * @return the wiki
   */
  public Wiki findByRequestWikiAndAuthor(@Param("requestWiki") String requestWiki,
                                         @Param("requestWiki") String author) {
    String cacheKey = requestWiki + "_" + author;
    Object cachedObject = wikiCache.get(cacheKey);
    if (cachedObject instanceof Wiki wiki) {
      log.info("Found wiki by request: {} and author {}", requestWiki, author);
      return wiki;
    }
    Wiki wiki = wikiRepository.findWikiByRequestWikiAndAuthor(requestWiki, author);
    wikiCache.put(cacheKey, wiki);
    log.info("Found wiki by request: {} and author {}", requestWiki, author);
    return wiki;
  }
}
