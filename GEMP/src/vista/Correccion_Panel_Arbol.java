package vista;


import excepciones.RaizNulaException;

import arbol_perturbacion_visual.AEvaluableVisual;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import modelo.ArbolPerturbacion;
import modelo.EtiquetaBean;
import modelo.Examen;
import modelo.NodoPerturbacionEvaluable;

public class Correccion_Panel_Arbol extends JPanel implements ActionListener, KeyListener
{

    private final JPanel panelAbajo = new JPanel();
    private final JPanel panelAbajoIzq = new JPanel();
    private Examen examen;
    private AEvaluableVisual jtree_arbol_visual = new AEvaluableVisual();
    private ArbolPerturbacion arbol;
    private final JLabel lblDesconocido = new JLabel("Desconocido:");
    private final JTextField textFieldConocido = new JTextField();
    private final JLabel lblParcialmenteConocido = new JLabel("Parcialmente conocido:");
    private final JTextField textFieldParcialmente = new JTextField();
    private final JLabel lblConocido = new JLabel("Conocido:");
    private final JTextField textFieldDesconocido = new JTextField();
    private final JLabel lblAprendido = new JLabel("Aprendido");
    private final JTextField textFieldAprendido = new JTextField();
    private final JPanel panel = new JPanel();
    private final JPanel panel_1 = new JPanel();
    private final JPanel panel_2 = new JPanel();
    private final JPanel panel_3 = new JPanel();
    private final JButton btnAceptar = new JButton("Aceptar");
    private final JPanel panel_4 = new JPanel();
    private JButton btnMaximizar = null;
    private final JPanel panel_5 = new JPanel();
    private final JPanel panel_6 = new JPanel();
    private final JButton btnGuardar = new JButton("Guardar");
    private final JCheckBox chckbxVerNodosOcultos = new JCheckBox("Ver Nodos Ocultos");
    public static final String GUARDAR = "GUARDAR";
    public static final String MAXIMIZAR = "MAXIMIZAR";
    public static final String ACEPTAR = "ACEPTAR";
    public static final String OCULTAR = "OCULTAR";
    private final JCheckBox chckbxModificado = new JCheckBox("Modificado");
    private final JLabel lblPorcentajeCorregido = new JLabel("Porcentaje Corregido:");
    private final JPanel panel_7 = new JPanel();
    private final JPanel panel_8 = new JPanel();
    private final JPanel panel_9 = new JPanel();
    private final JProgressBar progressBar = new JProgressBar();
    private ActionListener listener;
    private NodoPerturbacionEvaluable nodo_seleccionado = null;

    /**
     * @return the examen
     */
    public Examen getExamen()
    {
        return examen;
    }

    /**
     * @param examen
     *            the examen to set
     */
    public void setExamen(Examen examen)
    {
        this.examen = examen;

        if (examen != null)
            this.setArbol(examen.getArbol_podado_particular());
        else
            this.setArbol(null);

        this.chckbxVerNodosOcultos.setEnabled(examen != null);

        if (this.btnMaximizar != null)

            this.btnMaximizar.setEnabled(examen != null);
    }

    private void habilitaDesahabilita(boolean b)
    {
        /*  this.btnAceptar.setEnabled(b);
        this.btnGuardar.setEnabled(b);
        this.btnMaximizar.setEnabled(b);
        this.chckbxVerNodosOcultos.setEnabled(b); */
        this.textFieldDesconocido.setEnabled(b);
        this.textFieldParcialmente.setEnabled(b);
        this.textFieldConocido.setEnabled(b);
        this.textFieldAprendido.setEnabled(b);
    }

    /**
     * Create the panel.
     */
    public Correccion_Panel_Arbol(ArbolPerturbacion arbol,ActionListener padre, boolean valor)
    {
        this.listener = padre;
        this.setArbol(arbol);
        if (valor)
            this.btnMaximizar = new JButton("Maximizar");
        this.iniciaGeometria();
        this.asignaListeners();

    }

