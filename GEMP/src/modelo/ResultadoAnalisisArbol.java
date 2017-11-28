package modelo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

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
    public TreeSet<NodoConError> getNodosCOnError()
    {TreeSet<NodoConError> result=new TreeSet<NodoConError>();
     
            Iterator <NodoPerturbacion> it=this.getErrores().keySet().iterator();
            while (it.hasNext())
            {
                NodoPerturbacion n=it.next();
                Integer i=this.getErrores().get(n);
                result.add(new NodoConError(n,i));
                
                
                }
     return result;
        
        }
}
