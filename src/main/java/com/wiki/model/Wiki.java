package com.wiki.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wiki")
@Entity
public class Wiki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String requestWiki;
    @Column(columnDefinition = "text")
    private String answerWiki;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Comment> commentList;
}
