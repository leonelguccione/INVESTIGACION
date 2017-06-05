package paquete;



public class NotSemejanteException extends Exception
{
    public NotSemejanteException(String string, Throwable throwable, boolean b, boolean b1)
    {
        super(string, throwable, b, b1);
    }

    public NotSemejanteException(Throwable throwable)
    {
        super(throwable);
    }

    public NotSemejanteException(String string, Throwable throwable)
    {
        super(string, throwable);
    }

    public NotSemejanteException(String string)
    {
        super(string);
    }

    public NotSemejanteException()
    {
        super();
    }
}
