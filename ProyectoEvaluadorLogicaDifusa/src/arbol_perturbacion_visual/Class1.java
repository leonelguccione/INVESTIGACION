package arbol_perturbacion_visual;

import java.awt.Color;

public class Class1
{
    public Class1()
    {
        super();
    }

    public static void main(String[] args)
    {
        Class1 class1 = new Class1();
        Paleta p = new Paleta();
        p.addColor(new Color(255, 0, 0));
        p.addColor(new Color(0, 255, 0));
        p.addColor(new Color(0, 0, 255));
        p.getColor(0.5);

    }
}
