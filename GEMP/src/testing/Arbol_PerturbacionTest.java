package testing;

import modelo.ArbolPerturbacion;

import modelo.NodoPerturbacion;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Arbol_PerturbacionTest
{
    private ArbolPerturbacion arbol_perturbacion;
    private ArbolPerturbacion otro_arbol_perturbacion;
    
    public Arbol_PerturbacionTest()
    {
    }
    
    public ArbolPerturbacion construir_arbol()
    {
        String id = "id1";
        String descripcion = "D1";
        ArbolPerturbacion aux = new ArbolPerturbacion(id, descripcion);
        NodoPerturbacion raiz = new NodoPerturbacion("nodoRaiz");
        aux.agregarNodo(raiz, raiz);//como el arbol esta vacio usa solamente el primer parametro
       
        //creo varios nodos internos y los agrego al arbol
        NodoPerturbacion h1 = new NodoPerturbacion("h1");
        NodoPerturbacion h2 = new NodoPerturbacion("h2");
        NodoPerturbacion n1delh1 = new NodoPerturbacion("n1delh1");
        NodoPerturbacion n2delh1 = new NodoPerturbacion("n2delh1");
        NodoPerturbacion n1delh2 = new NodoPerturbacion("n1delh2");
        NodoPerturbacion n2delh2 = new NodoPerturbacion("n2delh2");
        NodoPerturbacion n3delh2 = new NodoPerturbacion("n3delh2");
        NodoPerturbacion n4delh2 = new NodoPerturbacion("n4delh2");
                aux.agregarNodo(raiz,h1);
        aux.agregarNodo(raiz,h2);
        aux.agregarNodo(h1, n1delh1);
        aux.agregarNodo(h1, n2delh1);
        aux.agregarNodo(h2, n1delh2);
        aux.agregarNodo(h2, n2delh2);
        aux.agregarNodo(h2, n3delh2);
        aux.agregarNodo(h2, n4delh2);
        return aux;
    }
    
    public ArbolPerturbacion otro_construir_arbol()
    {
        String id = "id1";
        String descripcion = "D1";
        ArbolPerturbacion aux = new ArbolPerturbacion(id, descripcion);
        NodoPerturbacion raiz = new NodoPerturbacion("nodoRaiz");
        aux.agregarNodo(raiz, raiz);//como el arbol esta vacio usa solamente el primer parametro
       
        //creo varios nodos internos y los agrego al arbol
        NodoPerturbacion h1 = new NodoPerturbacion("h1");
        NodoPerturbacion h2 = new NodoPerturbacion("h2");
        NodoPerturbacion n1delh1 = new NodoPerturbacion("n1delh1Distinto"); //cambio de identificador
        NodoPerturbacion n2delh1 = new NodoPerturbacion("n2delh1");
        NodoPerturbacion n1delh2 = new NodoPerturbacion("n1delh2");
        NodoPerturbacion n2delh2 = new NodoPerturbacion("n2delh2");
        NodoPerturbacion n3delh2 = new NodoPerturbacion("n3delh2");
        NodoPerturbacion n4delh2 = new NodoPerturbacion("n4delh2");
                aux.agregarNodo(raiz,h1);
        aux.agregarNodo(raiz,h2);
        aux.agregarNodo(h1, n1delh1);
        aux.agregarNodo(h1, n2delh1);
        aux.agregarNodo(h2, n1delh2);
        aux.agregarNodo(h2, n2delh2);
        aux.agregarNodo(h2, n3delh2);
        aux.agregarNodo(h2, n4delh2);
        return aux;
    }
    
    
    
    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }

    /**
     * @see ArbolPerturbacion#isSemejante(Arbol_Perturbacion)
     * Se comprueba si dos árboles son semejantes.
     * La semejanza está determinada por las siguientes condiciones:
     * - la igualdad entre los campos dato->idDato de cada nodo correspondiente
     * - la misma estructura de árbol.
     */
    @Test
    public void testIsSemejante()
    {
        this.arbol_perturbacion = this.construir_arbol();
        this.otro_arbol_perturbacion = this.construir_arbol();
        Assert.assertTrue("deberían ser semejantes", arbol_perturbacion.isSemejante(otro_arbol_perturbacion));
    }
    
    @Test
    public void testNotIsSemejante()
    {
        this.arbol_perturbacion = this.construir_arbol();
        this.otro_arbol_perturbacion = this.otro_construir_arbol();
        Assert.assertFalse("deberían ser diferentes", arbol_perturbacion.isSemejante(otro_arbol_perturbacion));
    }
}
