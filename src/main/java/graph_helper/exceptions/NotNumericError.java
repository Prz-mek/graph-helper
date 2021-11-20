package graph_helper.exceptions;

public class NotNumericError extends RuntimeException {
    public NotNumericError() {
        super("All data in array must be numeric!");
    }
}
