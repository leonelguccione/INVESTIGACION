package modelo;

import base_de_datos.BaseDeDatos;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/* Requerimientos de esta clase
 * crear_arbol()
 * alta_nodo()
 * limpiar()
 * guardar().- guardar el arbol creado y agregarlo a la lista de arboles
 * cargar().- cargar el listado de arboles
 *
 *
 */


public class Modelo_ABM_arbol_perturbacion
{
    BaseDeDatos db ;
    /**
     * listado de arboles de perturbación vacíos.
     * @aggregation shared
     */
  
    private Map<String, byte[]> mapa_arboles_perturbacion_serializado;

    /**
     * arbol de perturbación en uso. Ya sea seleccionado o creado nuevo.
     */
    
    public Modelo_ABM_arbol_perturbacion(BaseDeDatos db)
    {
        super();
        mapa_arboles_perturbacion_serializado = new TreeMap<String, byte[]>();
        this.db = db;
    }
    
    public void guardar_arbol_perturbacion(Arbol_Perturbacion arbol)
    {
        db.almacenar_arbol_perturbacion(arbol);
    }
    
    
    /**
     * @return retorna un iterator de arboles de perturbacion.
     * Toma los datos desde el almacenamiento y genera un Iterator para agregar a la jList.
     * 
     */
   
    public Iterator getIterator_listado_arboles_perturbacion()
    {
        return db.recuperar_arbol_perturbacion();
    }
    
    public void borrar_arbol_perturbacion(Arbol_Perturbacion arbol)
    {
        db.borrar_arbol_perturbacion(arbol);
    }
    

  


}
