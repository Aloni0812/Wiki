package com.wiki.repository;

import com.wiki.model.Wiki;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryWikiDAO {
        private final List<Wiki> wikiList = new ArrayList<>();

        public List<Wiki> findAllWiki() {
            return wikiList;
        }

        public Wiki saveWiki(Wiki wiki) {
            wikiList.add(wiki);
            return wiki;
        }

        public Wiki findByRequest(String request) {
            for (Wiki element : wikiList) {
                if (element.getRequestWiki().equals(request)) {
                    return element;
                }
            }
            return null;

        }

        public Wiki updateWiki(Wiki wiki) {
            int wikiIndex = -1;
            for (int i = 0; i < wikiList.size(); i++) {
                if (wikiList.get(i).getRequestWiki().equals(wiki.getRequestWiki())) {
                    wikiIndex = i;
                    break;
                }
            }
            if (wikiIndex > -1) {
                wikiList.set(wikiIndex, wiki);
                return wiki;
            }
            return null;
        }

        public void deleteWiki(String request) {
            var wiki = findByRequest(request);
            if (wiki != null) {
                wikiList.remove(wiki);
            }

        }
    }
