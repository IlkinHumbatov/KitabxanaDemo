package az.caspiantech.kitabxanademo.book.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BookCreateRequest {
    @NotBlank(message = "Title is required")
    private String title;


    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be non-negative")
    private BigDecimal price;

    @NotNull(message = "Page count is required")
    @Min(value = 1, message = "Page count must be at least 1")
    private Integer pageCount;

    @NotNull(message = "Published date is required")
    @PastOrPresent(message = "Published date cannot be in the future")
    private LocalDateTime publishedAt;
}
