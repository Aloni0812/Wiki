package com.wiki.service;

import com.wiki.cache.DataCache;
import com.wiki.dto.WikiDto;
import com.wiki.mapper.WikiMapper;
import com.wiki.model.Comment;
import com.wiki.model.Wiki;
import com.wiki.repository.CommentRepository;
import com.wiki.repository.WikiRepository;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class WikiService {
  private final CommentRepository commentRepository;
  private WikiRepository wikiRepository;
  private final DataCache wikiCache;

  public List<Wiki> findAllWiki() {
   /* Object wikiObject = wikiCache.get("all");
    if (wikiObject instanceof List<?> list && list.get(0)
            instanceof Wiki && !list.isEmpty()) {
      log.info("Loading data from the cache");
      return (List<Wiki>) list;
    }
    wikiCache.put("all", wikiRepository.findAll());*/
    log.info("Loading data from databases");
    log.info("Loading Cache from databases");
    return wikiRepository.findAll();
  }

  public Wiki findByRequest(final String requestWiki) {
 /*   Object cacheObject = wikiCache.get(requestWiki);
    if (cacheObject instanceof Wiki wikiObject) {
      return wikiObject;
    }*/
    Wiki wiki = wikiRepository.findWikiByRequestWiki(requestWiki);
  //  wikiCache.put(requestWiki, wiki);
   // log.info("Found wiki by request {}", wiki.getRequestWiki());
    return wiki;
  }

  public void deleteWiki(final String requestWiki) {
    Wiki wikiDelete = wikiRepository.findWikiByRequestWiki(requestWiki);
    if (wikiDelete != null) {
      List<Comment> commentDelete = wikiDelete.getCommentList();
      if (commentDelete != null && !commentDelete.isEmpty()) {
        commentRepository.deleteAll(commentDelete);
      }
      log.info("Deleted wiki by request {}", wikiDelete.getRequestWiki());
      wikiRepository.delete(wikiDelete);
    }
  }

  public Wiki updateWiki(final WikiDto wikidto) {
    wikiRepository.findWikiById(wikidto.getId())
                    .setRequestWiki(wikidto.getRequestWiki());
    wikiRepository.findWikiById(wikidto.getId())
            .setAnswerWiki(wikidto.getAnswerWiki());
    wikiRepository.findWikiById(wikidto.getId())
            .setCommentList(WikiMapper.toEntity(wikidto).getCommentList());
   // wikiCache.remove(wikidto.getRequestWiki());
    //wikiCache.put(wikidto.getRequestWiki(), WikiMapper.toEntity(wikidto));
    //log.info("Updated wiki with request {}", wikidto.getRequestWiki());
    return wikiRepository.findWikiById(wikidto.getId());
  }

  public Wiki saveWiki(final WikiDto wikiDto) {

    Wiki wiki = WikiMapper.toEntity(wikiDto);
    if(wiki.getCommentList()==null) {
      List<Comment> commentList = new ArrayList<>();
      wiki.setCommentList(commentList);
    }
    log.info("Saved wiki with request {}", wiki.getRequestWiki());
    return wikiRepository.save(wiki);
  }

  public Wiki findByRequestWikiAndAuthor(
          @Param("requestWiki") final String requestWiki,
          @Param("author") final String author) {
    String cacheKey = requestWiki + "_" + author;
    Object cachedObject = wikiCache.get(cacheKey);
    if (cachedObject instanceof Wiki wiki) {
      log.info("Found wiki by request: {} and author {}", requestWiki, author);
      return wiki;
    }
    Wiki wiki = wikiRepository
            .findWikiByRequestWikiAndAuthor(requestWiki, author);
    wikiCache.put(cacheKey, wiki);
    log.info("Found wiki by request: {} and author {}", requestWiki, author);
    return wiki;
  }

  public List<Wiki> bulkSaveWiki(final List<WikiDto> wikiDtoList) {
    List<Wiki> wikisListToSave = new ArrayList<>();
    for (WikiDto wikiDto : wikiDtoList) {
      Wiki wikiFind = wikiRepository
              .findWikiByRequestWiki(wikiDto.getRequestWiki());
      if (wikiFind != null) {
        wikiCache.remove(wikiFind.getRequestWiki());
        wikiCache.put("all", wikiFind);

      } else {
        wikiCache.put("all", wikiDto);
        wikisListToSave.add(WikiMapper.toEntity(wikiDto));
      }
    }
    List<Wiki> returnList = new ArrayList<>();
    for (WikiDto wikiDto : wikiDtoList) {
      returnList.add(WikiMapper.toEntity(wikiDto));
    }
    return wikiRepository.saveAll(wikisListToSave);
  }

  public Wiki findWikiId(final Long id) {
    return Optional.ofNullable(wikiCache.get(id.toString()))
            .filter(Wiki.class::isInstance)
            .map(Wiki.class::cast)
            .orElseGet(() -> {
              Wiki wiki = wikiRepository.findWikiById(id);
             // wikiCache.put(id.toString(), wiki);
              //log.info("Found wiki by id {}", id);
              return wiki;
            });
  }
}