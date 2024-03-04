package ru.proj.Wiki.servise;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.proj.Wiki.model.Wiki;

import java.util.List;

@SpringBootApplication
public interface WikiServise {


    List<Wiki> findAllWiki();

    Wiki saveWiki(Wiki wiki);

    Wiki findByRequest(String request);

    Wiki updateWiki(Wiki wiki);

    void deleteWiki(String request);
}
