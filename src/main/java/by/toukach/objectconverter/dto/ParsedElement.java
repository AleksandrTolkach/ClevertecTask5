package by.toukach.objectconverter.dto;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс, представляющий DTO для распаршенного объекта.
 */
@Data
@AllArgsConstructor
public class ParsedElement {

  private String parsedJson;
  private Map<String, Object> parsedObject;
}
