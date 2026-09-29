package az.caspiantech.kitabxanademo.book.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookPageResponse {
    Long id;
    Long bookId;
    Integer pageNumber;
    String content;
}
