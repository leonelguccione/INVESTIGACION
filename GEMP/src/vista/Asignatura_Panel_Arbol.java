package vista;

import arbol_perturbacion_visual.ANoEvaluableVisual;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modelo.ArbolPerturbacion;
import modelo.NodoPerturbacion;
import modelo.RelacionImpacto;

import util.VistaUtil;

public class Asignatura_Panel_Arbol extends JPanel implements ActionListener, KeyListener, Interface_Arbol_Asignatura
{
    private ANoEvaluableVisual jtree_arbol_visual = new ANoEvaluableVisual();
    private final JPanel panel_1 = new JPanel();
    private final JPanel panel_2 = new JPanel();
    private final JPanel panel_3 = new JPanel();
    private final JButton buttonAgregar = new JButton("Agregar");
    private final JButton buttonEliminar = new JButton("Eliminar");
    private final JPanel panel_4 = new JPanel();
    private final JPanel panel_5 = new JPanel();
    private final JTextField textFieldOrigen = new JTextField();
    private final JTextField textValor = new JTextField();
    private final JPanel panel_6 = new JPanel();
    private final JTextField textFieldDestino = new JTextField();
    private final JPanel panel_7 = new JPanel();
    private final JButton buttonNuevaRelacion = new JButton("Nueva");
    private final JPanel panel_8 = new JPanel();
    private final JButton buttonOrigen = new JButton("Origen");
    private final JPanel panel_9 = new JPanel();
    private final JButton buttonDestino = new JButton("Destino");
    private final JPanel panel_10 = new JPanel();
    private final JButton buttonCancelar = new JButton("Cancelar");
    private final JButton buttonAceptar = new JButton("Aceptar");

    private JPanel panel;
    private final JPanel panel_12 = new JPanel();
    private final JPanel panel_13 = new JPanel();
    private final JLabel label = new JLabel("Nodo:");
    private final JLabel label_1 = new JLabel("Actual:");
    private final JTextField textFieldActual = new JTextField();
    private final JTextField textFieldNodo = new JTextField();
    private Asignatura_Ventana listener;
    private boolean modoEdicion = false;
    public static final String ACEPTAR = "ACEPTAR";
    public static final String CANCELAR = "CANCELAR";
    private static final String AGREGAR_NODO = "AGREGAR_NODO";
    private static final String ELIMINAR_NODO = "ELIMINAR_NODO";
    private static final String ORIGEN = "ORIGEN";
    private static final String DESTINO = "DESTINO";
    private static final String NUEVA_RELACION = "NUEVA_RELACION";
    
    private ArbolPerturbacion arbol = null;

    private NodoPerturbacion nodo_seleccionado = null;
    private NodoPerturbacion nodo_origen = null;
    private NodoPerturbacion nodo_destino = null;
    
    /**
     * Create the panel.
     */
    public Asignatura_Panel_Arbol(Asignatura_Ventana listener)
    {
        this.listener = listener;
        this.iniciaGeometria();
        this.asignaListener();
    }

