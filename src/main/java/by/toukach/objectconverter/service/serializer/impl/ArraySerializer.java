package by.toukach.objectconverter.service.serializer.impl;

import by.toukach.objectconverter.service.factory.SerializerFactory;
import by.toukach.objectconverter.service.factory.impl.SerializerFactoryImpl;
import by.toukach.objectconverter.service.serializer.Serializer;
import by.toukach.objectconverter.util.SymbolUtil;

/**
 * Класс для сериализации массива в JSON.
 */
public class ArraySerializer implements Serializer {

  @Override
  public String serialize(Object object) {
    SerializerFactory serializerFactory = new SerializerFactoryImpl();
    Object[] objectArray = (Object[]) object;

    StringBuilder builder = new StringBuilder();
    builder.append(SymbolUtil.LEFT_SQR_BRACKET);

    for (Object objectInArray : objectArray) {
      Serializer serializer = serializerFactory.getSerializer(objectInArray);
      String serializedObjectInArray = serializer.serialize(objectInArray);

      builder.append(serializedObjectInArray);
      builder.append(SymbolUtil.COMMA);
    }

    builder.deleteCharAt(builder.length() - 1);
    builder.append(SymbolUtil.RIGHT_SQR_BRACKET);
    return builder.toString();
  }
}
