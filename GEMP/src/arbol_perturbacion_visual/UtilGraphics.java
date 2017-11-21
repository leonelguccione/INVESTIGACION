package arbol_perturbacion_visual;

import geometria2d.GeometriaUtil;
import geometria2d.Punto;

import geometria2d.Segmento;

import java.awt.Color;
import java.awt.Graphics;

public class UtilGraphics
{
    public static void lineaDegrade(Graphics g, int x1, int y1, int x2, int y2, Color origen, Color destino, int pasos)
    {
        double dif_X = (double) (x2 - x1) / pasos;
        double dif_Y = (double) (y2 - y1) / pasos;
        double dif_Red = (double) (destino.getRed() - origen.getRed()) / pasos;
        double dif_Green = (double) (destino.getGreen() - origen.getGreen()) / pasos;
        double dif_Blue = (double) (destino.getBlue() - origen.getBlue()) / pasos;
        double dif_Alpha = (double) (destino.getAlpha() - origen.getAlpha()) / pasos;


        for (int i = 0; i < pasos; i++)
        {

            g.setColor(new Color(origen.getRed() + (int) (dif_Red * i), origen.getGreen() + (int) (dif_Green * i),
                                 origen.getBlue() + (int) (dif_Blue * i), origen.getAlpha() + (int) (dif_Alpha * i)));
            g.drawLine(x1 + (int) (dif_X * i), y1 + (int) (dif_Y * i), x1 + (int) (dif_X * (i + 1)),
                       y1 + (int) (dif_Y * (i + 1)));
        }


    }

    public static Color getColorAlpha(Color c, int alpha)
    {
        Color respuesta = new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
        return respuesta;

    }

    static void flecha(Graphics g, int x1, int y1, int x2, int y2, Color colorOrigen, Color colorDestino, int ancho,
                       int alto, int tamFlecha, int pasos)
    {
        Punto origen_bl = new Punto(x1 - ancho / 2, y1 + alto / 2);
        Punto origen_br = new Punto(x1 + ancho / 2, y1 + alto / 2);
        Punto origen_tl = new Punto(x1 - ancho / 2, y1 - alto / 2);
        Punto origen_tr = new Punto(x1 + ancho / 2, y1 - alto / 2);
        Punto origen = null;

        Punto destino_bl = new Punto(x2 - ancho / 2, y2 + alto / 2);
        Punto destino_br = new Punto(x2 + ancho / 2, y2 + alto / 2);
        Punto destino_tl = new Punto(x2 - ancho / 2, y2 - alto / 2);
        Punto destino_tr = new Punto(x2 + ancho / 2, y2 - alto / 2);
        Punto destino = null;


        Punto origen_centro = new Punto(x1, y1);
        Punto destino_centro = new Punto(x2, y2);
        Segmento linea = new Segmento(origen_centro, destino_centro);

        Segmento[] segmentos_origen =
        {
            new Segmento(origen_br, origen_tr), new Segmento(origen_bl, origen_tl), new Segmento(origen_tl, origen_tr),
            new Segmento(origen_br, origen_bl)
        };

        Segmento[] segmentos_destino =
        {
            new Segmento(destino_br, destino_tr), new Segmento(destino_bl, destino_tl),
            new Segmento(destino_tl, destino_tr), new Segmento(destino_br, destino_bl)
        };


        int i = 0;

        while (origen == null && i < 4)
        {
            origen = GeometriaUtil.interseccion(linea, segmentos_origen[i]);
            i++;
        }

        i = 0;
        while (destino == null && i < 4)
        {
            destino = GeometriaUtil.interseccion(linea, segmentos_destino[i]);
            i++;
        }
        UtilGraphics.lineaDegrade(g, (int) origen.getX(), (int) origen.getY(), (int) destino.getX(),
                                  (int) destino.getY(), colorOrigen, colorDestino, pasos);


        g.setColor(colorDestino);
        double angulo = linea.getAngulo();
        double angulo1 = angulo + Math.PI * (11.0 / 12.0);
        double angulo2 = angulo - Math.PI * (11.0 / 12.0);

        Punto costado1 =
            new Punto(destino.getX() + Math.cos(angulo1) * tamFlecha, destino.getY() + Math.sin(angulo1) * tamFlecha);
        Punto costado2 =
            new Punto(destino.getX() + Math.cos(angulo2) * tamFlecha, destino.getY() + Math.sin(angulo2) * tamFlecha);
        g.drawLine((int) destino.getX(), (int) destino.getY(), (int) costado1.getX(), (int) costado1.getY());
        g.drawLine((int) destino.getX(), (int) destino.getY(), (int) costado2.getX(), (int) costado2.getY());
        int[] xPoints =
        {
            (int) destino.getX(), (int) costado1.getX(), (int) costado2.getX()
        };
        int[] yPoints =
        {
            (int) destino.getY(), (int) costado1.getY(), (int) costado2.getY()
        };
        g.fillPolygon(xPoints, yPoints, 3);
    }
}
