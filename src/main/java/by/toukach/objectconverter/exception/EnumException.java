package by.toukach.objectconverter.exception;


/**
 * Класс, представляющий исключение, которое выбрасывается при некорректной работе с Enum.
 */
public class EnumException extends RuntimeException {

  public EnumException(String message) {
    super(message);
  }
}
