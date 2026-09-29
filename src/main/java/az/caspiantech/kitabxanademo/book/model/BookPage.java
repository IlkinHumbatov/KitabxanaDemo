package az.caspiantech.kitabxanademo.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookPage {
    private Long id;
    private Long bookId;
    private Integer pageNumber;
    private String content;
}
