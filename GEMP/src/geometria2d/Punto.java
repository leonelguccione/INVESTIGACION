package geometria2d;

public class Punto
{
    private double x;
    private double y;

    public Punto(double x, double y)
    {
	super();
	this.x = x;
	this.y = y;
    }

    public Punto()
    {
	this(0, 0);
    }

    public double getX()
    {
	return x;
    }

    public void setX(double x)
    {
	this.x = x;
    }

    public double getY()
    {
	return y;
    }

    public void setY(double y)
    {
	this.y = y;
    }

    @Override
    public String toString()
    {
	return "Punto: ( " + x + " ; " + y + " )";
    }
    

}
