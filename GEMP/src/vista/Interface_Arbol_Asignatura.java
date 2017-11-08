package vista;

import modelo.ArbolPerturbacion;

public interface Interface_Arbol_Asignatura
{
    ArbolPerturbacion getArbol();
    void setArbol(ArbolPerturbacion arbol);
    void setModoEdicion(boolean b);
}
