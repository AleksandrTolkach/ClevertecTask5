package by.toukach.objectconverter.dto.subject;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Класс, представляющий DTO для клиентов.
 */
@Data
@Builder
@AllArgsConstructor
public class Customer {

  private UUID id;
  private String name;
}
