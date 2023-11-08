package by.toukach.objectconverter.service.factory;

import by.toukach.objectconverter.service.serializer.Serializer;

/**
 * Интерфейс, представляющий фабрику для создания сериализаторов.
 */
public interface SerializerFactory {

  /**
   * Метод для получения сериализаторов.
   *
   * @param object объект сериализации.
   * @return запрашиваемый сериализатор.
   */
  Serializer getSerializer(Object object);
}
