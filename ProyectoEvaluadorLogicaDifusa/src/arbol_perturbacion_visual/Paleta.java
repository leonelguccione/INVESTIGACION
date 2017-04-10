package arbol_perturbacion_visual;

import java.awt.Color;

import java.util.ArrayList;

public class Paleta
{
    private ArrayList<Color> colores;

    public Paleta(ArrayList<Color> colores)
    {
        this.colores = colores;
    }

    public Paleta()
    {
        this.colores = new ArrayList<Color>();
    }

    public void addColor(Color color)
    {
        this.colores.add(color);
    }

    public Color getColor(double valor)
    {
        Color respuesta = null;
        if (this.colores != null && this.colores.size() > 0)
            if (this.colores.size() == 1)
                respuesta = this.colores.get(0);
            else
            {
                if (valor >= 0 && valor < 1)
                {
                    double paso = 1.0 / (this.colores.size() - 1);
                    int indice = (int) (valor / paso);
                    double parametro = valor - indice * paso;
                    double r1 = this.colores.get(indice).getRed();
                    double r2 = this.colores.get(indice + 1).getRed();
                    double g1 = this.colores.get(indice).getGreen();
                    double g2 = this.colores.get(indice + 1).getGreen();
                    double b1 = this.colores.get(indice).getBlue();
                    double b2 = this.colores.get(indice + 1).getBlue();
                    double rojo = r1 - parametro * (r1 - r2);
                    double verde = g1 - parametro * (g1 - g2);
                    double azul = b1 - parametro * (b1 - b2);
                    respuesta = new Color((int) rojo, (int) verde, (int) azul);
                } else if (valor == 1)
                    respuesta = this.colores.get(this.colores.size() - 1);
            }

        return respuesta;
    }
}
