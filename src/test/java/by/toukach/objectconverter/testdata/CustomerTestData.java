package by.toukach.objectconverter.testdata;

import by.toukach.objectconverter.dto.subject.Customer;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@Builder(setterPrefix = "with")
@Accessors(fluent = true, chain = true)
@FieldNameConstants
public class CustomerTestData {

  @Builder.Default
  private UUID id = UUID.fromString("2b8e8277-ef5d-4ce3-b084-402e883f4a0d");

  @Builder.Default
  private String name = "Maz";

  public Customer buildCustomer() {
    return Customer.builder()
        .id(id)
        .name(name)
        .build();
  }
}
