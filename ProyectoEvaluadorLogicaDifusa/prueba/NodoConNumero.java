package prueba;

import javax.swing.tree.DefaultMutableTreeNode;

public class NodoConNumero extends DefaultMutableTreeNode
{
    private String texto;
    private Double numero;

    public NodoConNumero(Object object, boolean b)
    {
        super(object, b);
    }

    public NodoConNumero(Object object)
    {
        super(object);
    }

    public NodoConNumero(String texto, Double numero)
    {
        super();
        this.texto = texto;
        this.numero = numero;
    }

    public NodoConNumero()
    {
        super();
    }

    public void setTexto(String texto)
    {
        this.texto = texto;
    }

    public String getTexto()
    {
        return texto;
    }

    public void setNumero(Double numero)
    {
        this.numero = numero;
    }

    public Double getNumero()
    {
        return numero;
    }

    @Override
    public String toString()
    {
        // TODO Implement this method
        return this.getTexto() + "\n" + this.numero;
    }
}
