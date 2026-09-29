package az.caspiantech.kitabxanademo.common.exception;

public class DuplicateIsbnException extends RuntimeException {
    public DuplicateIsbnException(String isbn) {
        super("Book already exists with ISBN: " + isbn);
    }
}
