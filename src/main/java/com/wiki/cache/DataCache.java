package com.wiki.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataCache {
    private final Map<String,Object> heshmap= new ConcurrentHashMap<>();

    public void put(final String key, final Object toPut){
        heshmap.put(key, toPut);
    }

    public Object get(final String key){
        return heshmap.get(key);
    }

    public void clear() {
        heshmap.clear();
    }

    public void remove(String key){
        heshmap.remove(key);
    }
}
