package by.toukach.objectconverter.exception;

/**
 * Класс, представляющий исключение, которое выбрасывается при некорректной работе с полем.
 */
public class FieldAccessException extends RuntimeException {

  public FieldAccessException(String message, Throwable cause) {
    super(message, cause);
  }
}
