package by.toukach.objectconverter.service.deserializer.impl;

import by.toukach.objectconverter.exception.DeserializeException;
import by.toukach.objectconverter.exception.ExceptionMessage;
import by.toukach.objectconverter.service.deserializer.Deserializer;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Класс для десериализации String.
 */
public class StringDeserializer implements Deserializer {

  @Override
  public Object deserialize(Object object, Class<?> type) {
    Map<String, String> stringMap = (Map<String, String>) object;

    return stringMap.entrySet().stream()
        .map(Entry::getValue)
        .findFirst()
        .orElseThrow(() -> new DeserializeException(
            String.format(ExceptionMessage.OBJECT_NOT_DESERIALIZED, type.getName())));
  }
}
