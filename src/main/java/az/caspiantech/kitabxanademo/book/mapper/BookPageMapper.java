package az.caspiantech.kitabxanademo.book.mapper;

import az.caspiantech.kitabxanademo.book.dto.response.BookPageResponse;
import az.caspiantech.kitabxanademo.book.model.BookPage;
import org.springframework.stereotype.Component;

@Component
public class BookPageMapper {
    public BookPageResponse toResponse(BookPage bookPage) {
        return BookPageResponse.builder()
                .id(bookPage.getId())
                .bookId(bookPage.getBookId())
                .pageNumber(bookPage.getPageNumber())
                .content(bookPage.getContent())
                .build();
    }
}
