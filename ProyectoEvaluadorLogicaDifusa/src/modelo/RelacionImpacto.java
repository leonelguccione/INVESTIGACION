package modelo;

import java.io.Serializable;

public class RelacionImpacto implements Serializable
{
    private NodoPerturbacion nodo;
    private double valor;

    public void setNodo(NodoPerturbacion nodo)
    {
        this.nodo = nodo;
    }

    public NodoPerturbacion getNodo()
    {
        return nodo;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }

    public double getValor()
    {
        return valor;
    }


    public RelacionImpacto(NodoPerturbacion nodo, double valor)
    {
        this.nodo = nodo;
        this.valor = valor;
    }
    public RelacionImpacto(NodoPerturbacion nodo)
    {
        this(nodo,0.0);
        }
    
}
