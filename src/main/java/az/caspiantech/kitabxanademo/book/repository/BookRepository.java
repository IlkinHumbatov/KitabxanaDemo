package az.caspiantech.kitabxanademo.book.repository;

import az.caspiantech.kitabxanademo.book.model.Book;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> findById(Long id);

    List<Book> findAll();

    List<Book> findAll(int page, int size, String sortField, boolean ascending);

    long count();

    Book update(Long id, Book book);

    void deleteById(Long id);

    boolean existsById(Long id);

    Optional<Book> findMaxPriceBook();

    Optional<Book> findMinPriceBook();

    List<Book> findByPriceRange(BigDecimal min, BigDecimal max);

}