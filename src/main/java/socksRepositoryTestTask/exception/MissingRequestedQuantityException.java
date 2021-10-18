package socksRepositoryTestTask.exception;

public class MissingRequestedQuantityException extends RuntimeException{
    public MissingRequestedQuantityException() {
        super("missing requested quantity to outcome on database");
    }
}
