package graph_helper.exceptions;

public class NoPathError extends RuntimeException {
    public NoPathError() {
        super("Path between given point doesn't exist!");
    }
}
