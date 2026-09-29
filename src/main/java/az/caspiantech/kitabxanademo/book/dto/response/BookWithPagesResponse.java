package az.caspiantech.kitabxanademo.book.dto.response;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookWithPagesResponse {
    Long id;
    String title;
    String author;
    String isbn;
    BigDecimal price;
    Integer pageCount;
    LocalDateTime publishedAt;
    private List<BookPageResponse> pages;
}
