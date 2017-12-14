package geometria2d;

public class Segmento
{
private Punto p;
private Punto q;
    
public Punto getP()
{
    return p;
}

public void setP(Punto p)
{
    this.p = p;
}

public Punto getQ()
{
    return q;
}

public void setQ(Punto q)
{
    this.q = q;
}

public Segmento(Punto p, Punto q)
{
    super();
    this.p = p;
    this.q = q;
}

@Override
public String toString()
{
    return "Segmento : " + p + " <--> " + q;
}

public double getLargo()
{
    double cat1=this.getP().getX()-this.getQ().getX();
        double cat2=this.getP().getY()-this.getQ().getY();
        
    double respuesta= Math.sqrt(cat1*cat1+cat2*cat2);
    return respuesta;
    
    }

public double getAngulo()
{
    double respuesta;
    double catAdy=this.getQ().getX()-this.getP().getX();
        double catOp=this.getQ().getY()-this.getP().getY();
        respuesta=Math.acos((catAdy/this.getLargo()));
        if(catOp<0)
            respuesta=Math.PI*2-respuesta;
    
    return respuesta;
    }
}
