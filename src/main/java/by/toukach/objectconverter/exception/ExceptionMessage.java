package by.toukach.objectconverter.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Класс, хранящий список сообщений об ошибках.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessage {

  public static final String FIELD_ACCESS_NOT_ALLOWED = "Не удалось получить данные из поля %s";
  public static final String CONSTRUCTOR_NOT_DEFINED = "Данного конструктора нет у объекта %s";
  public static final String INSTANCE_CREATE = "Не удалось создать объект %s";
  public static final String INVOKE_METHOD = "Не удалось вызвать метод %s";
  public static final String OBJECT_NOT_DESERIALIZED = "Не удалось десериализовать объект %s";
  public static final String STRING_NOT_PARSED = "Не удалось распарсить строку";
  public static final String ENUM_NOT_EXISTS = "Нет такого значения enum %s";
}
