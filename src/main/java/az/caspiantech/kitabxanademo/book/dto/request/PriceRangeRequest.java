package az.caspiantech.kitabxanademo.book.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class PriceRangeRequest {

    @NotNull(message = "Min price is required")
    private BigDecimal min;

    @NotNull(message = "Max price is required")
    private BigDecimal max;
}
