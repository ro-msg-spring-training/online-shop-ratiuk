package ro.msg.learning.shop.exception;

public class NoLocationForStocksException extends RuntimeException {
    private static final long serialVersionUID = 6258572634467994992L;

    public NoLocationForStocksException() {
        super("No location with stock found");
    }
}
