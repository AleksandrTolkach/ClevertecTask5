package by.toukach.objectconverter.exception;

/**
 * Класс, представляющий исключение, которое выбрасывается при неправильной работе с конструкторами.
 */
public class ConstructorAccessException extends RuntimeException {

  public ConstructorAccessException(String message, Throwable cause) {
    super(message, cause);
  }
}
