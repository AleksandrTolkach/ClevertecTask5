package by.toukach.objectconverter.service.converter.impl;

import static org.assertj.core.api.Assertions.assertThat;

import by.toukach.objectconverter.dto.subject.Company;
import by.toukach.objectconverter.dto.subject.Department;
import by.toukach.objectconverter.dto.subject.Employee;
import by.toukach.objectconverter.dto.subject.Order;
import by.toukach.objectconverter.dto.subject.Project;
import by.toukach.objectconverter.service.converter.ObjectConverter;
import by.toukach.objectconverter.testdata.CompanyTestData;
import by.toukach.objectconverter.testdata.DepartmentTestData;
import by.toukach.objectconverter.testdata.EmployeeTestData;
import by.toukach.objectconverter.testdata.ObjectMapperTestData;
import by.toukach.objectconverter.testdata.OrderTestData;
import by.toukach.objectconverter.testdata.ProjectTestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ObjectConverterImplTest {

  private final ObjectConverter objectConverter = ObjectConverterImpl.getInstance();
  private final ObjectMapper objectMapper = ObjectMapperTestData.getMapper();

  @ParameterizedTest
  @MethodSource("objectProvider")
  public void convertToJsonTestShouldReturnJson(Object object) throws JsonProcessingException {
    // given, when
    String expectedResult = objectMapper.writeValueAsString(object);
    String actualResult = objectConverter.convertToJson(object);

    // then
    assertThat(actualResult)
        .isEqualTo(expectedResult);
  }

  @ParameterizedTest
  @MethodSource("jsonProvider")
  public void convertToObjectTestShouldReturnObject(String json, Class<?> type)
      throws JsonProcessingException {
    // given, when
    Object expectedResult = objectMapper.readValue(json, type);
    Object actualResult = objectConverter.convertToObject(json, type);

    // then
    assertThat(actualResult)
        .isEqualTo(expectedResult);
  }

  public static Stream<Arguments> objectProvider() {
    Employee employee = EmployeeTestData.builder().build().buildEmployee();
    Department department = DepartmentTestData.builder().build().buildDepartment();
    Company company = CompanyTestData.builder().build().buildCompany();
    Order order = OrderTestData.builder().build().buildOrder();
    Project project = ProjectTestData.builder().build().buildProject();

    return Stream.of(
        Arguments.arguments(employee),
        Arguments.arguments(department),
        Arguments.arguments(company),
        Arguments.arguments(order),
        Arguments.arguments(project)
    );
  }

  public static Stream<Arguments> jsonProvider() throws JsonProcessingException {
    String employeeAsJson = EmployeeTestData.builder().build().employeeAsJson();
    String projectAsJson = ProjectTestData.builder().build().projectAsJson();
    String departmentAsJson = DepartmentTestData.builder().build().departmentAsJson();
    String companyAsJson = CompanyTestData.builder().build().companyAsJson();

    return Stream.of(
        Arguments.arguments(employeeAsJson, Employee.class),
        Arguments.arguments(projectAsJson, Project.class),
        Arguments.arguments(departmentAsJson, Department.class),
        Arguments.arguments(companyAsJson, Company.class)
    );
  }
}
