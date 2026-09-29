package az.caspiantech.kitabxanademo.book.repository.impl;

import az.caspiantech.kitabxanademo.book.mapper.BookMapper;
import az.caspiantech.kitabxanademo.book.model.Book;
import az.caspiantech.kitabxanademo.book.repository.BookRepository;
import az.caspiantech.kitabxanademo.generated.tables.records.BookRecord;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.jooq.DSLContext;
import org.jooq.SortField;
import org.springframework.stereotype.Repository;

import static az.caspiantech.kitabxanademo.generated.Tables.BOOK;

@Repository
public class BookRepositoryImpl  implements BookRepository {

    private final BookMapper mapper;
    private final DSLContext dsl;

    public BookRepositoryImpl(BookMapper mapper, DSLContext dsl) {
        this.mapper = mapper;
        this.dsl = dsl;
    }


    @Override
    public Book save(Book book) {
        LocalDateTime now = LocalDateTime.now();

        BookRecord record = dsl.insertInto(BOOK)
                .set(BOOK.TITLE, book.getTitle())
                .set(BOOK.AUTHOR, book.getAuthor())
                .set(BOOK.ISBN, book.getIsbn())
                .set(BOOK.PRICE, book.getPrice())
                .set(BOOK.PAGE_COUNT, book.getPageCount())
                .set(BOOK.PUBLISHED_AT, book.getPublishedAt())
                .set(BOOK.CREATED_AT, now)
                .set(BOOK.UPDATED_AT, now)
                .returning()
                .fetchOne();
        return mapper.toDomain(record);
    }

    @Override
    public Optional<Book> findById(Long id) {
        BookRecord record = dsl.selectFrom(BOOK)
                .where(BOOK.ID.eq(id))
                .fetchOne();
        return Optional.ofNullable(mapper.toDomain(record));
    }

    @Override
    public List<Book> findAll() {
        return dsl.selectFrom(BOOK)
                .fetch()
                .map(mapper::toDomain);
    }

    @Override
    public List<Book> findAll(int page, int size, String sortField, boolean ascending) {
        SortField<?> sort = resolveSortField(sortField, ascending);

        return dsl.selectFrom(BOOK)
                .orderBy(sort)
                .limit(size)
                .offset(page * size)
                .fetch()
                .map(mapper::toDomain);
    }

    @Override
    public long count() {
        return dsl.fetchCount(BOOK);
    }

    @Override
    public Book update(Long id, Book book) {
        dsl.update(BOOK)
                .set(BOOK.TITLE, book.getTitle())
                .set(BOOK.AUTHOR, book.getAuthor())
                .set(BOOK.ISBN, book.getIsbn())
                .set(BOOK.PRICE, book.getPrice())
                .set(BOOK.PAGE_COUNT, book.getPageCount())
                .set(BOOK.PUBLISHED_AT, book.getPublishedAt())
                .set(BOOK.UPDATED_AT, LocalDateTime.now())
                .where(BOOK.ID.eq(id))
                .execute();

        return findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        dsl.deleteFrom(BOOK)
                .where(BOOK.ID.eq(id))
                .execute();
    }

    @Override
    public boolean existsById(Long id) {
        return dsl.fetchExists(
                dsl.selectFrom(BOOK).where(BOOK.ID.eq(id))
        );
    }

    @Override
    public Optional<Book> findMaxPriceBook() {
        BookRecord record = dsl.selectFrom(BOOK)
                .orderBy(BOOK.PRICE.desc())
                .limit(1)
                .fetchOne();

        return Optional.ofNullable(mapper.toDomain(record));
    }

    @Override
    public Optional<Book> findMinPriceBook() {
        BookRecord record = dsl.selectFrom(BOOK)
                .orderBy(BOOK.PRICE.asc())
                .limit(1)
                .fetchOne();

        return Optional.ofNullable(mapper.toDomain(record));
    }

    @Override
    public List<Book> findByPriceRange(BigDecimal min, BigDecimal max) {
        return dsl.selectFrom(BOOK)
                .where(BOOK.PRICE.between(min, max))
                .orderBy(BOOK.PRICE.asc())
                .fetch()
                .map(mapper::toDomain);
    }

    private SortField<?> resolveSortField(String field, boolean ascending) {
        var column = switch (field) {
            case "price" -> BOOK.PRICE;
            case "title" -> BOOK.TITLE;
            case "author" -> BOOK.AUTHOR;
            case "createdAt" -> BOOK.CREATED_AT;
            default -> BOOK.ID;
        };

        return ascending ? column.asc() : column.desc();
    }
}
