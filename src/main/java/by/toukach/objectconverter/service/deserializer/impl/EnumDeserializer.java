package by.toukach.objectconverter.service.deserializer.impl;

import by.toukach.objectconverter.exception.DeserializeException;
import by.toukach.objectconverter.exception.EnumException;
import by.toukach.objectconverter.exception.ExceptionMessage;
import by.toukach.objectconverter.service.deserializer.Deserializer;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Класс для десериализации Enum.
 */
public class EnumDeserializer implements Deserializer {

  @Override
  public Object deserialize(Object object, Class<?> type) {
    Map<String, Object> enumMap = (Map<String, Object>) object;

    String enumValue = enumMap.entrySet().stream()
        .map(Entry::getValue)
        .findFirst()
        .map(String::valueOf)
        .orElseThrow(() -> new DeserializeException(
            String.format(ExceptionMessage.OBJECT_NOT_DESERIALIZED, type)));

    return Arrays.stream(type.getEnumConstants())
        .filter(s -> s.toString().equals(enumValue))
        .findFirst()
        .orElseThrow(() -> new EnumException(
            String.format(ExceptionMessage.ENUM_NOT_EXISTS, enumValue)));
  }
}
