package by.toukach.objectconverter.exception;

/**
 * Класс, представляющий исключение,
 * которое выбрасывается при возникновении ошибок во время десериализации.
 */
public class DeserializeException extends RuntimeException {

  public DeserializeException(String message) {
    super(message);
  }
}
