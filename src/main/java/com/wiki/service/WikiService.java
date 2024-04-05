package com.wiki.service;

import com.wiki.cache.DataCache;
import com.wiki.dto.WikiDto;
import com.wiki.mapper.WikiMapper;
import com.wiki.model.Comment;
import com.wiki.model.Wiki;
import com.wiki.repository.CommentRepository;
import com.wiki.repository.WikiRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class WikiService {
    private final CommentRepository commentRepository;
    private final WikiRepository wikiRepository;
    private final DataCache wikiCache;

    public List<Wiki> findAllWiki() {
       // return wikiRepository.findAll();
        Object wikiObject = wikiCache.get("all");
        if(wikiObject instanceof List<?> list && list.get(0) instanceof Wiki && !list.isEmpty())
            return (List<Wiki>) list;
        wikiCache.put("all",wikiRepository.findAll());
        return wikiRepository.findAll();
    }

    public Wiki findByRequest(String requestWiki) {
    Object cacheObject=wikiCache.get(requestWiki);
    if(cacheObject instanceof Wiki wikiObject)
        return wikiObject;
    Wiki wiki=wikiRepository.findWikiByRequestWiki(requestWiki);
    wikiCache.put(requestWiki,wiki);
        return wiki;
    }

    public void deleteWiki(String requestWiki) {
        Wiki wikiDelete = wikiRepository.findWikiByRequestWiki(requestWiki);
        if (wikiDelete != null) {
            List<Comment> commentDelete = wikiDelete.getCommentList();
            if (commentDelete != null && !commentDelete.isEmpty()) {
                commentRepository.deleteAll(commentDelete);
            }
            wikiRepository.delete(wikiDelete);
            wikiCache.remove(wikiDelete.getRequestWiki());
        }
    }

    public Wiki updateWiki(WikiDto wikidto) {
        wikiRepository.findWikiByRequestWiki(wikidto.getRequestWiki()).setAnswerWiki(wikidto.getAnswerWiki());
        wikiRepository.findWikiByRequestWiki(wikidto.getRequestWiki()).setCommentList(WikiMapper.toEntity(wikidto).getCommentList());
        wikiCache.remove(wikidto.getRequestWiki());
        wikiCache.put(wikidto.getRequestWiki(),WikiMapper.toEntity(wikidto));
        return wikiRepository.findWikiByRequestWiki(wikidto.getRequestWiki());
    }

    public Wiki saveWiki(final WikiDto wikiDto) {
        Wiki wiki=WikiMapper.toEntity(wikiDto);
        wikiCache.put(wiki.getRequestWiki(),wiki);
        return wikiRepository.save(wiki);
    }
}
