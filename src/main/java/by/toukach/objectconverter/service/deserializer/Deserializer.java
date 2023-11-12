package by.toukach.objectconverter.service.deserializer;

/**
 * Интерфейс для десериализации объекта в указанный тип.
 */
public interface Deserializer {

  /**
   * Метод для десериализации объекта в указанный тип.
   *
   * @param object объект для десериализации.
   * @param type целевой тип.
   * @return запрашиваемый объект.
   */
  Object deserialize(Object object, Class<?> type);
}
