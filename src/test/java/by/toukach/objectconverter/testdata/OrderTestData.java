package by.toukach.objectconverter.testdata;

import by.toukach.objectconverter.dto.subject.Customer;
import by.toukach.objectconverter.dto.subject.Order;
import by.toukach.objectconverter.dto.subject.Project;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@Builder(setterPrefix = "with")
@Accessors(fluent = true, chain = true)
@FieldNameConstants
public class OrderTestData {

  @Builder.Default
  private Long id = 23L;

  @Builder.Default
  private String name = "Soft";

  @Builder.Default
  private OffsetDateTime placedAt = OffsetDateTime.now();

  @Builder.Default
  private Map<Customer, List<Project>> projectMap = new HashMap<>();

  public Order buildOrder() {
    projectMap.put(CustomerTestData.builder().build().buildCustomer(),
        List.of(ProjectTestData.builder().build().buildProject()));

    return Order.builder()
        .id(id)
        .name(name)
        .placedAt(placedAt)
        .projectMap(projectMap)
        .build();
  }
}
