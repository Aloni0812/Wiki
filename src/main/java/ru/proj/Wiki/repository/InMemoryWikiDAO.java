package ru.proj.Wiki.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import ru.proj.Wiki.model.Wiki;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryWikiDAO {
        private final List<Wiki> WIKI = new ArrayList<>();

        public List<Wiki> findAllWiki() {
            return WIKI;
        }

        public Wiki saveWiki(Wiki wiki) {
            WIKI.add(wiki);
            return wiki;
        }

        public Wiki findByRequest(String request) {
            for (Wiki element : WIKI) {
                if (element.getRequestWiki().equals(request)) {
                    return element;
                }
            }
            return null;

        }

        public Wiki updateWiki(Wiki Wiki) {
            int wikiIndex = -1;
            for (int i = 0; i < WIKI.size(); i++) {
                if (WIKI.get(i).getRequestWiki().equals(Wiki.getRequestWiki())) {
                    wikiIndex = i;
                    break;
                }
            }
            if (wikiIndex > -1) {
                WIKI.set(wikiIndex, Wiki);
                return Wiki;
            }
            return null;
        }

        public void deleteWiki(String request) {
            var student = findByRequest(request);
            if (student != null) {
                WIKI.remove(student);
            }

        }
    }
