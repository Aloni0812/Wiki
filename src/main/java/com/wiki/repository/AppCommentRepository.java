package com.wiki.repository;

import com.wiki.model.AppComment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppCommentRepository extends JpaRepository<AppComment, Long> {
    AppComment findAppCommentById(Long id);
}