    private void iniciaGeometria()
    {
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                       .addGroup(groupLayout.createSequentialGroup()
                                                                                                              .addGap(5)
                                                                                                              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                                                                                   .addComponent(this.jtree_arbol_visual,
                                                                                                                                                 GroupLayout.DEFAULT_SIZE,
                                                                                                                                                 806,
                                                                                                                                                 Short.MAX_VALUE)
                                                                                                                                   .addComponent(this.panelAbajo,
                                                                                                                                                 GroupLayout.PREFERRED_SIZE,
                                                                                                                                                 701, GroupLayout.PREFERRED_SIZE))
                                                            .addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                     .addGroup(groupLayout.createSequentialGroup()
                                                                                                            .addGap(5)
                                                                                                            .addComponent(this.jtree_arbol_visual,
                                                                                                                          GroupLayout.DEFAULT_SIZE,
                                                                                                                          215, Short.MAX_VALUE)
                                                          .addGap(5)
                                                          .addComponent(this.panelAbajo, GroupLayout.PREFERRED_SIZE,
                                                                        139, GroupLayout.PREFERRED_SIZE)
                                                          .addGap(5)));
        this.panelAbajoIzq.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        this.panelAbajoIzq.setLayout(new GridLayout(0, 4, 0, 0));

        this.panelAbajoIzq.add(this.lblDesconocido);

        this.panelAbajoIzq.add(this.panel);
        this.textFieldDesconocido.setEnabled(false);
        this.panel.add(this.textFieldDesconocido);
        this.textFieldDesconocido.setColumns(10);
        this.chckbxVerNodosOcultos.setEnabled(false);

        this.panelAbajoIzq.add(this.chckbxVerNodosOcultos);
        this.chckbxModificado.setEnabled(false);

        this.panelAbajoIzq.add(this.chckbxModificado);

        this.panelAbajoIzq.add(this.lblParcialmenteConocido);

        this.panelAbajoIzq.add(this.panel_1);
        this.textFieldParcialmente.setEnabled(false);
        this.panel_1.add(this.textFieldParcialmente);
        this.textFieldParcialmente.setColumns(10);

        this.panelAbajoIzq.add(this.panel_8);

        this.panelAbajoIzq.add(this.lblPorcentajeCorregido);

        this.panelAbajoIzq.add(this.lblConocido);

        this.panelAbajoIzq.add(this.panel_2);
        this.textFieldConocido.setEnabled(false);
        this.panel_2.add(this.textFieldConocido);
        this.textFieldConocido.setColumns(10);

        this.panelAbajoIzq.add(this.panel_5);
        if (this.btnMaximizar != null)
        {
            this.btnMaximizar.setEnabled(false);
            this.panel_5.add(this.btnMaximizar);
        }
        this.panelAbajoIzq.add(this.panel_9);
        this.progressBar.setStringPainted(true);

        this.panel_9.add(this.progressBar);

        this.panelAbajoIzq.add(this.lblAprendido);

        this.panelAbajoIzq.add(this.panel_3);
        this.textFieldAprendido.setEnabled(false);
        this.panel_3.add(this.textFieldAprendido);
        this.textFieldAprendido.setColumns(10);

        this.panelAbajoIzq.add(this.panel_7);
        this.panel_7.add(this.panel_4);
        this.panel_4.setLayout(new BorderLayout(0, 0));
        this.btnAceptar.setEnabled(false);
        this.panel_4.add(this.btnAceptar);

        this.panelAbajoIzq.add(this.panel_6);
        this.btnGuardar.setEnabled(false);

