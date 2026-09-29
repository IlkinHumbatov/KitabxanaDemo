package az.caspiantech.kitabxanademo.common.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id){
        super("Book not found with id: " + id);
    }

    public BookNotFoundException(Long bookId, int pageNumber) {
        super("Səhifə tapılmadı: kitab id=" + bookId + ", səhifə=" + pageNumber);
    }
}
