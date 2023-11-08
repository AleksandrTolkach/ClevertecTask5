package by.toukach.objectconverter.exception;

/**
 * Класс, представляющий исключение, которое выбрасывается при парсинге строки.
 */
public class ParseException extends RuntimeException {

  public ParseException(String message) {
    super(message);
  }
}
