package by.toukach.objectconverter.service.parser.impl;

import by.toukach.objectconverter.dto.ParsedElement;
import by.toukach.objectconverter.service.factory.ParserFactory;
import by.toukach.objectconverter.service.factory.impl.ParserFactoryImpl;
import by.toukach.objectconverter.service.parser.Parser;
import by.toukach.objectconverter.util.SymbolUtil;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Класс для парсинга ключа в JSON.
 */
public class KeyParser implements Parser {

  @Override
  public ParsedElement parse(String json) {
    StringBuilder builder = new StringBuilder();
    StringBuilder parsedJsonBuilder = new StringBuilder();

    int i = 0;
    while (json.charAt(i) != SymbolUtil.COLON.charAt(0)) {
      builder.append(json.charAt(i));
      ++i;
    }

    parsedJsonBuilder.append(builder);

    json = json.substring(i);

    ParserFactory parserFactory = new ParserFactoryImpl();
    Parser parser = parserFactory.getParser(json);
    ParsedElement parsedElement = parser.parse(json);

    parsedJsonBuilder.append(parsedElement.getParsedJson());

    builder.deleteCharAt(0);
    builder.deleteCharAt(builder.length() - 1);

    Map<String, Object> parsedObjectStructure = new LinkedHashMap<>();
    parsedObjectStructure.put(builder.toString(), parsedElement.getParsedObject());

    return new ParsedElement(parsedJsonBuilder.toString(), parsedObjectStructure);
  }
}
