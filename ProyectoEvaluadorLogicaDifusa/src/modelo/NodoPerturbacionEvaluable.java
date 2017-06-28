package modelo;

import java.util.HashMap;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class NodoPerturbacionEvaluable extends NodoPerturbacion
{
    
    private boolean evaluado=true;
    

    public void setEvaluado(boolean evaluado)
    {
        this.evaluado = evaluado;
        
        if (!evaluado && !this.esHoja())
        {
            for (int i = 0; i < this.getChildCount(); i++)
            {
                NodoPerturbacionEvaluable hijo = (NodoPerturbacionEvaluable) this.getChildAt(i);
                hijo.setEvaluado(false);
            }
        }
    }

    public boolean isEvaluado()
    {
        return evaluado;
    }

    public NodoPerturbacionEvaluable(String string)
    {
        super(string);
    }

    public NodoPerturbacionEvaluable(Object object)
    {
        super(object);
    }

    public NodoPerturbacionEvaluable(Object object, boolean b)
    {
        super(object, b);
    }
    
    public NodoPerturbacionEvaluable(NodoPerturbacion base)
    {
     
        this(base.getUserObject());
        if(!base.esHoja())
        for(int i=0;i<base.getChildCount();i++)
        {
            this.add(new NodoPerturbacionEvaluable((NodoPerturbacion)base.getChildAt(i)));
            }
       }
    
    
    public NodoPerturbacionEvaluable(NodoPerturbacion base,HashMap<NodoPerturbacion, NodoPerturbacionEvaluable> hashmap)
    {
     
        this(base.getUserObject());
        hashmap.put(base, this);
        
        if(!base.esHoja())
        for(int i=0;i<base.getChildCount();i++)
        {
            this.add(new NodoPerturbacionEvaluable((NodoPerturbacion)base.getChildAt(i),hashmap));
            }
       }
    
    public boolean tieneHijoEvaluable()
    {
        boolean respuesta = false;
        int tam = this.getChildCount();
        int i = 0;
        while (i < tam && !respuesta)
        {
            NodoPerturbacionEvaluable hijo = (NodoPerturbacionEvaluable) this.getChildAt(i);
            respuesta = hijo.isEvaluado();
            i++;
        }
        return respuesta;
    }


   

}
