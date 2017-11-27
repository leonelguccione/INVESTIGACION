package modelo;

import java.util.HashMap;

public class ResultadoAnalisisArbol
{
    private HashMap<NodoPerturbacion, Integer> errores = new HashMap<NodoPerturbacion, Integer>();
    private ArbolPerturbacion arbol;

    public ResultadoAnalisisArbol()
    {
        super();
    }

    void sumaError(NodoPerturbacion nodoAnalizable)
    {
        Integer contador = this.errores.get(nodoAnalizable);

        if (contador == null)
            contador = 1;
        else
            contador++;
        this.errores.put(nodoAnalizable, contador);

    }

    public HashMap<NodoPerturbacion, Integer> getErrores()
    {
        return errores;
    }

    public void setArbol(ArbolPerturbacion arbol)
    {
        this.arbol = arbol;
    }

    public ArbolPerturbacion getArbol()
    {
        return arbol;
    }
}
