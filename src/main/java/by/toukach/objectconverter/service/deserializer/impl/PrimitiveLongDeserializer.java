package by.toukach.objectconverter.service.deserializer.impl;

import by.toukach.objectconverter.exception.DeserializeException;
import by.toukach.objectconverter.exception.ExceptionMessage;
import by.toukach.objectconverter.service.deserializer.Deserializer;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Класс для десериализации long.
 */
public class PrimitiveLongDeserializer implements Deserializer {

  @Override
  public Object deserialize(Object object, Class<?> type) {
    Map<String, String> longMap = (Map<String, String>) object;

    return Long.parseLong(longMap.entrySet().stream()
        .map(Entry::getValue)
        .findFirst()
        .orElseThrow(() -> new DeserializeException(
            String.format(ExceptionMessage.OBJECT_NOT_DESERIALIZED, type))));
  }
}
