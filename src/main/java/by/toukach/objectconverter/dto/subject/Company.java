package by.toukach.objectconverter.dto.subject;

import by.toukach.objectconverter.config.objectmapper.LocalDateTimeCustomDeserializer;
import by.toukach.objectconverter.config.objectmapper.LocalDateTimeCustomSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

/**
 * Класс, представляющий DTO для компании.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Company {

  private UUID id;
  @JsonSerialize(using = LocalDateTimeCustomSerializer.class)
  @JsonDeserialize(using = LocalDateTimeCustomDeserializer.class)
  private LocalDateTime createdAt;
  private String name;
  private List<Department> departmentList;
}
