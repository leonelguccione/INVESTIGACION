package modelo;

import java.util.ArrayList;


/**Representa Parcial1, Parcial2, Totalizador...
 */
public class Parcial
{
    private String nombre;
    private Arbol_Perturbacion arbol_podado = null;
    private ArrayList<Instancia_Evaluacion> instancias = new ArrayList<Instancia_Evaluacion>();

    public Parcial()
    {
        super();
    }
    
}
