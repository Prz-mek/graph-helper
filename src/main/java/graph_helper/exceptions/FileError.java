package graph_helper.exceptions;

public class FileError extends RuntimeException{
    public FileError() {
        super("The given path points to a file that doesn't exist!");
    }
}
