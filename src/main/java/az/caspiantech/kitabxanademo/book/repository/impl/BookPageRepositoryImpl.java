package az.caspiantech.kitabxanademo.book.repository.impl;

import az.caspiantech.kitabxanademo.book.model.BookPage;

import java.util.List;
import java.util.Optional;

public class BookPageRepositoryImpl implements BookPageRepository {
    @Override
    public List<BookPage> findAllByBookId(Long bookId) {
        return dsl.selectFrom(BOOK_PAGES)
                .where(BOOK_PAGES.BOOK_ID.eq(bookId))
                .orderBy(BOOK_PAGES.PAGE_NUMBER.asc())
                .fetch()
                .map(this::toModel);
    }

    @Override
    public Optional<BookPage> findByBookIdAndPageNumber(Long bookId, int pageNumber) {
        return dsl.selectFrom(BOOK_PAGES)
                .where(BOOK_PAGES.BOOK_ID.eq(bookId))
                .and(BOOK_PAGES.PAGE_NUMBER.eq(pageNumber))
                .fetchOptional()
                .map(this::toModel);
    }

    private BookPage toModel(Record record) {
        return BookPage.builder()
                .id(record.get(BOOK_PAGES.ID))
                .bookId(record.get(BOOK_PAGES.BOOK_ID))
                .pageNumber(record.get(BOOK_PAGES.PAGE_NUMBER))
                .content(record.get(BOOK_PAGES.CONTENT))
                .build();
    }
}
