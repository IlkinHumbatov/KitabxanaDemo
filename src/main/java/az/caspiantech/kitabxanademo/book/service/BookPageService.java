package az.caspiantech.kitabxanademo.book.service;

import az.caspiantech.kitabxanademo.book.dto.response.BookPageResponse;

import java.util.List;

public interface BookPageService {
    List<BookPageResponse> getAllByBookId(Long bookId);

    BookPageResponse getByBookIdAndPageNumber(Long bookId, int pageNumber);
}
