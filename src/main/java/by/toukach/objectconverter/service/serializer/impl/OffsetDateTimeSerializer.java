package by.toukach.objectconverter.service.serializer.impl;

import by.toukach.objectconverter.util.SymbolUtil;

/**
 * Класс для сериализации OffsetDateTime в JSON.
 */
public class OffsetDateTimeSerializer extends SimpleSerializer {

  @Override
  public String serialize(Object object) {
    return String.valueOf(String.format(SymbolUtil.DOUBLE_QUOTES, object));
  }
}
