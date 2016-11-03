package util;

import java.util.Enumeration;

import modelo.Arbol_Perturbacion;
import modelo.Nodo_Perturbacion;

public class Algebra_Arbol
{
    public Algebra_Arbol()
    {
        super();
    }

    public static boolean isSemejante(Arbol_Perturbacion a, Arbol_Perturbacion b)
    {
        boolean resultado = true;

        return compara_nodos(a.getRaiz(), b.getRaiz());
    }

    private static boolean compara_nodos(Nodo_Perturbacion n, Nodo_Perturbacion m)
    {
        boolean resultado;

        //Si los dos nodos referencian al mismo objeto, o ambos son null, entonces son semejantes.
        if (n == m)
            resultado = true;
        //si no referencian al mismo objeto

        else
        {
            //si ambos sean diferentes de null
            if (n != null && m != null)
            {
                //si sus id de dato contienen el mismo texto
                if (n.getDato().getIdDato().equals(m.getDato().getIdDato()))
                { //si n y m no tienen la misma cantidad de hijos NO son semejantes
                    if (n.getChildCount() != n.getChildCount())
                    {
                        resultado = false;
                    }
                    //si m es rama entonces
                    else
                    { //si n y me tienen la misma cantidad de hijos  entonces si, ambos son hojas son semejantes
                        if (n.esHoja())
                            resultado = true;
                        else
                        //si ambos son ramas con igual cantidad de hijos, entonces debo verificar la semenjanza de cada hijo
                        {
                            Enumeration hijos_N = n.children();
                            Enumeration hijos_M = m.children();
                            Nodo_Perturbacion proximo_M;
                            Nodo_Perturbacion proximo_N;
                            boolean semejanzaparcial = true;
                            while (hijos_N.hasMoreElements() && hijos_M.hasMoreElements() && semejanzaparcial)
                            {
                                proximo_M = (Nodo_Perturbacion) hijos_M.nextElement();
                                proximo_N = (Nodo_Perturbacion) hijos_N.nextElement();
                                semejanzaparcial = compara_nodos(proximo_N, proximo_M);

                            }
                            resultado = semejanzaparcial;
                        }
                    }
                }
                //si sus id de dato contienen textos diferentes NO son semejantes
                else
                {
                    resultado = false;
                }
            }
            //Si SOLO un nodo es null entonces NO son semejantes
            else
                resultado = false;
        }
        return resultado;
    }
}
