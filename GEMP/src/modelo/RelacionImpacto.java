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

    @Override
    public String toString()
    {
        // TODO Implement this method
        return "Valor: "+this.getValor()+" Destino: "+this.getNodo();
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof RelacionImpacto))
        {
            return false;
        }
        final RelacionImpacto other = (RelacionImpacto) object;
        if (!(nodo == null ? other.nodo == null : nodo.equals(other.nodo)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((nodo == null) ? 0 : nodo.hashCode());
        return result;
    }

}
