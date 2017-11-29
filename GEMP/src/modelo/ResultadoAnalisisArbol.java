package modelo;

import java.util.ArrayList;
import java.util.Enumeration;
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

    public void sumaError(NodoPerturbacion nodoAnalizable)
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
    {
        TreeSet<NodoConError> result = new TreeSet<NodoConError>();

        Iterator<NodoPerturbacion> it = this.getErrores()
                                            .keySet()
                                            .iterator();
        while (it.hasNext())
        {
            NodoPerturbacion n = it.next();
            Integer i = this.getErrores().get(n);
            result.add(new NodoConError(n, i));


        }
        return result;

    }

    void analizaCorrecciones(ArrayList<ArbolPerturbacion> lista)
    {
        if (this.arbol != null)
        {
            NodoPerturbacionEvaluable evaluable = (NodoPerturbacionEvaluable) this.getArbol().getRaiz();
            for (int i = 0; i < lista.size(); i++)
            {
                NodoPerturbacionEvaluable evaluable_i = (NodoPerturbacionEvaluable) lista.get(i).getRaiz();

                this.cuentaErroresRecursivo(evaluable, evaluable_i);
            }
        }


    }

    private void cuentaErroresRecursivo(NodoPerturbacionEvaluable nodoGeneral, NodoPerturbacionEvaluable nodoParticular)
    {
        double sumaErrores = nodoParticular.getDesconocido() + nodoParticular.getParcialmenteConocido();

        if (sumaErrores == 1 && !nodoParticular.tieneHijoEvaluable() && nodoParticular.isEvaluado())
            this.sumaError(nodoGeneral);


        if (nodoParticular.tieneHijoEvaluable())
        {
            Enumeration hijos_propios = nodoGeneral.children();
            Enumeration hijos_analizable = nodoParticular.children();
            NodoPerturbacionEvaluable proximo_analizable;
            NodoPerturbacionEvaluable proximo_propio;
            while (hijos_propios.hasMoreElements() && hijos_analizable.hasMoreElements())
            {
                proximo_analizable = (NodoPerturbacionEvaluable) hijos_analizable.nextElement();
                proximo_propio = (NodoPerturbacionEvaluable) hijos_propios.nextElement();
                this.cuentaErroresRecursivo(proximo_propio, proximo_analizable);

            }
        }

    }
}
