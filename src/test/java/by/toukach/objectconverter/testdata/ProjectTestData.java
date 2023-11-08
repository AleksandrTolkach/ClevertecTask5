package by.toukach.objectconverter.testdata;

import by.toukach.objectconverter.dto.subject.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@Builder(setterPrefix = "with")
@Accessors(fluent = true, chain = true)
@FieldNameConstants
public class ProjectTestData {

  @Builder.Default
  private UUID id = UUID.fromString("7cf82b0e-598d-4120-aa67-3c8b7ea334a8");

  @Builder.Default
  private String name = "Pool";

  @Builder.Default
  private BigDecimal price = BigDecimal.valueOf(200022L);

  public Project buildProject() {
    return Project.builder()
        .id(id)
        .name(name)
        .price(price)
        .build();
  }

  public String projectAsJson() throws JsonProcessingException {
    ObjectMapper objectMapper = ObjectMapperTestData.getMapper();
    return objectMapper.writeValueAsString(buildProject());
  }
}
