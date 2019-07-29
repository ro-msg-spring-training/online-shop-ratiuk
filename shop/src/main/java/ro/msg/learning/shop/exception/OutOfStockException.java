package ro.msg.learning.shop.exception;

public class OutOfStockException extends RuntimeException {
    private static final long serialVersionUID = 5226631307951164950L;

    public OutOfStockException(Long id) {
        super("Product with id " + id + " is out of stock!");
    }
}
