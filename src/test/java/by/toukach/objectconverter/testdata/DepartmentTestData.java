package by.toukach.objectconverter.testdata;

import by.toukach.objectconverter.dto.subject.Department;
import by.toukach.objectconverter.dto.subject.Employee;
import by.toukach.objectconverter.dto.subject.Specialization;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@Builder(setterPrefix = "with")
@Accessors(fluent = true, chain = true)
@FieldNameConstants
public class DepartmentTestData {

  @Builder.Default
  private long id = 99;

  @Builder.Default
  private LocalDateTime createdAt = LocalDateTime.of(2021, 12, 2, 1, 36, 2);

  @Builder.Default
  private String name = "Dev";

  @Builder.Default
  private List<Employee> employeeList = new ArrayList<>(List.of(
      EmployeeTestData.builder().build().buildEmployee(),
      EmployeeTestData.builder()
          .withId(2L)
          .withCreatedAt(LocalDateTime.of(2002, 10, 2, 22, 2, 10))
          .withName("Lola")
          .withDateOfBirth(LocalDate.of(1980, 2, 2))
          .withRespectLevel(99.9D)
          .withAchievementList(new String[] {"Hard Project Complete", "Top"})
          .withSpecialization(Specialization.DEVELOPER)
          .withSalary(BigDecimal.valueOf(1000000L))
          .build()
          .buildEmployee(),
      EmployeeTestData.builder()
          .withId(3L)
          .withCreatedAt(LocalDateTime.of(2010, 1, 23, 12, 2, 10))
          .withName("Pasha")
          .withDateOfBirth(LocalDate.of(1999, 5, 10))
          .withRespectLevel(2.9D)
          .withAchievementList(new String[] {"Funny", "Tall"})
          .withSpecialization(Specialization.DEVELOPER)
          .withSalary(BigDecimal.valueOf(99999L))
          .build()
          .buildEmployee()
      ));


  public Department buildDepartment() {
    return Department.builder()
        .id(id)
        .createdAt(createdAt)
        .name(name)
        .employeeList(employeeList)
        .build();
  }

  public String departmentAsJson() throws JsonProcessingException {
    ObjectMapper objectMapper = ObjectMapperTestData.getMapper();
    return objectMapper.writeValueAsString(buildDepartment());
  }
}
