package by.toukach.objectconverter.service.serializer.impl;

import by.toukach.objectconverter.exception.ExceptionMessage;
import by.toukach.objectconverter.exception.FieldAccessException;
import by.toukach.objectconverter.service.factory.SerializerFactory;
import by.toukach.objectconverter.service.factory.impl.SerializerFactoryImpl;
import by.toukach.objectconverter.service.serializer.Serializer;
import by.toukach.objectconverter.util.SymbolUtil;
import java.lang.reflect.Field;

/**
 * Класс для сериализации кастомного объекта в JSON.
 */
public class ObjectSerializer implements Serializer {

  @Override
  public String serialize(Object object) {
    SerializerFactory serializerFactory = new SerializerFactoryImpl();

    Field[] declaredFields = object.getClass().getDeclaredFields();

    StringBuilder builder = new StringBuilder();
    builder.append(SymbolUtil.LEFT_BRACKET);

    for (Field declaredField : declaredFields) {
      Object objectFromField = getObjectFromField(declaredField, object);

      Serializer serializer = serializerFactory.getSerializer(objectFromField);
      String serializedObjectFromField = serializer.serialize(objectFromField);

      builder.append(String.format(SymbolUtil.FIELD_VALUE, declaredField.getName(),
          serializedObjectFromField));
    }

    builder.deleteCharAt(builder.length() - 1);
    builder.append(SymbolUtil.RIGHT_BRACKET);

    return builder.toString();
  }

  private Object getObjectFromField(Field field, Object object) {
    field.setAccessible(true);

    try {
      return field.get(object);
    } catch (IllegalAccessException e) {
      throw new FieldAccessException(String.format(ExceptionMessage.FIELD_ACCESS_NOT_ALLOWED,
          field.getName()), e);
    }
  }
}
