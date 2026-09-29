package az.caspiantech.kitabxanademo.common.exception;

public class BookPageNotFoundException extends RuntimeException {
    public BookPageNotFoundException(Long bookId, int pageNumber) {
        super("Səhifə tapılmadı: kitab id=" + bookId + ", səhifə=" + pageNumber);
    }
}
