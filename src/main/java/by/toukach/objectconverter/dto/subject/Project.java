package by.toukach.objectconverter.dto.subject;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий DTO для проекта.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

  private UUID id;
  private String name;
  private BigDecimal price;
}
