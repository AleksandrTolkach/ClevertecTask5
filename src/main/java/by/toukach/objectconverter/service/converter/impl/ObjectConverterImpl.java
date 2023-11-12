package by.toukach.objectconverter.service.converter.impl;

import by.toukach.objectconverter.dto.ParsedElement;
import by.toukach.objectconverter.service.converter.ObjectConverter;
import by.toukach.objectconverter.service.deserializer.Deserializer;
import by.toukach.objectconverter.service.factory.DeserializerFactory;
import by.toukach.objectconverter.service.factory.ParserFactory;
import by.toukach.objectconverter.service.factory.SerializerFactory;
import by.toukach.objectconverter.service.factory.impl.DeserializerFactoryImpl;
import by.toukach.objectconverter.service.factory.impl.ParserFactoryImpl;
import by.toukach.objectconverter.service.factory.impl.SerializerFactoryImpl;
import by.toukach.objectconverter.service.parser.Parser;
import by.toukach.objectconverter.service.serializer.Serializer;
import by.toukach.objectconverter.util.SymbolUtil;

/**
 * Класс для конвертации объекта в JSON и наоборот.
 */
public class ObjectConverterImpl implements ObjectConverter {

  private static final ObjectConverter instance = new ObjectConverterImpl();

  private final SerializerFactory serializerFactory;
  private final DeserializerFactory deserializerFactory;
  private final ParserFactory parserFactory;

  private ObjectConverterImpl() {
    serializerFactory = new SerializerFactoryImpl();
    deserializerFactory = new DeserializerFactoryImpl();
    parserFactory = new ParserFactoryImpl();
  }

  @Override
  public Object convertToObject(String json, Class<?> type) {
    json = json.replaceAll(SymbolUtil.SPACES, SymbolUtil.EMPTY_SPACE);

    Parser parser = parserFactory.getParser(json);
    ParsedElement parsedElement = parser.parse(json);

    Deserializer deserializer = deserializerFactory.getDeserializer(type);
    return deserializer.deserialize(parsedElement.getParsedObject(), type);
  }

  @Override
  public String convertToJson(Object object) {
    Serializer serializer = serializerFactory.getSerializer(object);
    return serializer.serialize(object);
  }

  public static ObjectConverter getInstance() {
    return instance;
  }
}
