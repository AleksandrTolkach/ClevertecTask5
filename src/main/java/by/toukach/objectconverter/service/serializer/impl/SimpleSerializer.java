package by.toukach.objectconverter.service.serializer.impl;

import by.toukach.objectconverter.service.serializer.Serializer;
import by.toukach.objectconverter.util.SymbolUtil;

/**
 * Класс для сериализации объектов с корректно переопределенным методом toString() в JSON.
 */
public class SimpleSerializer implements Serializer {

  @Override
  public String serialize(Object object) {
    return object == null || object instanceof Number || object instanceof Boolean
        ? String.valueOf(object)
        : String.format(SymbolUtil.DOUBLE_QUOTES, object);
  }
}
