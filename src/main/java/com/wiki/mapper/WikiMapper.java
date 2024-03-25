package com.wiki.mapper;

import com.wiki.dto.WikiDto;
import com.wiki.model.Wiki;

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
         //    wiki.setId(wikiDto.getId());
             wiki.setRequestWiki(wikiDto.getRequestWiki());
             wiki.setAnswerWiki(wikiDto.getAnswerWiki());
             return wiki;
        }
        return null;
    }
}
