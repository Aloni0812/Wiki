package com.wiki.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DataCacheTest {
  private  DataCache dataCache;
  @BeforeEach
  void setUp() {
    dataCache = new DataCache();
  }

  @Test
  void putAndGet() {
    String key = "key";
    String value = "value";

    dataCache.put(key, value);
    Object retrievedValue = dataCache.get(key);

    assertEquals(value, retrievedValue);
  }

  @Test
  void putMaxSizeReached() {
    for (int i = 0; i < DataCache.getMAX_SIZE(); i++) {
      String key = "key" + i;
      String value = "value" + i;
      dataCache.put(key, value);
    }

    String newKey = "newKey";
    String newValue = "newValue";
    dataCache.put(newKey, newValue);

    assertNull(dataCache.get("key0"));
    assertEquals(newValue, dataCache.get(newKey));
  }

  @Test
  void remove() {
    String key = "key";
    String value = "value";
    dataCache.put(key, value);

    dataCache.remove(key);

    assertNull(dataCache.get(key));
  }

  @Test
  void clear() {
    String key = "key";
    String value = "value";
    dataCache.put(key, value);

    dataCache.clear();

    assertNull(dataCache.get(key));
  }
}
