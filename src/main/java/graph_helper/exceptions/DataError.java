package graph_helper.exceptions;

public class DataError extends RuntimeException{
    public DataError() {
        super("The given graph data is not supported!");
    }
}
