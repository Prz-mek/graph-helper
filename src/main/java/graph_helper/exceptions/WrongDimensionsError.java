package graph_helper.exceptions;

public class WrongDimensionsError extends RuntimeException {
    public WrongDimensionsError() {
        super("Array must have the same number of rows as columns!");
    }
}
