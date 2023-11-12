package by.toukach.objectconverter.service.deserializer.impl;

import by.toukach.objectconverter.exception.DeserializeException;
import by.toukach.objectconverter.exception.ExceptionMessage;
import by.toukach.objectconverter.service.deserializer.Deserializer;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

/**
 * Класс для десериализации UUID.
 */
public class UuidDeserializer implements Deserializer {

  @Override
  public Object deserialize(Object object, Class<?> type) {
    Map<String, String> map = (Map<String, String>) object;

    return UUID.fromString(map.entrySet().stream()
        .map(Entry::getValue)
        .findFirst()
        .orElseThrow(() -> new DeserializeException(
            String.format(ExceptionMessage.OBJECT_NOT_DESERIALIZED, type))));
  }
}
