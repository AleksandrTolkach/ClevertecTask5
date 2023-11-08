package by.toukach.objectconverter.service.factory.impl;

import by.toukach.objectconverter.service.deserializer.Deserializer;
import by.toukach.objectconverter.service.deserializer.impl.ArrayDeserializer;
import by.toukach.objectconverter.service.deserializer.impl.BigDecimalDeserializer;
import by.toukach.objectconverter.service.deserializer.impl.BooleanDeserializer;
import by.toukach.objectconverter.service.deserializer.impl.EnumDeserializer;
import by.toukach.objectconverter.service.deserializer.impl.LocalDateDeserializer;
import by.toukach.objectconverter.service.deserializer.impl.LocalDateTimeDeserializer;
import by.toukach.objectconverter.service.deserializer.impl.NumberDeserializer;
import by.toukach.objectconverter.service.deserializer.impl.ObjectDeserializer;
import by.toukach.objectconverter.service.deserializer.impl.PrimitiveLongDeserializer;
import by.toukach.objectconverter.service.deserializer.impl.StringDeserializer;
import by.toukach.objectconverter.service.deserializer.impl.UuidDeserializer;
import by.toukach.objectconverter.service.factory.DeserializerFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Класс, представляющий фабрику, для создания десериализаторов.
 */
public class DeserializerFactoryImpl implements DeserializerFactory {

  private final Map<Predicate<Class<?>>, Deserializer> deserializerMap = new LinkedHashMap<>();

  /**
   * Конструктор для создания фабрики. При создании фабрика наполняется десериализаторами.
   */
  public DeserializerFactoryImpl() {
    deserializerMap.put(UUID.class::equals, new UuidDeserializer());
    deserializerMap.put(BigDecimal.class::equals, new BigDecimalDeserializer());
    deserializerMap.put(k -> boolean.class.equals(k) || Boolean.class.equals(k),
        new BooleanDeserializer());
    deserializerMap.put(String.class::equals, new StringDeserializer());
    deserializerMap.put(boolean.class::equals, new StringDeserializer());
    deserializerMap.put(LocalDate.class::equals, new LocalDateDeserializer());
    deserializerMap.put(LocalDateTime.class::equals, new LocalDateTimeDeserializer());
    deserializerMap.put(Enum.class::isAssignableFrom, new EnumDeserializer());
    deserializerMap.put(Class::isArray, new ArrayDeserializer());
    deserializerMap.put(Number.class::isAssignableFrom, new NumberDeserializer());
    deserializerMap.put(long.class::equals, new PrimitiveLongDeserializer());
  }

  @Override
  public Deserializer getDeserializer(Class<?> type) {
    return deserializerMap.entrySet().stream()
        .filter(e -> e.getKey().test(type))
        .findFirst()
        .map(Entry::getValue)
        .orElse(new ObjectDeserializer());
  }
}
