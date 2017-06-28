package modelo;

public interface IConjuntoDifuso
{
    /**
     * @return the desconocido
     */
    double getDesconocido();

    /**
     * @param desconocido the desconocido to set
     */
    void setDesconocido(double desconocido);

    /**
     * @return the insatisfactorio
     */
    double getParcialmenteConocido();

    /**
     * @param parcialmente the insatisfactorio to set
     */
    void setParcialmenteConocido(double parcialmente);

    /**
     * @return the conocido
     */
    double getConocido();

    /**
     * @param conocido the conocido to set
     */
    void setConocido(double conocido);

    /**
     * @return the aprendido
     */
    double getAprendido();

    /**
     * @param aprendido the aprendido to set
     */
    void setAprendido(double aprendido);

    boolean isValid();

    boolean isCero();

    void inicializar();

    double getNota();

    void setEtiquetaDesdeNota(double nota) throws Exception;
}
