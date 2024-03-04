package com.wiki.servise;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.wiki.model.Wiki;

import java.util.List;

@SpringBootApplication
public interface WikiServise {


    List<Wiki> findAllWiki();

    Wiki saveWiki(Wiki wiki);

    Wiki findByRequest(String request);

    Wiki updateWiki(Wiki wiki);

    void deleteWiki(String request);
}
