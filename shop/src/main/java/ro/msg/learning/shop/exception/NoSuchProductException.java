package ro.msg.learning.shop.exception;

public class NoSuchProductException extends RuntimeException {
    private static final long serialVersionUID = -2239296367572091148L;

    public NoSuchProductException(Long id) {
        super("Product with id " + id + " was not found");
    }
}
