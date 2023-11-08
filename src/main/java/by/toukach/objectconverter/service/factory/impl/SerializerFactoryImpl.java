package by.toukach.objectconverter.service.factory.impl;

import by.toukach.objectconverter.service.factory.SerializerFactory;
import by.toukach.objectconverter.service.serializer.Serializer;
import by.toukach.objectconverter.service.serializer.impl.ArraySerializer;
import by.toukach.objectconverter.service.serializer.impl.BigDecimalSerializer;
import by.toukach.objectconverter.service.serializer.impl.CollectionSerializer;
import by.toukach.objectconverter.service.serializer.impl.EnumSerializer;
import by.toukach.objectconverter.service.serializer.impl.LocalDateSerializer;
import by.toukach.objectconverter.service.serializer.impl.LocalDateTimeSerializer;
import by.toukach.objectconverter.service.serializer.impl.MapSerializer;
import by.toukach.objectconverter.service.serializer.impl.ObjectSerializer;
import by.toukach.objectconverter.service.serializer.impl.OffsetDateTimeSerializer;
import by.toukach.objectconverter.service.serializer.impl.PrimitiveSerializer;
import by.toukach.objectconverter.service.serializer.impl.SimpleSerializer;
import by.toukach.objectconverter.service.serializer.impl.UuidSerializer;
import by.toukach.objectconverter.service.serializer.impl.WrapperSerializer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Класс, представляющий фабрику для создания сериализаторов.
 */
public class SerializerFactoryImpl implements SerializerFactory {

  private final Map<Predicate<Object>, Serializer> serializerMap = new LinkedHashMap<>();

  /**
   * Конструктор для создания фабрики. При создании фабрика наполняется сериализаторами.
   */
  public SerializerFactoryImpl() {
    serializerMap.put(Objects::isNull, new SimpleSerializer());
    serializerMap.put(o -> o.getClass().isPrimitive(), new PrimitiveSerializer());
    serializerMap.put(this::getWrapperAndStringPredicate, new WrapperSerializer());
    serializerMap.put(Collection.class::isInstance, new CollectionSerializer());
    serializerMap.put(o -> o.getClass().isArray(), new ArraySerializer());
    serializerMap.put(Map.class::isInstance, new MapSerializer());
    serializerMap.put(LocalDate.class::isInstance, new LocalDateSerializer());
    serializerMap.put(LocalDateTime.class::isInstance, new LocalDateTimeSerializer());
    serializerMap.put(BigDecimal.class::isInstance, new BigDecimalSerializer());
    serializerMap.put(k -> k.getClass().isEnum(), new EnumSerializer());
    serializerMap.put(UUID.class::isInstance, new UuidSerializer());
    serializerMap.put(OffsetDateTime.class::isInstance, new OffsetDateTimeSerializer());
  }

  @Override
  public Serializer getSerializer(Object object) {
    return serializerMap.entrySet().stream()
        .filter(e -> e.getKey().test(object))
        .map(Entry::getValue)
        .findFirst()
        .orElse(new ObjectSerializer());
  }

  private boolean getWrapperAndStringPredicate(Object object) {
    return object instanceof Boolean
        || object instanceof Byte
        || object instanceof Short
        || object instanceof Integer
        || object instanceof Long
        || object instanceof Float
        || object instanceof Double
        || object instanceof Character
        || object instanceof String;
  }
}
