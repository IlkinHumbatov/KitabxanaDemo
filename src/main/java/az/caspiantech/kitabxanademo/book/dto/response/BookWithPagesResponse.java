package az.caspiantech.kitabxanademo.book.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BookWithPagesResponse {
    Long id;
    String title;
    String author;
    String isbn;
    BigDecimal price;
    Integer pageCount;
    LocalDateTime publishedAt;
    List<BookPageResponse> pages;
}
