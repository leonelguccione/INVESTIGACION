package geometria2d;

public class GeometriaUtil
{

    public static Punto interseccion(Segmento a, Segmento b)
    {
	Punto r = null;
	double[][] matriz = new double[2][2];
	matriz[0][0] = a.getQ().getX() - a.getP().getX();
	matriz[0][1] = b.getP().getX() - b.getQ().getX();
	matriz[1][0] = a.getQ().getY() - a.getP().getY();
	matriz[1][1] = b.getP().getY() - b.getQ().getY();

	double s = determinante(matriz);

	matriz[0][0] = b.getP().getX() - a.getP().getX();
	matriz[0][1] = b.getP().getX() - b.getQ().getX();
	matriz[1][0] = b.getP().getY() - a.getP().getY();
	matriz[1][1] = b.getP().getY() - b.getQ().getY();

	double x = determinante(matriz);

	matriz[0][0] = a.getQ().getX() - a.getP().getX();
	matriz[0][1] = b.getP().getX() - a.getP().getX();
	matriz[1][0] = a.getQ().getY() - a.getP().getY();
	matriz[1][1] = b.getP().getY() - a.getP().getY();

	double y = determinante(matriz);

	if (s != 0)
	{
	    double alpha = x / s;
	    double beta = y / s;
	    if (alpha >= 0 && alpha <= 1 && beta >= 0 && beta <= 1)
	    {

		r = new Punto(a.getP().getX() + alpha * (a.getQ().getX() - a.getP().getX()),
			a.getP().getY() + alpha * (a.getQ().getY() - a.getP().getY()));
	    }

	}
	return r;
    }

    public static double determinante(double[][] matriz)
    {
	return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
    }
    
}
