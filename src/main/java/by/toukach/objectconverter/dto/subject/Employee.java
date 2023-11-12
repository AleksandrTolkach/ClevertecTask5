package by.toukach.objectconverter.dto.subject;

import by.toukach.objectconverter.config.objectmapper.LocalDateCustomDeserializer;
import by.toukach.objectconverter.config.objectmapper.LocalDateCustomSerializer;
import by.toukach.objectconverter.config.objectmapper.LocalDateTimeCustomDeserializer;
import by.toukach.objectconverter.config.objectmapper.LocalDateTimeCustomSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

/**
 * Класс, представляющий DTO для сотрудника.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Employee {

  private long id;
  @JsonSerialize(using = LocalDateTimeCustomSerializer.class)
  @JsonDeserialize(using = LocalDateTimeCustomDeserializer.class)
  private LocalDateTime createdAt;
  private String name;
  @JsonSerialize(using = LocalDateCustomSerializer.class)
  @JsonDeserialize(using = LocalDateCustomDeserializer.class)
  private LocalDate dateOfBirth;
  private Specialization specialization;
  private Double respectLevel;
  private BigDecimal salary;
  private String[] achievementList;
  private boolean active;
}
