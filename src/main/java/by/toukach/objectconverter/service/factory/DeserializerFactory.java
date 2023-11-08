package by.toukach.objectconverter.service.factory;

import by.toukach.objectconverter.service.deserializer.Deserializer;

/**
 * Интерфейс, представляющий фабрику, для создания десериализаторов.
 */
public interface DeserializerFactory {

  /**
   * Метод для получения десериализации.
   *
   * @param type тип объекта десериализации.
   * @return запрашиваемый десериализатор.
   */
  Deserializer getDeserializer(Class<?> type);
}
