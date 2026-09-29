package az.caspiantech.kitabxanademo.book.mapper;

import az.caspiantech.kitabxanademo.book.dto.request.BookCreateRequest;
import az.caspiantech.kitabxanademo.book.dto.request.BookUpdateRequest;
import az.caspiantech.kitabxanademo.book.dto.response.BookResponse;
import az.caspiantech.kitabxanademo.book.model.Book;
import az.caspiantech.kitabxanademo.generated.tables.records.BookRecord;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    // Request -> Domain model (yeni kitab yaradarkən)
    public Book toDomain(BookCreateRequest request) {
        return Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .price(request.getPrice())
                .pageCount(request.getPageCount())
                .publishedAt(request.getPublishedAt())
                .build();
    }

    // Update request -> mövcud domain modelin üzərinə yazmaq üçün
    public void updateDomain(Book existing, BookUpdateRequest request) {
        existing.setTitle(request.getTitle());
        existing.setAuthor(request.getAuthor());
        existing.setIsbn(request.getIsbn());
        existing.setPrice(request.getPrice());
        existing.setPageCount(request.getPageCount());
        existing.setPublishedAt(request.getPublishedAt());
    }

    // jOOQ Record -> Domain model
    public Book toDomain(BookRecord record) {
        if (record == null) {
            return null;
        }
        return Book.builder()
                .id(record.getId())
                .title(record.getTitle())
                .author(record.getAuthor())
                .isbn(record.getIsbn())
                .price(record.getPrice())
                .pageCount(record.getPageCount())
                .publishedAt(record.getPublishedAt())
                .createdAt(record.getCreatedAt())
                .updatedAt(record.getUpdatedAt())
                .build();
    }

    // Domain model -> Response DTO
    public BookResponse toResponse(Book book) {
        if (book == null) {
            return null;
        }
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .price(book.getPrice())
                .pageCount(book.getPageCount())
                .publishedAt(book.getPublishedAt())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }

    public Book toDomain(BookUpdateRequest request) {
        return Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .price(request.getPrice())
                .publishedAt(request.getPublishedAt())
                .pageCount(request.getPageCount())
                .build();
    }
}
