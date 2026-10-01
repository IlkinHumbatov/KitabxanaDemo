package az.caspiantech.kitabxanademo.book.service.impl;

import az.caspiantech.kitabxanademo.book.mapper.BookPageMapper;
import az.caspiantech.kitabxanademo.book.model.BookPage;
import az.caspiantech.kitabxanademo.book.repository.BookPageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookPageServiceImpl implements BookPageRepository {


    private final BookPageRepository bookPageRepository;
    private final BookPageMapper mapper;

    @Override
    public List<BookPage> getAllByBookId(Long bookId) {
        log.info("Kitabın bütün səhifələri gətirilir: bookId={}", bookId);
        return bookPageRepository.findAllByBookId(bookId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<BookPage> findAllByBookId(Long bookId) {
        return List.of();
    }

    @Override
    public Optional<BookPage> findByBookIdAndPageNumber(Long bookId, int pageNumber) {
        return Optional.empty();
    }
}
