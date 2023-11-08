package by.toukach.objectconverter.service.converter;

/**
 * Интерфейс для конвертации объектов в JSON и наоборот.
 */
public interface ObjectConverter {

  /**
   * Метод для конвертации JSON в объект.
   *
   * @param json JSON для конвертации.
   * @param type целевой тип объекта.
   * @return запрашиваемый объект.
   */
  Object convertToObject(String json, Class<?> type);

  /**
   * Метод для конвертации объекта в JSON.
   *
   * @param object объект для конвертации.
   * @return запрашиваемый JSON.
   */
  String convertToJson(Object object);
}
