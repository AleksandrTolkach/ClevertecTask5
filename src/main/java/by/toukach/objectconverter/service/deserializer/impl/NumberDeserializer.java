package by.toukach.objectconverter.service.deserializer.impl;

import by.toukach.objectconverter.exception.DeserializeException;
import by.toukach.objectconverter.exception.ExceptionMessage;
import by.toukach.objectconverter.exception.MethodException;
import by.toukach.objectconverter.service.deserializer.Deserializer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Класс для десериализации Number.
 */
public class NumberDeserializer implements Deserializer {

  private static final String VALUE_OF = "valueOf";

  @Override
  public Object deserialize(Object object, Class<?> type) {
    Map<String, String> numberMap = (Map<String, String>) object;

    String numberAsString = numberMap.entrySet().stream()
        .map(Entry::getValue)
        .findFirst()
        .orElseThrow(() -> new DeserializeException(
            String.format(ExceptionMessage.OBJECT_NOT_DESERIALIZED, type)));

    try {

      Method valueOfMethod = type.getDeclaredMethod(VALUE_OF, String.class);
      return valueOfMethod.invoke(null, numberAsString);

    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new MethodException(String.format(ExceptionMessage.INVOKE_METHOD, VALUE_OF), e);
    }
  }
}
