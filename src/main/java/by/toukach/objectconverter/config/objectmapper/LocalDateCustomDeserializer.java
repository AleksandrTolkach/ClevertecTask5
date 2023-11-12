package by.toukach.objectconverter.config.objectmapper;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Класс для десериализации ObjectMapper типа LocalDate.
 */
public class LocalDateCustomDeserializer extends StdDeserializer<LocalDate> {

  private static final String DATE_TIME_FORMAT = "yyyy-MM-dd";

  public LocalDateCustomDeserializer() {
    super(LocalDate.class);
  }

  @Override
  public LocalDate deserialize(JsonParser jsonParser,
      DeserializationContext deserializationContext) throws IOException, JacksonException {
    return LocalDate.parse(jsonParser.getValueAsString(),
        DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
  }
}
