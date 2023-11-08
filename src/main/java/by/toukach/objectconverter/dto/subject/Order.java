package by.toukach.objectconverter.dto.subject;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Класс, представляющий DTO для заказа.
 */
@Data
@Builder
@AllArgsConstructor
public class Order {

  private Long id;
  private String name;
  private OffsetDateTime placedAt;
  private Map<Customer, List<Project>> projectMap;
}
