package com.wiki.service;

import com.wiki.dto.WikiDto;
import com.wiki.mapper.WikiMapper;
import com.wiki.model.Comment;
import com.wiki.model.Wiki;
import com.wiki.repository.CommentRepository;
import com.wiki.repository.UserRepository;
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
    private final UserRepository UserRepository;
    private final WikiRepository wikiRepository;

    public List<Wiki> findAllWiki() {
        return wikiRepository.findAll();
    }

    public Wiki findByRequest(String requestWiki) {
        return wikiRepository.findWikiByRequestWiki(requestWiki);
    }

    public void deleteWiki(String requestWiki) {
        Wiki wikiDelete = wikiRepository.findWikiByRequestWiki(requestWiki);
        if (wikiDelete != null) {
            List<Comment> commentDelete = wikiDelete.getCommentList();
            if (commentDelete != null && !commentDelete.isEmpty()) {
                commentRepository.deleteAll(commentDelete);
            }
            wikiRepository.delete(wikiDelete);
        }
    }

    public Wiki updateWiki(WikiDto wikidto) {
        wikiRepository.findWikiByRequestWiki(wikidto.getRequestWiki()).setAnswerWiki(wikidto.getAnswerWiki());
        wikiRepository.findWikiByRequestWiki(wikidto.getRequestWiki()).setCommentList(WikiMapper.toEntity(wikidto).getCommentList());
        return wikiRepository.findWikiByRequestWiki(wikidto.getRequestWiki());
    }

    public Wiki saveWiki(final WikiDto wikiDto) {
        return wikiRepository.save(WikiMapper.toEntity(wikiDto));
    }

    public Wiki getWikiWithCommentID(Long id) {
        Comment comment = commentRepository.findCommentById(id);
        return wikiRepository.findWikiByCommentListContains(comment);
    }

}
