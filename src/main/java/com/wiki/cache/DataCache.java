package com.wiki.cache;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import org.springframework.stereotype.Component;


@Component
public class DataCache {
  @Getter
  private static final int MAX_SIZE = 100;
  private final Map<String, Object> hashmap = new LinkedHashMap<>(MAX_SIZE,
          0.75f, true) {
    @Override
    protected boolean removeEldestEntry(final Map.Entry eldest) {
      return size() > MAX_SIZE;
    }
  };
  public void put(final String key, final Object toPut) {
    hashmap.put(key, toPut);
  }

  public Object get(final String key) {
    return hashmap.get(key);
  }

  public void clear() {
    hashmap.clear();
  }

  public void remove(final String key) {
    hashmap.remove(key);
  }
}
