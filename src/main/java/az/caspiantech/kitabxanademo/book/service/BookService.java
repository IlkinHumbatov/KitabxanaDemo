package az.caspiantech.kitabxanademo.book.service;

import az.caspiantech.kitabxanademo.book.dto.request.BookCreateRequest;
import az.caspiantech.kitabxanademo.book.dto.request.BookUpdateRequest;
import az.caspiantech.kitabxanademo.book.dto.response.BookResponse;
import az.caspiantech.kitabxanademo.book.dto.response.BookWithPagesResponse;
import az.caspiantech.kitabxanademo.common.pagination.PageResponse;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    BookResponse create(BookCreateRequest request);

    BookResponse getById(Long id);

    List<BookResponse> getAll();

    PageResponse<BookResponse> getAllPaginated(int page, int size, String sortField, boolean ascending);

    BookResponse update(Long id, BookUpdateRequest request);

    void delete(Long id);

    BookResponse getMaxPriceBook();

    BookResponse getMinPriceBook();

    List<BookResponse> getByPriceRange(BigDecimal min, BigDecimal max);

    BookWithPagesResponse getByIdWithPages(Long id);
}
