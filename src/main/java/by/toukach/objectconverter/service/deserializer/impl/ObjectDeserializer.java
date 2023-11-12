package by.toukach.objectconverter.service.deserializer.impl;

import by.toukach.objectconverter.exception.ConstructorAccessException;
import by.toukach.objectconverter.exception.ExceptionMessage;
import by.toukach.objectconverter.service.deserializer.Deserializer;
import by.toukach.objectconverter.service.factory.DeserializerFactory;
import by.toukach.objectconverter.service.factory.impl.DeserializerFactoryImpl;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * Класс для десериализации кастомных классов.
 */
public class ObjectDeserializer implements Deserializer {

  private static final String LIST_CLASS = "java.util.LinkedList";
  private static final String ADD_METHOD = "add";

  @Override
  public Object deserialize(Object json, Class<?> type) {
    Map<String, Object> objectMap = (Map<String, Object>) json;

    Object targetObject;

    try {
      Constructor<?> declaredConstructor = type.getDeclaredConstructor();
      declaredConstructor.setAccessible(true);
      targetObject = declaredConstructor.newInstance();

      DeserializerFactory deserializerFactory = new DeserializerFactoryImpl();

      for (Field declaredField : targetObject.getClass().getDeclaredFields()) {
        declaredField.setAccessible(true);

        String fieldName = declaredField.getName();
        Class<?> fieldType = declaredField.getType();
        Object fieldValue = objectMap.get(fieldName);

        boolean isArray = fieldType.isArray();

        if (isArray || Collection.class.isAssignableFrom(fieldType)) {

          Class<?> componentType;

          if (isArray) {
            componentType = fieldType.getComponentType();
          } else {
            ParameterizedType genericType = (ParameterizedType) declaredField.getGenericType();
            Type actualTypeArgument = genericType.getActualTypeArguments()[0];

            componentType = (Class<?>) actualTypeArgument;
          }

          Map<String, Object> fieldValueMap = (Map<String, Object>) fieldValue;

          List<Object> valueList = fieldValueMap.entrySet().stream()
              .map(Entry::getValue)
              .collect(Collectors.toCollection(LinkedList::new));

          Object targetArray;

          if (isArray) {
            targetArray = Array.newInstance(componentType, valueList.size());
          } else {
            Class<?> listClass = Class.forName(LIST_CLASS);
            Constructor<?> listConstructor = listClass.getDeclaredConstructor();
            listConstructor.setAccessible(true);

            targetArray = listConstructor.newInstance();
          }

          for (int i = 0; i < valueList.size(); i++) {

            Object value = valueList.get(i);
            Deserializer deserializer = deserializerFactory.getDeserializer(componentType);

            Map<String, Object> fieldValueAsMap = new LinkedHashMap<>();

            if (deserializer instanceof ObjectDeserializer) {

              Object deserializedObject = deserializer.deserialize(value, componentType);

              if (isArray) {
                Array.set(targetArray, i, deserializedObject);
              } else {
                Method addMethod = targetArray.getClass()
                    .getDeclaredMethod(ADD_METHOD, Object.class);

                addMethod.invoke(targetArray, deserializedObject);
              }

            } else {
              fieldValueAsMap.put(null, value);

              Object deserializedObject = deserializer.deserialize(fieldValueAsMap, componentType);

              if (isArray) {
                Array.set(targetArray, i, deserializedObject);
              } else {
                Method addMethod = targetArray.getClass()
                    .getDeclaredMethod(ADD_METHOD, Object.class);

                addMethod.invoke(targetArray, deserializedObject);
              }
            }
          }

          declaredField.set(targetObject, targetArray);

        } else {
          Deserializer deserializer = deserializerFactory.getDeserializer(fieldType);
          Object deserializedFieldValue = deserializer.deserialize(fieldValue, fieldType);

          declaredField.set(targetObject, deserializedFieldValue);
        }
      }

    } catch (NoSuchMethodException e) {
      throw new ConstructorAccessException(String.format(ExceptionMessage.CONSTRUCTOR_NOT_DEFINED,
          type.getName()), e);
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
        | InvocationTargetException e) {
      throw new ConstructorAccessException(String.format(ExceptionMessage.INSTANCE_CREATE,
          type.getName()), e);
    }

    return targetObject;
  }
}
