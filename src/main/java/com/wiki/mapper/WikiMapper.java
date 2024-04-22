package com.wiki.mapper;

import com.wiki.dto.CommentDto;
import com.wiki.dto.WikiDto;
import com.wiki.model.Comment;
import com.wiki.model.Wiki;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Wiki mapper.
 */
public final class WikiMapper {
  private WikiMapper() {
  }

  /**
   * To dto wiki dto.
   *
   * @param wiki the wiki
   * @return the wiki dto
   */
  public static WikiDto toDto(final Wiki wiki) {
    if (wiki != null) {
      WikiDto wikiDto = new WikiDto();
      wikiDto.setRequestWiki(wiki.getRequestWiki());
      wikiDto.setAnswerWiki(wiki.getAnswerWiki());
      return wikiDto;
    }
    return null;
  }

  /**
   * To entity wiki.
   *
   * @param wikiDto the wiki dto
   * @return the wiki
   */
  public static Wiki toEntity(final WikiDto wikiDto) {
    if (wikiDto != null) {
      Wiki wiki = new Wiki();
      wiki.setRequestWiki(wikiDto.getRequestWiki());
      wiki.setAnswerWiki(wikiDto.getAnswerWiki());
      if (wikiDto.getCommentDtoList() == null) {
        return wiki;
      }
      List<Comment> commentList = new ArrayList<>();
      for (CommentDto commentDto : wikiDto.getCommentDtoList()) {
        commentList.add(CommentMapper.toEntity(commentDto));

      }
      wiki.setCommentList(commentList);
      return wiki;
    }
    return null;
  }
}
