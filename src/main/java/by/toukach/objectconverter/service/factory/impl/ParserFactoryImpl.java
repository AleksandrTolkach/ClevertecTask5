package by.toukach.objectconverter.service.factory.impl;

import by.toukach.objectconverter.service.factory.ParserFactory;
import by.toukach.objectconverter.service.parser.Parser;
import by.toukach.objectconverter.service.parser.impl.ArrayParser;
import by.toukach.objectconverter.service.parser.impl.KeyParser;
import by.toukach.objectconverter.service.parser.impl.ObjectParser;
import by.toukach.objectconverter.service.parser.impl.PrimitiveParser;
import by.toukach.objectconverter.util.SymbolUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

/**
 * Класс, представляющий фабрику для создания парсеров.
 */
public class ParserFactoryImpl implements ParserFactory {

  private final Map<Predicate<String>, Parser> parserMap = new LinkedHashMap<>();

  /**
   * Конструктор для создания фабрики. При создании фабрика наполняется парсерами.
   */
  public ParserFactoryImpl() {
    parserMap.put(k -> k.startsWith(SymbolUtil.LEFT_BRACKET)
        || k.startsWith(SymbolUtil.COLON + SymbolUtil.LEFT_BRACKET),
        new ObjectParser());
    parserMap.put(k -> k.startsWith(SymbolUtil.COLON + SymbolUtil.LEFT_SQR_BRACKET),
        new ArrayParser());
    parserMap.put(k -> k.startsWith(SymbolUtil.COLON),
        new PrimitiveParser());
  }

  @Override
  public Parser getParser(String json) {
    return parserMap.entrySet().stream()
        .filter(e -> e.getKey().test(json))
        .findFirst()
        .map(Entry::getValue)
        .orElse(new KeyParser());
  }
}
