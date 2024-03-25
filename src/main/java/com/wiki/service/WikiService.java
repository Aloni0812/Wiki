package com.wiki.service;

import com.wiki.dto.AppCommentDto;
import com.wiki.dto.WikiDto;
import com.wiki.mapper.AppCommentMapper;
import com.wiki.mapper.WikiMapper;
import com.wiki.model.AppComment;
import com.wiki.model.Wiki;
import com.wiki.repository.AppCommentRepository;
import com.wiki.repository.AppUserRepository;
import com.wiki.repository.WikiRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class WikiService {
    private final AppCommentRepository appCommentRepository;
    private final AppUserRepository appUserRepository;
    private final WikiRepository wikiRepository;
    private final List<Wiki> wikiList= new ArrayList<>();
    public List<Wiki> findAllWiki(){
        return wikiRepository.findAll();
    }

    public Wiki findByRequest(String requestWiki) {
        Wiki wiki = wikiRepository.findWikiByRequestWiki(requestWiki);
        return wiki;
    }

    public void deleteWiki(String requestWiki) {
        Wiki wikiDelete = wikiRepository.findWikiByRequestWiki(requestWiki);
        if (wikiDelete != null) {
            List<AppComment> appCommentDelete = wikiDelete.getAppCommentList();
            if (appCommentDelete != null && !appCommentDelete.isEmpty()) {
                appCommentRepository.deleteAll(appCommentDelete);
            }
            wikiRepository.delete(wikiDelete);
        }
    }


    public Wiki updateWiki(Wiki wiki) {
        int wikiIndex = -1;
        for (int i = 0; i < wikiList.size(); i++) {
            if (wikiList.get(i).getRequestWiki().equals(wiki.getRequestWiki())) {
                wikiIndex = i;
                break;
            }
        }
        if (wikiIndex > -1) {
            wikiList.set(wikiIndex, wiki);
            return wiki;
        }
        return null;
    }

    public Wiki saveWiki(final WikiDto wikiDto) {
        return wikiRepository.save(WikiMapper.toEntity(wikiDto));
    }

}
