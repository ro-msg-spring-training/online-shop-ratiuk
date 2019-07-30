package ro.msg.learning.shop.exception;

public class OrderCreationException extends RuntimeException {
    private static final long serialVersionUID = -2946634712073942020L;

    public OrderCreationException() {
        super("Failed to create Order");
    }
}
