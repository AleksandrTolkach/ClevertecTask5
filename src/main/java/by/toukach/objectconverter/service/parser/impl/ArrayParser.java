package by.toukach.objectconverter.service.parser.impl;

import by.toukach.objectconverter.dto.ParsedElement;
import by.toukach.objectconverter.service.factory.ParserFactory;
import by.toukach.objectconverter.service.factory.impl.ParserFactoryImpl;
import by.toukach.objectconverter.service.parser.Parser;
import by.toukach.objectconverter.util.SymbolUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

/**
 * Класс для парсинга массива в JSON.
 */
public class ArrayParser implements Parser {

  @Override
  public ParsedElement parse(String json) {
    StringBuilder parsedJsonBuilder = new StringBuilder();

    parsedJsonBuilder.append(json.substring(0, 2));
    String jsonSubstring = json.substring(2);

    ParsedElement parsedElement;

    if (jsonSubstring.startsWith(SymbolUtil.LEFT_BRACKET)) {

      parsedElement = objectParse(jsonSubstring);
      parsedJsonBuilder.append(parsedElement.getParsedJson());
      parsedJsonBuilder.append(SymbolUtil.RIGHT_BRACKET);

    } else {
      parsedElement = simpleParse(jsonSubstring);
      parsedJsonBuilder.append(parsedElement.getParsedJson());
    }

    parsedJsonBuilder.append(SymbolUtil.RIGHT_SQR_BRACKET);
    parsedElement.setParsedJson(parsedJsonBuilder.toString());

    return parsedElement;
  }

  private ParsedElement simpleParse(String json) {
    StringBuilder parsedJsonBuilder = new StringBuilder();

    ParserFactory parserFactory = new ParserFactoryImpl();
    Map<String, Object> parsedArray = new LinkedHashMap<>();

    do {
      if (json.startsWith(SymbolUtil.COMMA)) {
        json = json.substring(1);
      }

      Parser parser = parserFactory.getParser(json);

      if (parser instanceof KeyParser) {
        parser = parserFactory.getParser(SymbolUtil.COLON);
      }

      ParsedElement parsedElement = parser.parse(json);
      parsedJsonBuilder.append(parsedElement.getParsedJson());

      for (Entry<String, Object> parsedObjectEntry : parsedElement.getParsedObject().entrySet()) {

        String key = UUID.randomUUID().toString();

        parsedArray.put(key, parsedObjectEntry.getValue());
      }

      json = json.replaceFirst(parsedElement.getParsedJson(), SymbolUtil.EMPTY);
      parsedJsonBuilder.append(SymbolUtil.COMMA);

    } while (json.startsWith(SymbolUtil.COMMA));

    parsedJsonBuilder.deleteCharAt(parsedJsonBuilder.length() - 1);

    return new ParsedElement(parsedJsonBuilder.toString(), parsedArray);
  }

  private ParsedElement objectParse(String json) {
    StringBuilder parsedJsonBuilder = new StringBuilder();

    ParserFactory parserFactory = new ParserFactoryImpl();
    Map<String, Object> parsedArray = new LinkedHashMap<>();

    do {
      if (json.startsWith(SymbolUtil.COMMA)) {
        parsedJsonBuilder.append(json.charAt(0));
        json = json.substring(1);
      }

      Parser parser = parserFactory.getParser(json);
      ParsedElement parsedElement = parser.parse(json);
      String parsedJson = parsedElement.getParsedJson();

      parsedJsonBuilder.append(parsedJson);
      json = json.substring(parsedJson.length(), json.length());

      Map<String, Object> parsedObject = parsedElement.getParsedObject();
      String key = UUID.randomUUID().toString();

      parsedArray.put(key, parsedObject);
    } while (json.startsWith(SymbolUtil.COMMA));

    parsedJsonBuilder.deleteCharAt(parsedJsonBuilder.length() - 1);

    return new ParsedElement(parsedJsonBuilder.toString(), parsedArray);
  }
}
