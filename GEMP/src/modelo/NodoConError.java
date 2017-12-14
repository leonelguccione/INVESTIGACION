package modelo;

public class NodoConError implements Comparable
{
    private NodoPerturbacion nodo;
    private Integer cantidad;

    public NodoConError(NodoPerturbacion nodo, Integer cantidad)
    {
        this.nodo = nodo;
        this.cantidad = cantidad;
    }

    public void setNodo(NodoPerturbacion nodo)
    {
        this.nodo = nodo;
    }

    public NodoPerturbacion getNodo()
    {
        return nodo;
    }

    public void setCantidad(Integer cantidad)
    {
        this.cantidad = cantidad;
    }

    public Integer getCantidad()
    {
        return cantidad;
    }

    @Override
    public int compareTo(Object o)
    {
        // TODO Implement this method
        NodoConError otro = (NodoConError) o;
        int resultado = this.getCantidad() - otro.getCantidad();
        if (resultado == 0)
        {
            double r = this.getNodo().getNota() - otro.getNodo().getNota();
            if (r > 0)
                resultado = 1;
            else if (r < 0)
                resultado = -1;
        }

        return resultado;
    }
}
