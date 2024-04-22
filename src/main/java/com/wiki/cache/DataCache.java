package com.wiki.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;


/**
 * The type Data cache.
 */
@Component
public class DataCache {
  private static final int MAX_SIZE = 100;
  private final Map<String, Object> hashmap = new ConcurrentHashMap<>();

  /**
   * Put.
   *
   * @param key   the key
   * @param toPut the to put
   */
  public void put(final String key, final Object toPut) {
    if (hashmap.size() > MAX_SIZE) {
      hashmap.remove(hashmap.keySet().iterator().next());
    }
    hashmap.put(key, toPut);
  }

  /**
   * Get object.
   *
   * @param key the key
   * @return the object
   */
  public Object get(final String key) {
    return hashmap.get(key);
  }

  /**
   * Clear.
   */
  public void clear() {
    hashmap.clear();
  }

  /**
   * Remove.
   *
   * @param key the key
   */
  public void remove(final String key) {
    hashmap.remove(key);
  }
}
