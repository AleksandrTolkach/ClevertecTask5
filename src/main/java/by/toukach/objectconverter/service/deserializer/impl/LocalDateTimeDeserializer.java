package by.toukach.objectconverter.service.deserializer.impl;

import by.toukach.objectconverter.service.deserializer.Deserializer;
import by.toukach.objectconverter.util.SymbolUtil;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Класс для десериализации LocalDateTime.
 */
public class LocalDateTimeDeserializer implements Deserializer {

  @Override
  public Object deserialize(Object object, Class<?> type) {
    Map<String, String> localDateTimeMap = (Map<String, String>) object;

    return LocalDateTime.parse(localDateTimeMap.entrySet().stream()
        .map(Entry::getValue)
        .findFirst()
        .map(s -> s.replace(SymbolUtil.EMPTY_SPACE.charAt(0), SymbolUtil.TIME_CHAR.charAt(0)))
        .orElseThrow());
  }
}
