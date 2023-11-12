package by.toukach.objectconverter.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Класс содержащий часто используемые символы для сериализации, десериализации и парсинга.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SymbolUtil {

  public static final String DOUBLE_QUOTES = "\"%s\"";
  public static final String FIELD_VALUE = "\"%s\":%s,";
  public static final String LEFT_BRACKET = "{";
  public static final String RIGHT_BRACKET = "}";
  public static final String LEFT_SQR_BRACKET = "[";
  public static final String RIGHT_SQR_BRACKET = "]";
  public static final String COMMA = ",";
  public static final String TIME_CHAR = "T";
  public static final String EMPTY_SPACE = " ";
  public static final String EMPTY = "";
  public static final String SPACES = "\\s+";
  public static final String QUOTA = "\"";
  public static final String COLON = ":";
}
