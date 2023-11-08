package by.toukach.objectconverter.service.serializer.impl;

import by.toukach.objectconverter.service.factory.SerializerFactory;
import by.toukach.objectconverter.service.factory.impl.SerializerFactoryImpl;
import by.toukach.objectconverter.service.serializer.Serializer;
import by.toukach.objectconverter.util.SymbolUtil;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Класс для сериализации Map в JSON.
 */
public class MapSerializer implements Serializer {

  @Override
  public String serialize(Object object) {
    SerializerFactory serializerFactory = new SerializerFactoryImpl();

    Map<Object, Object> objectMap = (Map<Object, Object>) object;
    Set<Entry<Object, Object>> entrySet = objectMap.entrySet();

    StringBuilder builder = new StringBuilder();
    builder.append(SymbolUtil.LEFT_BRACKET);

    for (Entry<Object, Object> entry : entrySet) {
      Object value = entry.getValue();

      Serializer serializer = serializerFactory.getSerializer(value);
      String serializedObject = serializer.serialize(value);

      builder.append(String.format(SymbolUtil.FIELD_VALUE, entry.getKey(), serializedObject));
    }

    builder.deleteCharAt(builder.length() - 1);
    builder.append(SymbolUtil.RIGHT_BRACKET);

    return builder.toString();
  }
}
