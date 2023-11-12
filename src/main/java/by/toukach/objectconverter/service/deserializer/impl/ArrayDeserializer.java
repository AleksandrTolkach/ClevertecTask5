package by.toukach.objectconverter.service.deserializer.impl;

import by.toukach.objectconverter.service.deserializer.Deserializer;
import by.toukach.objectconverter.util.SymbolUtil;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * Класс для десериализации массива.
 */
public class ArrayDeserializer implements Deserializer {

  @Override
  public Object deserialize(Object object, Class<?> type) {
    Map<String, String> arrayMap = (Map<String, String>) object;

    Class<?> componentType = type.getComponentType();

    boolean isNumber = isNumber(componentType);
    boolean isBoolean = boolean.class.equals(componentType) || Boolean.class.equals(componentType);

    List<String> collect = arrayMap.entrySet().stream()
        .map(Entry::getValue)
        .map(s -> s.startsWith(SymbolUtil.LEFT_SQR_BRACKET) ? s.substring(1) : s)
        .map(s -> isNumber ? s.substring(1) : (isBoolean ? s.substring(1)
            : s.substring(1, s.length() - 1)))
        .collect(Collectors.toCollection(LinkedList::new));

    if (type.isArray()) {
      return collect.toArray();
    } else {
      return collect;
    }
  }

  private boolean isNumber(Class<?> type) {
    return type.equals(int.class)
        || type.equals(double.class)
        || type.equals(float.class)
        || Number.class.isAssignableFrom(type);
  }
}
