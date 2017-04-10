/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author leonel
 */
public class EtiquetaBean implements Cloneable, Serializable
{
    @SuppressWarnings("compatibility:-6277143836951072233")
    private static final long serialVersionUID = -5711517413450138642L;

    //   public static final String PROP_DESCONOCIDO_PROPERTY = "desconocidoProperty";
    private double desconocido;

    //   public static final String PROP_INSATISFACTORIO_PROPERTY = "insatisfactorioProperty";
    private double parcialmente_conocido;

    //    public static final String PROP_CONOCIDO_PROPERTY = "conocidoProperty";
    private double conocido;

    //    public static final String PROP_APRENDIDO_PROPERTY = "aprendidoProperty";
    private double aprendido;

    //    private PropertyChangeSupport propertySupport;


    public EtiquetaBean(double aprendido, double conocido, double parcialmente_conocido, double desconocido)
    {
        super();
        this.desconocido = desconocido;
        this.parcialmente_conocido = parcialmente_conocido;
        this.conocido = conocido;
        this.aprendido = aprendido;

    }

    public EtiquetaBean()
    {
        this(0.00, 0.00, 0.00, 0.00);
    }


    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    /**
     * @return the desconocido
     */
    public double getDesconocido()
    {
        return desconocido;
    }

    /**
     * @param desconocido the desconocido to set
     */
    public void setDesconocido(double desconocido)
    {
        this.desconocido = desconocido;
    }

    /**
     * @return the insatisfactorio
     */
    public double getParcialmenteConocido()
    {
        return parcialmente_conocido;
    }

    /**
     * @param parcialmente the insatisfactorio to set
     */
    public void setParcialmenteConocido(double parcialmente)
    {
        this.parcialmente_conocido = parcialmente;
    }

    /**
     * @return the conocido
     */
    public double getConocido()
    {
        return conocido;
    }

    /**
     * @param conocido the conocido to set
     */
    public void setConocido(double conocido)
    {
        this.conocido = conocido;
    }

    /**
     * @return the aprendido
     */
    public double getAprendido()
    {
        return aprendido;
    }

    /**
     * @param aprendido the aprendido to set
     */
    public void setAprendido(double aprendido)
    {
        this.aprendido = aprendido;
    }


    public void suma(EtiquetaBean e)
    {
        setDesconocido(desconocido + e.getDesconocido());
        setParcialmenteConocido(parcialmente_conocido + e.getParcialmenteConocido());
        setConocido(conocido + e.getConocido());
        setAprendido(aprendido + e.getAprendido());
    }

    public void resta(EtiquetaBean e)
    {
        setDesconocido(desconocido - e.getDesconocido());
        setParcialmenteConocido(parcialmente_conocido - e.getParcialmenteConocido());
        setConocido(conocido - e.getConocido());
        setAprendido(aprendido - e.getAprendido());
    }

    void dividir(double cant) throws ArithmeticException
    {
        setAprendido(aprendido / cant);
        setConocido(conocido / cant);
        setDesconocido(desconocido / cant);
        setParcialmenteConocido(parcialmente_conocido / cant);
    }

    void multiplica(double cant)
    {
        setAprendido(aprendido * cant);
        setConocido(conocido * cant);
        setDesconocido(desconocido * cant);
        setParcialmenteConocido(parcialmente_conocido * cant);
    }

    @Override
    public String toString()
    {
        String stDesconocido = String.format("%.2f", getDesconocido());
        String stInsatisfactorio = String.format("%.2f", getParcialmenteConocido());
        String stConocido = String.format("%.2f", getConocido());
        String stAprendido = String.format("%.2f", getAprendido());
        return ("<" + stDesconocido + "  " + stInsatisfactorio + "\n" + stConocido + "  " + stAprendido + ">");
    }

    public boolean isValid()
    {
        double s = getAprendido() + getConocido() + getDesconocido() + getParcialmenteConocido();
        boolean suma_uno = (Math.abs(s - 1.00) < 0.001);
        boolean caso1 =
            getAprendido() >= 0.0 && getConocido() >= 0.0 && getParcialmenteConocido() == 0.0 &&
            getDesconocido() == 0.0;
        boolean caso2 =
            getAprendido() == 0.0 && getConocido() >= 0.0 && getParcialmenteConocido() >= 0.0 &&
            getDesconocido() == 0.0;
        boolean caso3 =
            getAprendido() == 0.0 && getConocido() == 0.0 && getParcialmenteConocido() >= 0.0 &&
            getDesconocido() >= 0.0;
        return ((caso1 || caso2 || caso3) && suma_uno);

    }

    public boolean isCero()
    {
        return (getAprendido() == 0.0 && getConocido() == 0.0 && getParcialmenteConocido() == 0.0 &&
                getDesconocido() == 0.0);

    }

    public void inicializar()
    {
        setAprendido(0.00);
        setConocido(0.00);
        setDesconocido(0.00);
        setParcialmenteConocido(0.00);
    }

    public double getNota()
    {
        return this.getParcialmenteConocido() * 0.40 + this.getConocido() * 0.65 + this.getAprendido() * 1;
    }

    public void setEtiquetaDesdeNota(double nota) throws Exception
    {
        if (nota < 0 || nota > 1)
            throw new Exception("Nota no válida");
        if (nota <= 0.30)
        {
            this.desconocido = 1;
            this.parcialmente_conocido = 0;
            this.conocido = 0;
            this.aprendido = 0;
        } else if (nota < 0.40)
        {
            this.parcialmente_conocido = (nota - 0.30) * 10;
            this.desconocido = 1 - this.parcialmente_conocido;
            this.conocido = 0;
            this.aprendido = 0;
        } else if (nota < 0.55)
        {
            this.desconocido = 0;
            this.parcialmente_conocido = 1;
            this.conocido = 0;
            this.aprendido = 0;
        } else if (nota < 0.65)
        {
            this.desconocido = 0;
            this.conocido = (nota - 0.55) * 10;
            this.parcialmente_conocido = 1 - this.conocido;
            this.aprendido = 0;
        } else if (nota < 0.80)
        {
            this.desconocido = 0;
            this.parcialmente_conocido = 0;
            this.conocido = 1;
            this.aprendido = 0;
        } else if (nota < 0.90)
        {
            this.desconocido = 0;
            this.parcialmente_conocido = 0;
            this.aprendido = (nota - 0.8) * 10;
            this.conocido = 1 - this.aprendido;
        } else
        {
            this.desconocido = 0;
            this.parcialmente_conocido = 0;
            this.conocido = 0;
            this.aprendido = 1;
        }


    }

}
