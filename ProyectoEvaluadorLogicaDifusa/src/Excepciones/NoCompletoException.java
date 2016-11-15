package Excepciones;

public class NoCompletoException extends Exception {
    public NoCompletoException(String string, Throwable throwable, boolean b, boolean b1) {
        super(string, throwable, b, b1);
    }

    public NoCompletoException(Throwable throwable) {
        super(throwable);
    }

    public NoCompletoException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public NoCompletoException(String string) {
        super(string);
    }

    public NoCompletoException() {
        super();
    }
}