        this.panel_6.add(this.btnGuardar);
        GroupLayout gl_panelAbajo = new GroupLayout(this.panelAbajo);
        gl_panelAbajo.setHorizontalGroup(gl_panelAbajo.createParallelGroup(Alignment.LEADING)
                                         .addGroup(Alignment.TRAILING,
                                                   gl_panelAbajo.createSequentialGroup()
                                                                                    .addComponent(this.panelAbajoIzq,
                                                                                                  GroupLayout.DEFAULT_SIZE,
                                                                                                  664, Short.MAX_VALUE)
                                                                .addGap(37)));
        gl_panelAbajo.setVerticalGroup(gl_panelAbajo.createParallelGroup(Alignment.LEADING)
                                       .addGroup(gl_panelAbajo.createSequentialGroup()
                                                                                                                  .addGap(5)
                                                                                                                  .addComponent(this.panelAbajoIzq,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                129, GroupLayout.PREFERRED_SIZE)
                                                              .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                               Short.MAX_VALUE)));
        this.panelAbajo.setLayout(gl_panelAbajo);
        setLayout(groupLayout);
        this.jtree_arbol_visual.setLineasRectas(true);
        this.jtree_arbol_visual.setMuestraNodosOcultos(true);
        this.chckbxVerNodosOcultos.setSelected(true);


    }

    private void asignaListeners()
    {
        this.btnAceptar.setActionCommand(Correccion_Panel_Arbol.ACEPTAR);
        this.btnGuardar.setActionCommand(Correccion_Panel_Arbol.GUARDAR);

        this.chckbxVerNodosOcultos.setActionCommand(Correccion_Panel_Arbol.OCULTAR);
        this.btnAceptar.addActionListener(this);
        this.btnGuardar.addActionListener(this);
        this.btnGuardar.addActionListener(this.listener);

        this.chckbxVerNodosOcultos.addActionListener(this);
        this.textFieldDesconocido.addKeyListener(this);
        this.textFieldParcialmente.addKeyListener(this);
        this.textFieldConocido.addKeyListener(this);
        this.textFieldAprendido.addKeyListener(this);
        this.jtree_arbol_visual.addActionListener(this);
        if (this.btnMaximizar != null)
        {
            this.btnMaximizar.setActionCommand(Correccion_Panel_Arbol.MAXIMIZAR);
            this.btnMaximizar.addActionListener(this.listener);
        }

    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        if (arg0.getSource() == this.jtree_arbol_visual)
        {
            nodo_seleccionado = (NodoPerturbacionEvaluable) this.jtree_arbol_visual.getNodoSeleccionado();
            this.btnAceptar.setEnabled(this.nodo_seleccionado != null && this.valoresValidos());
            this.habilitaDesahabilita(false);
            if (nodo_seleccionado != null && this.jtree_arbol_visual.isEnabled())
            {

                if ((!nodo_seleccionado.tieneHijoEvaluable() && nodo_seleccionado.isEvaluado()))
                {
                    this.habilitaDesahabilita(true);
                    this.textFieldDesconocido.setText(String.valueOf(nodo_seleccionado.getDesconocido()));
                    this.textFieldParcialmente.setText(String.valueOf(nodo_seleccionado.getParcialmenteConocido()));
                    this.textFieldConocido.setText(String.valueOf(nodo_seleccionado.getConocido()));
                    this.textFieldAprendido.setText(String.valueOf(nodo_seleccionado.getAprendido()));
                } else
                {
                    this.habilitaDesahabilita(false);
                    if (nodo_seleccionado.isCero())
                    {
                        this.textFieldDesconocido.setText("");
                        this.textFieldParcialmente.setText("");
                        this.textFieldConocido.setText("");
                        this.textFieldAprendido.setText("");
                    } else
                    {
                        this.textFieldDesconocido.setText(String.valueOf(nodo_seleccionado.getDesconocido()));
                        this.textFieldParcialmente.setText(String.valueOf(nodo_seleccionado.getParcialmenteConocido()));
                        this.textFieldConocido.setText(String.valueOf(nodo_seleccionado.getConocido()));
                        this.textFieldAprendido.setText(String.valueOf(nodo_seleccionado.getAprendido()));
                    }
                }
            }
            this.btnAceptar.setEnabled(this.nodo_seleccionado != null && this.valoresValidos());
        }

        if (arg0.getActionCommand().equals(Correccion_Panel_Arbol.ACEPTAR))
            this.actualizaEtiqueta();
        if (arg0.getActionCommand().equals(Correccion_Panel_Arbol.OCULTAR))
            this.jtree_arbol_visual.setMuestraNodosOcultos(this.chckbxVerNodosOcultos.isSelected());


    }

    @Override
    public void keyPressed(KeyEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent arg0)
    {
        this.btnAceptar.setEnabled(this.nodo_seleccionado != null && this.valoresValidos());

    }

    @Override
    public void keyTyped(KeyEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    public void limpiarZona()
    {
        this.textFieldDesconocido.setText("");
        this.textFieldParcialmente.setText("");
        this.textFieldConocido.setText("");
        this.textFieldAprendido.setText("");
        this.setArbol(null);

    }

    public void setArbol(ArbolPerturbacion arbol)
    {
        this.arbol = arbol;
        if (arbol != null)
            this.jtree_arbol_visual.setModel(arbol.getTreeModel());
        else
            this.jtree_arbol_visual.setModel(null);
        this.verifica_modificado();
        this.actualiza_jtree();

    }


    public ArbolPerturbacion getArbol()
    {
        return arbol;
    }


    public void verifica_modificado()
    {
        if (this.examen != null)
        {
            this.chckbxModificado.setSelected(this.examen.isModificado());
            this.btnGuardar.setEnabled(this.examen.isModificado());
        } else
        {
            this.chckbxModificado.setSelected(false);
            this.btnGuardar.setEnabled(false);
        }
    }

    public void actualiza_jtree()
    {
        this.jtree_arbol_visual.repaint();

        if (this.examen != null && this.examen.getArbol_podado_particular() != null)
            try
            {
                int h = (int) this.examen
                                  .getArbol_podado_particular()
                                  .getPorcentajeCorreccion();
                this.progressBar.setValue(h);
            } catch (RaizNulaException e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

    }


    private boolean valoresValidos()
    {
        EtiquetaBean e = new EtiquetaBean();
        double aprendido = 0, conocido = 0, parcialmente = 0, desconocido = 0;

        try
        {
            aprendido = Double.parseDouble(this.textFieldAprendido.getText());
            conocido = Double.parseDouble(this.textFieldConocido.getText());
            desconocido = Double.parseDouble(this.textFieldDesconocido.getText());
            parcialmente = Double.parseDouble(this.textFieldParcialmente.getText());
            e.setAprendido(aprendido);
            e.setConocido(conocido);
            e.setDesconocido(desconocido);
            e.setParcialmenteConocido(parcialmente);
        } catch (Exception exception)
        {
            e.inicializar();
        }


        return e.isValid();

    }


    private void actualizaEtiqueta()
    {
        this.examen.setModificado(true);
        this.construyeEtiqueta();
        this.actualiza_jtree();


    }

    private void construyeEtiqueta()
    {
        double aprendido = 0, conocido = 0, parcialmente = 0, desconocido = 0;

        try
        {
            aprendido = Double.parseDouble(this.textFieldAprendido.getText());
            conocido = Double.parseDouble(this.textFieldConocido.getText());
            desconocido = Double.parseDouble(this.textFieldDesconocido.getText());
            parcialmente = Double.parseDouble(this.textFieldParcialmente.getText());
            this.nodo_seleccionado.setAprendido(aprendido);
            this.nodo_seleccionado.setConocido(conocido);
            this.nodo_seleccionado.setDesconocido(desconocido);
            this.nodo_seleccionado.setParcialmenteConocido(parcialmente);
        } catch (Exception e)
        {
            this.nodo_seleccionado.inicializar();
        }
        this.verifica_modificado();

    }


}
