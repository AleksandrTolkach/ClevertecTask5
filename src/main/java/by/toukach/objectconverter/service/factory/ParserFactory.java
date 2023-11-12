package by.toukach.objectconverter.service.factory;

import by.toukach.objectconverter.service.parser.Parser;

/**
 * Интерфейс, представляющий фабрику для создания парсеров.
 */
public interface ParserFactory {

  /**
   * Метод для получения парсера.
   *
   * @param string строка для парсинга.
   * @return запрашиваемый парсер.
   */
  Parser getParser(String string);
}
