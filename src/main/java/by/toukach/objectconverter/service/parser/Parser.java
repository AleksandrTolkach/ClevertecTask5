package by.toukach.objectconverter.service.parser;

import by.toukach.objectconverter.dto.ParsedElement;

/**
 * Интерфейс для парсинга JSON.
 */
public interface Parser {

  /**
   * Метод для парсинга JSON.
   *
   * @param json JSON для парсинга.
   * @return объект, содержащий распаршенный JSON.
   */
  ParsedElement parse(String json);
}
