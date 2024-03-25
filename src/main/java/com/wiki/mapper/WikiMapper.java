package com.wiki.mapper;

import com.wiki.dto.AppCommentDto;
import com.wiki.dto.WikiDto;
import com.wiki.model.AppComment;
import com.wiki.model.Wiki;

import java.util.ArrayList;
import java.util.List;

public class WikiMapper {
    private WikiMapper(){}
    public static WikiDto toDto(final Wiki wiki){
        if (wiki !=null){
            WikiDto wikiDto = new WikiDto();
        //    wikiDto.setId(wiki.getId());
            wikiDto.setRequestWiki(wiki.getRequestWiki());
            wikiDto.setAnswerWiki(wiki.getAnswerWiki());
            return wikiDto;
        }
        return null;
    }

    public static Wiki toEntity(final WikiDto wikiDto) {
        if (wikiDto != null) {
             Wiki wiki = new Wiki();
             wiki.setRequestWiki(wikiDto.getRequestWiki());
             wiki.setAnswerWiki(wikiDto.getAnswerWiki());
             if (wikiDto.getAppComment()==null)
             return wiki;
            List<AppComment> appCommentList = new ArrayList<>();
            for(AppCommentDto appCommentDto: wikiDto.getAppComment()){
                appCommentList.add(AppCommentMapper.toEntity(appCommentDto));
            }
            wiki.setAppCommentList(appCommentList);
            return wiki;
        }
        return null;
    }
}
