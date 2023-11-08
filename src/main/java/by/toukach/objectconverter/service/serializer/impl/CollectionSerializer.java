package by.toukach.objectconverter.service.serializer.impl;

import by.toukach.objectconverter.service.factory.SerializerFactory;
import by.toukach.objectconverter.service.factory.impl.SerializerFactoryImpl;
import by.toukach.objectconverter.service.serializer.Serializer;
import by.toukach.objectconverter.util.SymbolUtil;
import java.util.Collection;

/**
 * Класс для сериализации Collection в JSON.
 */
public class CollectionSerializer implements Serializer {

  @Override
  public String serialize(Object object) {
    SerializerFactory serializerFactory = new SerializerFactoryImpl();
    Collection<?> collection = (Collection<?>) object;

    StringBuilder builder = new StringBuilder();
    builder.append(SymbolUtil.LEFT_SQR_BRACKET);

    for (Object objectInCollection : collection) {
      Serializer serializer = serializerFactory.getSerializer(objectInCollection);
      String serializedObjectInCollection = serializer.serialize(objectInCollection);

      builder.append(serializedObjectInCollection);
      builder.append(SymbolUtil.COMMA);
    }
    builder.deleteCharAt(builder.length() - 1);
    builder.append(SymbolUtil.RIGHT_SQR_BRACKET);
    return builder.toString();
  }
}