    private void iniciaGeometria()
    {
        this.jtree_arbol_visual.setLineasRectas(true);
        this.jtree_arbol_visual.setBorder(new LineBorder(new Color(0, 0, 0)));
   
        
        this.panel_10.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        buttonCancelar.setEnabled(false);

        this.panel_10.add(this.buttonCancelar);
        buttonAceptar.setEnabled(false);

        this.panel_10.add(this.buttonAceptar);
        this.panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        this.panel_4.setLayout(new GridLayout(0, 3, 0, 0));

        this.panel_4.add(this.panel_5);
        this.panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        textFieldOrigen.setEnabled(false);
        this.textFieldOrigen.setText("");
        this.textFieldOrigen.setColumns(7);

        this.panel_5.add(this.textFieldOrigen);

        this.panel_4.add(this.panel_6);
        textFieldDestino.setEnabled(false);
        this.textFieldDestino.setColumns(7);

        this.panel_6.add(this.textFieldDestino);

        this.panel_4.add(this.panel_7);

        this.textValor.setEnabled(false);
        this.textValor.setColumns(5);
        this.panel_7.add(this.textValor);
        this.panel_4.add(this.panel_8);
        buttonOrigen.setEnabled(false);

        this.panel_8.add(this.buttonOrigen);

        this.panel_4.add(this.panel_9);
        buttonDestino.setEnabled(false);

        this.panel_9.add(this.buttonDestino);
        buttonAgregar.setEnabled(false);

        this.panel_4.add(this.panel_13);
        buttonNuevaRelacion.setEnabled(false);

        this.panel_13.add(this.buttonNuevaRelacion);
        this.panel_3.add(this.buttonAgregar);
        buttonEliminar.setEnabled(false);

        this.panel_3.add(this.buttonEliminar);
        this.panel_1.setBorder(null);
        GroupLayout gl_panel_1 = new GroupLayout(this.panel_1);
        gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                      .addGroup(gl_panel_1.createSequentialGroup()
                                                                                                           .addContainerGap()
                                                                                                           .addComponent(panel_2,
                                                                                                                         GroupLayout.PREFERRED_SIZE,
                                                                                                                         186, GroupLayout.PREFERRED_SIZE)
                                                          .addPreferredGap(ComponentPlacement.RELATED)
                                                          .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                                                          .addPreferredGap(ComponentPlacement.RELATED)
                                                          .addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
                                                          .addPreferredGap(ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                                                          .addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
                                                          .addContainerGap()));
        gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                    .addGroup(gl_panel_1.createSequentialGroup()
                                                                                                         .addGap(6)
                                                                                                         .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                                                                                                             .addComponent(panel_2,
                                                                                                                                           GroupLayout.PREFERRED_SIZE,
                                                                                                                                           61,
                                                                                                                                           GroupLayout.PREFERRED_SIZE)
                                                                                                                             .addComponent(panel_4,
                                                                                                                                           GroupLayout.DEFAULT_SIZE,
                                                                                                                                           GroupLayout.DEFAULT_SIZE,
                                                                                                                                           Short.MAX_VALUE)
                                                                                                    .addComponent(panel_3,
                                                                                                                  GroupLayout.DEFAULT_SIZE,
                                                                                                                  80,
                                                                                                                  Short.MAX_VALUE)
                                                                                                    .addComponent(panel_10,
                                                                                                                  GroupLayout.PREFERRED_SIZE,
                                                                                                                  66, GroupLayout.PREFERRED_SIZE))
                                                        .addContainerGap()));

        this.panel = new JPanel();
        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                                      .addGroup(gl_panel_2.createSequentialGroup()
                                                                                                           .addContainerGap()
                                                                                                           .addComponent(this.panel,
                                                                                                                         GroupLayout.PREFERRED_SIZE,
                                                                                                                         46, GroupLayout.PREFERRED_SIZE)
                                                          .addPreferredGap(ComponentPlacement.RELATED)
                                                          .addComponent(panel_12, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                                          .addContainerGap()));
        gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
                                    .addGroup(gl_panel_2.createSequentialGroup()
                                                                                                          .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                                                           Short.MAX_VALUE)
                                                        .addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                                                                            .addComponent(panel_12,
                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                          55, GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(this.panel,
                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                          55, GroupLayout.PREFERRED_SIZE))
                                                                                                   .addContainerGap()));
        panel_12.setLayout(new GridLayout(0, 1, 0, 0));
        textFieldActual.setEnabled(false);
        textFieldActual.setEditable(false);
        textFieldActual.setColumns(10);

        panel_12.add(textFieldActual);
        textFieldNodo.setEnabled(false);
        textFieldNodo.setColumns(10);

        panel_12.add(textFieldNodo);
        this.panel.setLayout(new GridLayout(0, 1, 0, 0));

        this.panel.add(label_1);

        this.panel.add(label);
        panel_2.setLayout(gl_panel_2);
        this.panel_1.setLayout(gl_panel_1);
        this.setBorder(new TitledBorder(null, "\u00C1rbol de Dominio", TitledBorder.LEADING, TitledBorder.TOP, null,
                                        null));

        //add(this.panel);
        GroupLayout gl_panel = new GroupLayout(this);
        gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                            .addGroup(gl_panel.createSequentialGroup()
                                                              .addComponent(jtree_arbol_visual,
                                                                            GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                                                              .addGap(6))
                                            .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                                          Short.MAX_VALUE));
        gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                                  .addGroup(gl_panel.createSequentialGroup()
                                                                                                    .addComponent(jtree_arbol_visual,
                                                                                                                  GroupLayout.DEFAULT_SIZE,
                                                                                                                  429, Short.MAX_VALUE)
                                                    .addPreferredGap(ComponentPlacement.RELATED)
                                                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 81,
                                                                  GroupLayout.PREFERRED_SIZE)));
        this.setLayout(gl_panel);
        
       

    }


    public void setArbol(ArbolPerturbacion arbolPerturbacion)
    {
        this.arbol = arbolPerturbacion;
        if (arbolPerturbacion != null)
            this.jtree_arbol_visual.setModel(arbolPerturbacion.getTreeModel());
        else
            this.jtree_arbol_visual.setModel(null);

    }

    public void setModoEdicion(boolean valor)
    {
        this.buttonAceptar.setEnabled(valor);
        this.buttonCancelar.setEnabled(valor);
        this.textFieldNodo.setEnabled(valor);
        this.modoEdicion = valor;
        if (!valor)
            this.setNodo_Origen(null);
        if (!valor)
            this.setNodo_Destino(null);
        this.verificaEnabled();
    }

    private void asignaListener()
    {
        this.buttonAgregar.setActionCommand(Asignatura_Panel_Arbol.AGREGAR_NODO);
        this.buttonEliminar.setActionCommand(Asignatura_Panel_Arbol.ELIMINAR_NODO);
        this.buttonOrigen.setActionCommand(Asignatura_Panel_Arbol.ORIGEN);
        this.buttonDestino.setActionCommand(Asignatura_Panel_Arbol.DESTINO);
        this.buttonNuevaRelacion.setActionCommand(Asignatura_Panel_Arbol.NUEVA_RELACION);
        this.buttonAceptar.setActionCommand(Asignatura_Panel_Arbol.ACEPTAR);
        this.buttonCancelar.setActionCommand(Asignatura_Panel_Arbol.CANCELAR);
       this.buttonAgregar.addActionListener(this);
        this.buttonEliminar.addActionListener(this);
        this.buttonOrigen.addActionListener(this);
        this.buttonDestino.addActionListener(this);
        this.buttonNuevaRelacion.addActionListener(this);
        this.buttonAceptar.addActionListener(this.listener);
        this.buttonCancelar.addActionListener(this.listener);
        this.jtree_arbol_visual.addActionListener(this);
        this.textFieldNodo.addKeyListener(this);
        this.buttonAceptar.addActionListener(this);
        this.buttonCancelar.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.jtree_arbol_visual)
        {
            setNodoSeleccionado((NodoPerturbacion) this.jtree_arbol_visual.getNodoSeleccionado());
        }
        if (e.getActionCommand().equals(Asignatura_Panel_Arbol.ORIGEN))
        {
            if (this.nodo_seleccionado != null && this.modoEdicion)
                this.setNodo_Origen(this.nodo_seleccionado);
        }
        if (e.getActionCommand().equals(Asignatura_Panel_Arbol.DESTINO))
        {
            if (this.nodo_seleccionado != null && this.modoEdicion)
                this.setNodo_Destino(this.nodo_seleccionado);
        }
        if (e.getActionCommand().equals(Asignatura_Panel_Arbol.AGREGAR_NODO))
        {
            if (this.jtree_arbol_visual.getModel() == null)
            {
                this.nuevaRaiz();
            } else
            {
                this.nuevoNodo();
            }
        }
        if (e.getActionCommand().equals(Asignatura_Panel_Arbol.ELIMINAR_NODO))
        {


            if (this.nodo_seleccionado != null)
            {
                if (!this.nodo_seleccionado.isRoot())
                {
                    this.arbol.borrarPostOrder(this.nodo_seleccionado);
                } else
                {
                    this.setArbol(null);
                }
                this.setNodo_Origen(null);
                this.setNodo_Destino(null);
            }

        }
        if (e.getActionCommand().equals(Asignatura_Panel_Arbol.NUEVA_RELACION))

        {
            try
            {
                double valor = Double.parseDouble(textValor.getText().trim());
                if (this.nodo_origen != null && this.nodo_destino != null)
                {
                    RelacionImpacto rel = new RelacionImpacto(this.nodo_destino, valor);
                    if (this.nodo_origen.contieneRelacion(rel))
                    {
                        int res =
                            VistaUtil.dialogoSiNo("Nueva Relaci�n",
                                                  "Ya existe la relacion: " +
                                                  this.nodo_origen.getRelacionImpacto(this.nodo_destino) +
                                                  "\nDesea reemplazarla?");
                        if (res == JOptionPane.YES_OPTION)
                        {
                            this.nodo_origen.removeImpacto(rel);
                            this.nodo_origen.addImpacto(rel);
                        }


                    } else
                        this.nodo_origen.addImpacto(rel);
                    this.jtree_arbol_visual.repaint();
                }
            } catch (Exception exception)
            {
                JOptionPane.showMessageDialog(this, textValor.getText().trim() + " no es un n�mero real v�lido");
            }


        }


        if (e.getActionCommand().equals(Asignatura_Panel_Arbol.ACEPTAR) ||
            e.getActionCommand().equals(Asignatura_Panel_Arbol.CANCELAR))
        {
            this.setNodoSeleccionado(null);
            this.textFieldNodo.setText("");

        }
        this.verificaEnabled();
    }

    private void verificaEnabled()
    {
        boolean valor = this.nodo_seleccionado != null && this.modoEdicion;
        this.buttonEliminar.setEnabled(valor);
        this.textFieldNodo.setEnabled(valor || (this.modoEdicion && this.jtree_arbol_visual.getModel() == null));
        this.buttonAgregar.setEnabled(this.textFieldNodo.isEnabled() && !this.textFieldNodo
                                                                             .getText()
                                                                             .isEmpty() && this.modoEdicion);
        this.buttonOrigen.setEnabled(valor);
        this.buttonDestino.setEnabled(valor);
        this.buttonNuevaRelacion.setEnabled(this.nodo_origen != null && this.nodo_destino != null &&
                                            this.nodo_origen != this.nodo_destino);
        this.textValor.setEnabled(this.nodo_origen != null && this.nodo_destino != null &&
                                  this.nodo_origen != this.nodo_destino);
        if (this.textValor.isEnabled())
            this.textValor.setText("0.0");
        else
            this.textValor.setText("");

    }

    private void setNodo_Origen(NodoPerturbacion nodoPerturbacion)
    {
        this.nodo_origen = nodoPerturbacion;
        if (nodoPerturbacion != null)
            this.textFieldOrigen.setText(nodoPerturbacion.getIdDato());
        else
            this.textFieldOrigen.setText("");
    }

    private void setNodo_Destino(NodoPerturbacion nodoPerturbacion)
    {
        this.nodo_destino = nodoPerturbacion;
        if (nodoPerturbacion != null)
            this.textFieldDestino.setText(nodoPerturbacion.getIdDato());
        else
            this.textFieldDestino.setText("");
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        // TODO Implement this method
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        // TODO Implement this method
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        this.verificaEnabled();
    }

    private void nuevaRaiz()
    {
        this.arbol = new ArbolPerturbacion(this.listener.getNombreDominio(), "< sin descripción >");
        arbol.setRaiz(this.textFieldNodo
                          .getText()
                          .trim());
        this.jtree_arbol_visual.setModel(this.arbol.getTreeModel());
        this.jtree_arbol_visual.setNodoSeleccionado(this.arbol.getRaiz());
        this.jtree_arbol_visual.repaint();
        this.textFieldActual.setText(this.textFieldNodo
                                         .getText()
                                         .trim());
        this.textFieldNodo.setText("");


    }

    private void nuevoNodo()
    {

        this.arbol.agregarNodo(this.nodo_seleccionado, this.textFieldNodo
                                                           .getText()
                                                           .trim());
        this.jtree_arbol_visual.repaint();
        //jtree_arbol.expandPath(this.jtree_arbol.getSelectionPath());
        this.textFieldNodo.setText("");


    }

    private void setNodoSeleccionado(NodoPerturbacion nodoPerturbacion)
    {
        this.nodo_seleccionado = nodoPerturbacion;
        if (this.nodo_seleccionado != null)
            this.textFieldActual.setText(this.nodo_seleccionado.getIdDato());
        else
            this.textFieldActual.setText("");
    }

    public ArbolPerturbacion getArbol()
    {
        return arbol;
    }

    public boolean isModoEdicion()
    {
        return modoEdicion;
    }

}
