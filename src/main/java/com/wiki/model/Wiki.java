package com.wiki.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "wiki")
@Entity
public class Wiki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String requestWiki;
    @Column(columnDefinition = "text")
    private String answerWiki;
    @OneToMany(mappedBy = "wiki",cascade = CascadeType.ALL)
    private List<AppComment> appCommentList;

    public void addAppComment(AppComment appComment){
        appCommentList.add(appComment);/// TODO: 25.03.2024  
        appComment.setWiki(this);
    }

    public  void removeAppComment(AppComment appComment){
        appCommentList.remove(appComment);
        appComment.setWiki(null);
    }

   // @ManyToMany(mappedBy = "wiki")
   // private List<AppUser> appUsers = new ArrayList<>();
}
