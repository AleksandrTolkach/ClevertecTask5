package by.toukach.objectconverter.service.serializer;

/**
 * Интерфейс для сериализации объекта в JSON.
 */
public interface Serializer {

  /**
   * Метод для парсинга объекта в JSON.
   *
   * @param object объект для парсинга.
   * @return запрашиваемый JSON.
   */
  String serialize(Object object);
}
