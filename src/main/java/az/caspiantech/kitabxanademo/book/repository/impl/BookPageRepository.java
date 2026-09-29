package az.caspiantech.kitabxanademo.book.repository.impl;

import az.caspiantech.kitabxanademo.book.model.BookPage;

import java.util.List;
import java.util.Optional;

public interface BookPageRepository {
    List<BookPage> findAllByBookId(Long bookId);

    Optional<BookPage> findByBookIdAndPageNumber(Long bookId, int pageNumber);
}
