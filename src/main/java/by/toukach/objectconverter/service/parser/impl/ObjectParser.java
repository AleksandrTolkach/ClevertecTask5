package by.toukach.objectconverter.service.parser.impl;

import by.toukach.objectconverter.dto.ParsedElement;
import by.toukach.objectconverter.exception.ExceptionMessage;
import by.toukach.objectconverter.exception.ParseException;
import by.toukach.objectconverter.service.factory.ParserFactory;
import by.toukach.objectconverter.service.factory.impl.ParserFactoryImpl;
import by.toukach.objectconverter.service.parser.Parser;
import by.toukach.objectconverter.util.SymbolUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Класс для парсинга JSON в кастомный объект.
 */
public class ObjectParser implements Parser {

  @Override
  public ParsedElement parse(String json) {
    StringBuilder parsedJsonBuilder = new StringBuilder();
    Map<String, Object> objectStructureMap = new LinkedHashMap<>();

    if (json.startsWith(SymbolUtil.COLON)) {
      parsedJsonBuilder.append(json.substring(0, 2));
      json = json.substring(2);
    } else {
      parsedJsonBuilder.append(json.charAt(0));
      json = json.substring(1);
    }

    ParsedElement parsedElement = parseElements(json, objectStructureMap);

    String parsedJson = parsedElement.getParsedJson();
    parsedJsonBuilder.append(parsedJson);
    parsedElement.setParsedJson(parsedJsonBuilder.toString());

    return parsedElement;
  }

  private ParsedElement parseElements(String json, Map<String, Object> objectStructure) {
    StringBuilder parsedJsonBuilder = new StringBuilder();

    ParserFactory parserFactory = new ParserFactoryImpl();
    Parser parser = parserFactory.getParser(json);
    ParsedElement parsedElement = parser.parse(json);

    String parsedJson = parsedElement.getParsedJson();
    parsedJsonBuilder.append(parsedElement.getParsedJson());
    json = json.substring(parsedJson.length());

    String key = parsedElement.getParsedObject().entrySet().stream()
        .map(Entry::getKey)
        .findFirst()
        .orElseThrow(() -> new ParseException(ExceptionMessage.STRING_NOT_PARSED));

    Object value = parsedElement.getParsedObject().entrySet().stream()
        .map(Entry::getValue)
        .findFirst()
        .orElseThrow(() -> new ParseException(ExceptionMessage.STRING_NOT_PARSED));

    objectStructure.put(key, value);

    if (json.startsWith(SymbolUtil.COMMA)) {
      parsedJsonBuilder.append(json.charAt(0));

      json = json.replaceFirst(SymbolUtil.COMMA, SymbolUtil.EMPTY);
      parsedElement = parseElements(json, objectStructure);

      parsedJsonBuilder.append(parsedElement.getParsedJson());
    }

    if (json.startsWith(SymbolUtil.RIGHT_BRACKET)) {
      parsedJsonBuilder.append(json.charAt(0));
    }

    return new ParsedElement(parsedJsonBuilder.toString(), objectStructure);
  }
}
