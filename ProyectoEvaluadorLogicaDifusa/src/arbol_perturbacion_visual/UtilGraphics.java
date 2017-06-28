package arbol_perturbacion_visual;

import java.awt.Color;
import java.awt.Graphics;

public class UtilGraphics
{
    public static void lineaDegrade(Graphics g, int x1, int y1, int x2, int y2, Color origen, Color destino, int pasos)
    {
        double dif_X = (double)(x2 - x1) / pasos;
       double dif_Y = (double)(y2 - y1) / pasos;
        double dif_Red=(double)(destino.getRed()-origen.getRed())/pasos;
        double dif_Green=(double)(destino.getGreen()-origen.getGreen())/pasos;
        double dif_Blue=(double)(destino.getBlue()-origen.getBlue())/pasos;
        double dif_Alpha=(double)(destino.getAlpha()-origen.getAlpha())/pasos;
        
        
        for (int i = 0; i < pasos; i++)
        {
            
            g.setColor(new Color(origen.getRed()+(int)(dif_Red*i),origen.getGreen()+(int)(dif_Green*i),origen.getBlue()+(int)(dif_Blue*i),origen.getAlpha()+(int)(dif_Alpha*i)));
            g.drawLine(x1 + (int)(dif_X * i), y1 +(int) (dif_Y * i), x1 +(int) (dif_X * (i + 1)), y1 + (int)(dif_Y * (i + 1)));
        }


    }
    
    public static Color getColorAlpha(Color c,int alpha)
    {
            Color respuesta=new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
            return respuesta;
     
        }
}
