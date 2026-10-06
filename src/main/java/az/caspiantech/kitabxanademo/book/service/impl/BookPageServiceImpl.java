package az.caspiantech.kitabxanademo.book.service.impl;

import az.caspiantech.kitabxanademo.book.dto.response.BookPageResponse;
import az.caspiantech.kitabxanademo.book.mapper.BookPageMapper;
import az.caspiantech.kitabxanademo.book.model.BookPage;
import az.caspiantech.kitabxanademo.book.repository.BookPageRepository;
import az.caspiantech.kitabxanademo.book.service.BookPageService;
import az.caspiantech.kitabxanademo.common.exception.BookPageNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookPageServiceImpl implements BookPageService {


    private final BookPageRepository bookPageRepository;
    private final BookPageMapper mapper;

    @Override
    public List<BookPageResponse> getAllByBookId(Long bookId) {
        log.info("Kitabın bütün səhifələri gətirilir: bookId={}", bookId);
        return bookPageRepository.findAllByBookId(bookId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public BookPageResponse getByBookIdAndPageNumber(Long bookId, int pageNumber) {

        log.info("Səhifə axtarılır: bookId={}, pageNumber={}", bookId, pageNumber);
        BookPage bookPage = bookPageRepository.findByBookIdAndPageNumber(bookId, pageNumber)
                .orElseThrow(() -> new BookPageNotFoundException(bookId, pageNumber));
        return mapper.toResponse(bookPage);
    }

}
