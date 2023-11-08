package by.toukach.objectconverter.testdata;

import by.toukach.objectconverter.dto.subject.Company;
import by.toukach.objectconverter.dto.subject.Department;
import by.toukach.objectconverter.dto.subject.Specialization;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@Builder(setterPrefix = "with")
@Accessors(fluent = true, chain = true)
@FieldNameConstants
public class CompanyTestData {

  @Builder.Default
  private UUID id = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");

  @Builder.Default
  private LocalDateTime createdAt = LocalDateTime.of(2003, 12, 22, 10, 50, 22);

  @Builder.Default
  private String name = "Clevertec";

  @Builder.Default
  private List<Department> departmentList = List.of(
      DepartmentTestData.builder().build().buildDepartment(),
      DepartmentTestData.builder()
          .withId(2)
          .withCreatedAt(LocalDateTime.now().withNano(0))
          .withName("Business")
          .withEmployeeList(List.of(
              EmployeeTestData.builder()
                  .withId(100L)
                  .withCreatedAt(LocalDateTime.of(2015, 11, 9, 20, 13, 22))
                  .withName("Vova")
                  .withDateOfBirth(LocalDate.of(2000, 3, 17))
                  .withSpecialization(Specialization.BUSINESS_ANALYST)
                  .withRespectLevel(2.0D)
                  .withAchievementList(new String[] {"None"})
                  .withSalary(BigDecimal.TEN)
                  .build()
                  .buildEmployee()
          ))
          .build()
          .buildDepartment()
  );

  public Company buildCompany() {
    return Company.builder()
        .id(id)
        .createdAt(createdAt)
        .name(name)
        .departmentList(departmentList)
        .build();
  }

  public String companyAsJson() throws JsonProcessingException {
    ObjectMapper objectMapper = ObjectMapperTestData.getMapper();
    return objectMapper.writeValueAsString(buildCompany());
  }
}
