package by.toukach.objectconverter.service.deserializer.impl;

import by.toukach.objectconverter.exception.DeserializeException;
import by.toukach.objectconverter.exception.ExceptionMessage;
import by.toukach.objectconverter.service.deserializer.Deserializer;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Класс для десериализации BigDecimal.
 */
public class BigDecimalDeserializer implements Deserializer {

  @Override
  public Object deserialize(Object object, Class<?> type) {
    Map<String, String> bigDecimalMap = (Map<String, String>) object;

    String bigDecimalAsString = bigDecimalMap.entrySet().stream()
        .map(Entry::getValue)
        .findFirst()
        .orElseThrow(() -> new DeserializeException(
            String.format(ExceptionMessage.OBJECT_NOT_DESERIALIZED, type)));

    return new BigDecimal(bigDecimalAsString);
  }
}
