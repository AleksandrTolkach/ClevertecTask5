package by.toukach.objectconverter.testdata;

import by.toukach.objectconverter.dto.subject.Employee;
import by.toukach.objectconverter.dto.subject.Specialization;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@Builder(setterPrefix = "with")
@Accessors(fluent = true, chain = true)
@FieldNameConstants
public class EmployeeTestData {

  private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

  @Builder.Default
  private long id = 1L;

  @Builder.Default
  private LocalDateTime createdAt = LocalDateTime.of(2023, 11, 9, 1, 36, 2);

  @Builder.Default
  private String name = "Oleg";

  @Builder.Default
  private LocalDate dateOfBirth = LocalDate.of(1985, 2, 3);

  @Builder.Default
  private Specialization specialization = Specialization.DEVELOPER;

  @Builder.Default
  private Double respectLevel = 12.3D;

  @Builder.Default
  private BigDecimal salary = BigDecimal.TEN;

  @Builder.Default
  private String[] achievementList = new String[] {"Smart", "Tall", "Crazy", "Funny"};

  @Builder.Default
  private boolean active = true;

  public Employee buildEmployee() {
    return Employee.builder()
        .id(id)
        .createdAt(createdAt)
        .name(name)
        .dateOfBirth(dateOfBirth)
        .specialization(specialization)
        .respectLevel(respectLevel)
        .salary(salary)
        .achievementList(achievementList)
        .active(active)
        .build();
  }

  public String employeeAsJson() throws JsonProcessingException {
    ObjectMapper objectMapper = ObjectMapperTestData.getMapper();
    return objectMapper.writeValueAsString(buildEmployee());
  }
}
