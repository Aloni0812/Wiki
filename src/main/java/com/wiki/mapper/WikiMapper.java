package com.wiki.mapper;

import com.wiki.dto.CommentDto;
import com.wiki.dto.WikiDto;
import com.wiki.model.Comment;
import com.wiki.model.Wiki;
import java.util.ArrayList;
import java.util.List;

public final class WikiMapper {
  private WikiMapper() {
  }
  public static WikiDto toDto(final Wiki wiki) {
    if (wiki != null) {
      WikiDto wikiDto = new WikiDto();
      wikiDto.setRequestWiki(wiki.getRequestWiki());
      wikiDto.setAnswerWiki(wiki.getAnswerWiki());
      wikiDto.setId(wiki.getId());
      List<CommentDto> commentList = new ArrayList<>();
      if (wiki.getCommentList() != null) {
        for (Comment comment : wiki.getCommentList()) {
          commentList.add(CommentMapper.toDto(comment));

        }
      }
      wikiDto.setCommentDtoList(commentList);
      return wikiDto;
    }
    return null;
  }

  public static Wiki toEntity(final WikiDto wikiDto) {
    if (wikiDto != null) {
      Wiki wiki = new Wiki();
      wiki.setRequestWiki(wikiDto.getRequestWiki());
      wiki.setAnswerWiki(wikiDto.getAnswerWiki());
      List<Comment> commentList = new ArrayList<>();
     // wiki.setId(wikiDto.getId());
      if (wikiDto.getCommentDtoList() != null) {


        for (CommentDto commentDto : wikiDto.getCommentDtoList()) {
          commentList.add(CommentMapper.toEntity(commentDto));

        }
      }
        wiki.setCommentList(commentList);

      return wiki;
    }
    return null;
  }
}
