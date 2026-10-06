
package az.caspiantech.kitabxanademo.book.service.impl;

import az.caspiantech.kitabxanademo.book.dto.request.BookCreateRequest;
import az.caspiantech.kitabxanademo.book.dto.request.BookUpdateRequest;
import az.caspiantech.kitabxanademo.book.dto.response.BookResponse;
import az.caspiantech.kitabxanademo.book.dto.response.BookWithPagesResponse;
import az.caspiantech.kitabxanademo.book.mapper.BookMapper;
import az.caspiantech.kitabxanademo.book.model.Book;
import az.caspiantech.kitabxanademo.book.repository.BookRepository;
import az.caspiantech.kitabxanademo.book.service.BookPageService;
import az.caspiantech.kitabxanademo.book.service.BookService;
import az.caspiantech.kitabxanademo.common.exception.BookNotFoundException;
import az.caspiantech.kitabxanademo.common.pagination.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper mapper;
    private final BookPageService bookPageService;

    @Override
    public BookResponse create(BookCreateRequest request) {
        log.info("Yeni kitab yaradılır: title={}, isbn={}", request.getTitle(), request.getIsbn());
        Book book = mapper.toDomain(request);
        Book saved = bookRepository.save(book);
        log.info("Kitab uğurla yaradıldı: id={}", saved.getId());
        return mapper.toResponse(saved);
    }


    @Override
    public BookResponse getById(Long id) {
        log.info("Kitab axtarılır: id={}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Kitab tapılmadı: id={}", id);
                    return new BookNotFoundException(id);
                });
        return mapper.toResponse(book);
    }

    @Override
    public List<BookResponse> getAll() {
        log.info("Bütün kitabların siyahısı alınır");
        List<BookResponse> result = bookRepository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
        log.debug("Tapılan kitab sayı: {}", result.size());
        return result;
    }

    @Override
    public PageResponse<BookResponse> getAllPaginated(int page, int size, String sortField, boolean ascending) {
        log.info("Səhifələnmiş kitab siyahısı: page={}, size={}, sortField={}, ascending={}",
                page, size, sortField, ascending);
        List<BookResponse> content = bookRepository.findAll(page, size, sortField, ascending).stream()
                .map(mapper::toResponse)
                .toList();

        long totalElements = bookRepository.count();
        log.debug("Səhifədə {} element, ümumi {} element", content.size(), totalElements);

        return PageResponse.of(content, page, size, totalElements);
    }

    @Override
    @Transactional
    public BookResponse update(Long id, BookUpdateRequest request) {
        log.info("Kitab yenilənir: id={}", id);
        if (!bookRepository.existsById(id)) {
            log.warn("Yeniləmə uğursuz oldu, kitab tapılmadı: id={}", id);
            throw new BookNotFoundException(id);
        }

        Book book = mapper.toDomain(request);
        Book updated = bookRepository.update(id, book);
        log.info("Kitab uğurla yeniləndi: id={}", id);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Kitab silinir: id={}", id);
        if (!bookRepository.existsById(id)) {
            log.warn("Silmə uğursuz oldu, kitab tapılmadı: id={}", id);
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
        log.info("Kitab uğurla silindi: id={}", id);
    }

    @Override
    public BookResponse getMaxPriceBook() {
        log.info("Ən baha kitab axtarılır");
        Book book = bookRepository.findMaxPriceBook()
                .orElseThrow(() -> {
                    log.warn("Kitab bazasında heç bir kitab tapılmadı (max price)");
                    return new BookNotFoundException(0L);
                });
        return mapper.toResponse(book);
    }

    @Override
    public BookResponse getMinPriceBook() {
        log.info("Ən ucuz kitab axtarılır");
        Book book = bookRepository.findMinPriceBook()
                .orElseThrow(() -> {
                    log.warn("Kitab bazasında heç bir kitab tapılmadı (min price)");
                    return new BookNotFoundException(0L);
                });
        return mapper.toResponse(book);
    }

    @Override
    public List<BookResponse> getByPriceRange(BigDecimal min, BigDecimal max) {
        log.info("Qiymət aralığına görə axtarış: min={}, max={}", min, max);
        List<BookResponse> result = bookRepository.findByPriceRange(min, max).stream()
                .map(mapper::toResponse)
                .toList();
        log.info("Qiymət aralığında tapılan kitab sayı: {}", result.size());
        return result;
    }

    @Override
    public BookWithPagesResponse getByIdWithPages(Long id) {
        log.info("Kitab (səhifələrlə birgə) axtarılır: id={}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return BookWithPagesResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .price(book.getPrice())
                .pageCount(book.getPageCount())
                .publishedAt(book.getPublishedAt())
                .pages(bookPageService.getAllByBookId(id))
                .build();
    }
}
