package az.caspiantech.kitabxanademo.book.controller;

import az.caspiantech.kitabxanademo.book.dto.request.BookCreateRequest;
import az.caspiantech.kitabxanademo.book.dto.request.BookUpdateRequest;
import az.caspiantech.kitabxanademo.book.dto.response.BookPageResponse;
import az.caspiantech.kitabxanademo.book.dto.response.BookResponse;
import az.caspiantech.kitabxanademo.book.dto.response.BookWithPagesResponse;
import az.caspiantech.kitabxanademo.book.service.BookPageService;
import az.caspiantech.kitabxanademo.book.service.BookService;
import az.caspiantech.kitabxanademo.common.pagination.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookPageService bookPageService;

    @PostMapping
    public ResponseEntity<BookResponse> create(@Valid @RequestBody BookCreateRequest request) {
        BookResponse response = bookService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<PageResponse<BookResponse>> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        return ResponseEntity.ok(bookService.getAllPaginated(page, size, sortField, ascending));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable Long id, @Valid @RequestBody BookUpdateRequest request) {
        return ResponseEntity.ok(bookService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/price/max")
    public ResponseEntity<BookResponse> getMaxPriceBook() {
        return ResponseEntity.ok(bookService.getMaxPriceBook());
    }

    @GetMapping("/price/min")
    public ResponseEntity<BookResponse> getMinPriceBook() {
        return ResponseEntity.ok(bookService.getMinPriceBook());
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<BookResponse>> getByPriceRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max
    ) {
        return ResponseEntity.ok(bookService.getByPriceRange(min, max));
    }

    @GetMapping("/{id}/full")
    public ResponseEntity<BookWithPagesResponse> getByIdWithPages(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getByIdWithPages(id));
    }

    @GetMapping("/{bookId}/pages/{pageNumber}")
    public ResponseEntity<BookPageResponse> getBookPage(
            @PathVariable Long bookId,
            @PathVariable int pageNumber) {
        return ResponseEntity.ok(bookPageService.getByBookIdAndPageNumber(bookId, pageNumber));
    }



}
