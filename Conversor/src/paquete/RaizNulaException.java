package paquete;



public class RaizNulaException extends Exception {
    public RaizNulaException(String string, Throwable throwable, boolean b, boolean b1) {
        super(string, throwable, b, b1);
    }

    public RaizNulaException(Throwable throwable) {
        super(throwable);
    }

    public RaizNulaException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public RaizNulaException(String string) {
        super(string);
    }

    public RaizNulaException() {
        super();
    }
}
