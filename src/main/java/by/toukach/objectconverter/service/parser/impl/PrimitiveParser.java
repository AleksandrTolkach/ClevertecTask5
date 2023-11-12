package by.toukach.objectconverter.service.parser.impl;

import by.toukach.objectconverter.dto.ParsedElement;
import by.toukach.objectconverter.service.parser.Parser;
import by.toukach.objectconverter.util.SymbolUtil;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Класс для парсинга примитивных значений в JSON.
 */
public class PrimitiveParser implements Parser {

  @Override
  public ParsedElement parse(String json) {
    StringBuilder builder = new StringBuilder();
    StringBuilder parsedJsonBuilder = new StringBuilder();

    for (int i = 0; i < json.length(); i++) {
      if (!String.valueOf(json.charAt(i)).equals(SymbolUtil.COMMA)
          && !String.valueOf(json.charAt(i)).equals(SymbolUtil.RIGHT_BRACKET)
          && !String.valueOf(json.charAt(i)).equals(SymbolUtil.RIGHT_SQR_BRACKET)) {

        builder.append(json.charAt(i));
        parsedJsonBuilder.append(json.charAt(i));
      } else {
        break;
      }
    }

    json = builder.toString();

    if (json.startsWith(SymbolUtil.COLON)) {
      json = json.substring(1);
    }

    if (json.startsWith(SymbolUtil.QUOTA) && json.endsWith(SymbolUtil.QUOTA)) {
      json = json.substring(1, json.length() - 1);
    }

    Map<String, Object> value = new LinkedHashMap<>();
    value.put(null, json);
    return new ParsedElement(parsedJsonBuilder.toString(), value);
  }
}
