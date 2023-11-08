package by.toukach.objectconverter.service.serializer.impl;

import by.toukach.objectconverter.util.SymbolUtil;

/**
 * Класс для сериализации LocalDateTime в JSON.
 */
public class LocalDateTimeSerializer extends SimpleSerializer {

  @Override
  public String serialize(Object object) {
    return super.serialize(object).replace(SymbolUtil.TIME_CHAR, SymbolUtil.EMPTY_SPACE);
  }
}
