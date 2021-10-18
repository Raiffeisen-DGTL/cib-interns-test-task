package socksRepositoryTestTask.exception;

public class NoSuchEntityOnDatabaseException extends RuntimeException{
    public NoSuchEntityOnDatabaseException() {
        super("no such requested entity on database");
    }
}
