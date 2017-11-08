package vista;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.Iterator;

import javax.sql.rowset.serial.SerialException;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Asignatura;
import modelo.Modelo;
import modelo.Modelo_ABM_Asignatura;

import util.VistaUtil;

public class Asignatura_Ventana extends JInternalFrame implements ActionListener, ListSelectionListener
{
    private Asignatura_Arbol_Modal ventanaModal;
    DefaultListModel listModel_asignaturas = new DefaultListModel();
    Asignatura asignatura_en_uso = null;
    private JPanel contentPane;
    private JPanel panel_Izquierda;
    private JList listAsignaturas;
    private JPanel panel_Izq_centro;
    private JPanel panel_Izq_Inf;
    private JLabel lblCdigo;
    private JTextField textFieldCodigo;
    private JLabel lblDominio_1;
    private JTextField textFieldNombre;
    private JTextField textFieldDominio;
    private JLabel lblDominio;
    private JButton btnEliminar_Asignatura;
    private JButton btnModificar;
    private JButton btnNueva;
    private JButton btnMaximizar;
    private JPanel panel_10;
    private JPanel panel_11;
    private JPanel panel_12;
    private JPanel panel_13;
    private final Asignatura_Panel_Arbol panel_derecha;
    private Modelo modelo;
    private Modelo_ABM_Asignatura modelo_abm_asignatura;
    private static final String ELIMINAR = "ELIMINAR";
    private static final String MAXIMIZAR = "MAXIMIZAR";
    private static final String NUEVA = "NUEVA";
    private static final String MODIFICAR = "MODIFICAR";
    private boolean modificar;
    private Interface_Arbol_Asignatura arbolVisual;

    /**
     * Launch the application.
     */

    public Asignatura_Ventana(Modelo modelo)
    {
        this.panel_derecha = new Asignatura_Panel_Arbol(this);
        this.arbolVisual = this.panel_derecha;
        this.iniciaGeometria();
        this.modelo = modelo;
        this.modelo_abm_asignatura = modelo.getModelo_abm_asignatura();
        this.listAsignaturas.setModel(listModel_asignaturas);
        this.asignatura_en_uso = null;
        listModel_asignaturas.clear();
        this.cargar_jList_asignaturas();
        this.arbolVisual.setArbol(null);
        this.asignaListeners();
    }

    private void cargar_jList_asignaturas()
    {
        try
        {
            this.modelo.recupera_sistema();
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        this.listModel_asignaturas.clear();
        Iterator iterator_asignaturas = this.modelo
                                            .getAsignaturas()
                                            .values()
                                            .iterator();
        while (iterator_asignaturas.hasNext())
        {
            Asignatura asignatura_ste = (Asignatura) iterator_asignaturas.next();
            this.listModel_asignaturas.addElement(asignatura_ste);
        }
    }

    private void iniciaGeometria()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1068, 796);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(this.contentPane);

        this.panel_Izquierda = new JPanel();
        this.panel_Izquierda.setBorder(null);
        GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                          .addGroup(gl_contentPane.createSequentialGroup()
                                                                                                                       .addComponent(this.panel_Izquierda,
                                                                                                                                     GroupLayout.PREFERRED_SIZE,
                                                                                                                                     222, GroupLayout.PREFERRED_SIZE)
                                                                  .addPreferredGap(ComponentPlacement.RELATED)
                                                                  .addComponent(this.panel_derecha,
                                                                                GroupLayout.DEFAULT_SIZE, 646,
                                                                                Short.MAX_VALUE)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                                                                      .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                                                                                              .addComponent(this.panel_derecha,
                                                                                                                                                            Alignment.LEADING,
                                                                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                                                                            636,
                                                                                                                                                            Short.MAX_VALUE)
                                                                                                                                              .addComponent(this.panel_Izquierda,
                                                                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                                                                            636, Short.MAX_VALUE))
                                                                .addGap(3)));

        this.listAsignaturas = new JList();
        this.listAsignaturas.setBorder(new TitledBorder(null, "Listado de Asignaturas", TitledBorder.LEADING,
                                                        TitledBorder.TOP, null, null));

        this.panel_Izq_centro = new JPanel();

        this.panel_Izq_Inf = new JPanel();
        GroupLayout gl_panel_Izquierda = new GroupLayout(this.panel_Izquierda);
        gl_panel_Izquierda.setHorizontalGroup(gl_panel_Izquierda.createParallelGroup(Alignment.TRAILING)
                                              .addGroup(gl_panel_Izquierda.createSequentialGroup()
                                                                                                                                    .addContainerGap()
                                                                                                                                    .addGroup(gl_panel_Izquierda.createParallelGroup(Alignment.TRAILING)
                                                                                                                                                                .addComponent(this.listAsignaturas,
                                                                                                                                                                              Alignment.LEADING,
                                                                                                                                                                              GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                              212,
                                                                                                                                                                              Short.MAX_VALUE)
                                                                                                                                                                .addComponent(this.panel_Izq_Inf,
                                                                                                                                                                              Alignment.LEADING,
                                                                                                                                                                              GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                              212,
                                                                                                                                                                              Short.MAX_VALUE)
                                                                                                                                      .addComponent(this.panel_Izq_centro,
                                                                                                                                                    Alignment.LEADING,
                                                                                                                                                    GroupLayout.PREFERRED_SIZE,
                                                                                                                                                    212, Short.MAX_VALUE))
                                                                          .addGap(4)));
        gl_panel_Izquierda.setVerticalGroup(gl_panel_Izquierda.createParallelGroup(Alignment.TRAILING)
                                            .addGroup(gl_panel_Izquierda.createSequentialGroup()
                                                                                                                                  .addContainerGap()
                                                                                                                                  .addComponent(this.listAsignaturas,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                558, Short.MAX_VALUE)
                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        .addComponent(this.panel_Izq_centro,
                                                                                      GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        .addComponent(this.panel_Izq_Inf,
                                                                                      GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                                                        .addContainerGap()));
        this.panel_Izq_centro.setLayout(new GridLayout(0, 2, 0, 0));

        this.panel_10 = new JPanel();
        this.panel_Izq_centro.add(this.panel_10);

        this.btnEliminar_Asignatura = new JButton("Eliminar");
        this.panel_10.add(this.btnEliminar_Asignatura);

        this.panel_11 = new JPanel();
        this.panel_Izq_centro.add(this.panel_11);

        this.btnModificar = new JButton("Modificar");
        this.panel_11.add(this.btnModificar);

        this.panel_12 = new JPanel();
        this.panel_Izq_centro.add(this.panel_12);

        this.btnNueva = new JButton("Nueva ");
        this.panel_12.add(this.btnNueva);

        this.panel_13 = new JPanel();
        this.panel_Izq_centro.add(this.panel_13);

        this.btnMaximizar = new JButton("Maximizar");
        this.panel_13.add(this.btnMaximizar);
        GridBagLayout gbl_panel_Izq_Inf = new GridBagLayout();
        gbl_panel_Izq_Inf.columnWeights = new double[]
        {
            0.0, 1.0
        };
        gbl_panel_Izq_Inf.rowWeights = new double[]
        {
            0.0, 0.0, 0.0
        };
        this.panel_Izq_Inf.setLayout(gbl_panel_Izq_Inf);

        this.lblCdigo = new JLabel("Código");
        GridBagConstraints gbc_lblCdigo = new GridBagConstraints();
        gbc_lblCdigo.anchor = GridBagConstraints.EAST;
        gbc_lblCdigo.insets = new Insets(0, 0, 5, 5);
        gbc_lblCdigo.gridx = 0;
        gbc_lblCdigo.gridy = 0;
        this.panel_Izq_Inf.add(this.lblCdigo, gbc_lblCdigo);

        this.textFieldCodigo = new JTextField();
        GridBagConstraints gbc_textFieldCodigo = new GridBagConstraints();
        gbc_textFieldCodigo.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldCodigo.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldCodigo.gridx = 1;
        gbc_textFieldCodigo.gridy = 0;
        this.panel_Izq_Inf.add(this.textFieldCodigo, gbc_textFieldCodigo);
        this.textFieldCodigo.setColumns(10);

        this.lblDominio_1 = new JLabel("Nombre:");
        GridBagConstraints gbc_lblDominio_1 = new GridBagConstraints();
        gbc_lblDominio_1.anchor = GridBagConstraints.EAST;
        gbc_lblDominio_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblDominio_1.gridx = 0;
        gbc_lblDominio_1.gridy = 1;
        this.panel_Izq_Inf.add(this.lblDominio_1, gbc_lblDominio_1);

        this.textFieldNombre = new JTextField();
        this.textFieldNombre.setColumns(10);
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldNombre.gridx = 1;
        gbc_textFieldNombre.gridy = 1;
        this.panel_Izq_Inf.add(this.textFieldNombre, gbc_textFieldNombre);

        this.lblDominio = new JLabel("Descripción");
        GridBagConstraints gbc_lblDominio = new GridBagConstraints();
        gbc_lblDominio.insets = new Insets(0, 0, 0, 5);
        gbc_lblDominio.anchor = GridBagConstraints.EAST;
        gbc_lblDominio.gridx = 0;
        gbc_lblDominio.gridy = 2;
        this.panel_Izq_Inf.add(this.lblDominio, gbc_lblDominio);

        this.textFieldDominio = new JTextField();
        this.textFieldDominio.setColumns(10);
        GridBagConstraints gbc_textFieldDominio = new GridBagConstraints();
        gbc_textFieldDominio.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldDominio.gridx = 1;
        gbc_textFieldDominio.gridy = 2;
        this.panel_Izq_Inf.add(this.textFieldDominio, gbc_textFieldDominio);
        this.panel_Izquierda.setLayout(gl_panel_Izquierda);
        this.contentPane.setLayout(gl_contentPane);
        this.textFieldCodigo.setEnabled(false);
        this.textFieldNombre.setEnabled(false);
        this.textFieldDominio.setEnabled(false);
        this.habilitaModificarEliminar(false);
        this.listAsignaturas.setEnabled(true);

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals(Asignatura_Ventana.ELIMINAR))
        {
            int res =
                VistaUtil.dialogoSiNo("Eliminar Asignatura",
                                      "Seguro desea eliminar la Asignatura " + this.asignatura_en_uso.getNombre() +
                                      " ?\nLa operación no se podrá revertir");
            if (res == JOptionPane.YES_OPTION)
                this.eliminarAsignatura();
        }
        if (e.getActionCommand().equals(Asignatura_Ventana.MODIFICAR))
            this.modificarAsignatura();
        if (e.getActionCommand().equals(Asignatura_Ventana.NUEVA))
            this.nuevaAsignatura();
        if (e.getActionCommand().equals(Asignatura_Panel_Arbol.ACEPTAR))
            this.aceptar();
        if (e.getActionCommand().equals(Asignatura_Panel_Arbol.CANCELAR))
        {
            this.modoEdicion(false);
            this.btnNueva.setEnabled(true);
            this.actualizaAsignatura();

        }

        if (e.getActionCommand().equals(Asignatura_Ventana.MAXIMIZAR))
        {

            this.ventanaModal = new Asignatura_Arbol_Modal("", this.panel_derecha.getArbol(), this);
            this.ventanaModal.setModoEdicion(this.panel_derecha.isModoEdicion());
            this.setVisible(false);
            this.arbolVisual = this.ventanaModal;
            this.ventanaModal.addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowDeactivated(WindowEvent arg0)
                {

                    if (Asignatura_Ventana.this.ventanaModal != null)
                    {
                        Asignatura_Ventana.this.ventanaModal.requestFocus();
                        Asignatura_Ventana.this.ventanaModal.toFront();
                    }

                }

                @Override
                public void windowClosing(WindowEvent e)
                {
                    // TODO Implement this method
                    Asignatura_Ventana.this.panel_derecha.setArbol(Asignatura_Ventana.this.ventanaModal.getArbol());
                    Asignatura_Ventana.this
                        .panel_derecha.setModoEdicion(Asignatura_Ventana.this.ventanaModal.isModoEdicion());

                    Asignatura_Ventana.this.arbolVisual = Asignatura_Ventana.this.panel_derecha;
                    Asignatura_Ventana.this.panel_derecha.setVisible(true);
                    Asignatura_Ventana.this.setVisible(true);

                    Asignatura_Ventana.this.ventanaModal = null;
                }
            });
            this.panel_derecha.setVisible(false);

        }


    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        this.asignatura_en_uso = (Asignatura) this.listAsignaturas.getSelectedValue();


        this.actualizaAsignatura();
    }

    private void asignaListeners()
    {
        this.listAsignaturas.addListSelectionListener(this);
        this.btnEliminar_Asignatura.addActionListener(this);
        this.btnModificar.addActionListener(this);
        this.btnNueva.addActionListener(this);
        this.btnMaximizar.addActionListener(this);

        this.btnEliminar_Asignatura.setActionCommand(Asignatura_Ventana.ELIMINAR);
        this.btnModificar.setActionCommand(Asignatura_Ventana.MODIFICAR);
        this.btnNueva.setActionCommand(Asignatura_Ventana.NUEVA);
        this.btnMaximizar.setActionCommand(Asignatura_Ventana.MAXIMIZAR);


    }

    private void habilitaModificarEliminar(boolean valor)
    {

        this.btnEliminar_Asignatura.setEnabled(valor);
        this.btnModificar.setEnabled(valor);
        this.listAsignaturas.setEnabled(valor);


    }

    private void eliminarAsignatura()
    {
        try
        {
            this.modelo_abm_asignatura.borrar_asignatura(this.asignatura_en_uso);
            JOptionPane.showMessageDialog(this, "Asignatura eliminada");
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        this.modelo
            .getAsignaturas()
            .remove(this.asignatura_en_uso.getCodigo());
        this.cargar_jList_asignaturas();

        this.asignatura_en_uso = null;
        this.habilitaModificarEliminar(false);
        this.actualizaAsignatura();

    }

    private void actualizaAsignatura()
    {
        this.habilitaModificarEliminar(this.asignatura_en_uso != null);
        if (this.asignatura_en_uso != null)
        {
            this.arbolVisual.setArbol(this.asignatura_en_uso.getArbol_dominio());


            this.textFieldCodigo.setText(this.asignatura_en_uso.getCodigo());
            this.textFieldNombre.setText(this.asignatura_en_uso.getNombre());
            if (this.asignatura_en_uso.getArbol_dominio() != null)
                this.textFieldDominio.setText(this.asignatura_en_uso
                                                  .getArbol_dominio()
                                                  .getNombre());
            else
                this.textFieldDominio.setText("");
        } else
        {
            this.arbolVisual.setArbol(null);
            this.textFieldCodigo.setText("");
            this.textFieldNombre.setText("");
            this.textFieldDominio.setText("");
        }
    }

    private void modificarAsignatura()
    {
        this.modoEdicion(true);
        if (this.asignatura_en_uso != null)
        {
            this.modificar = true;
            this.textFieldDominio.setEnabled(true);
            this.textFieldNombre.setEnabled(true);
            this.arbolVisual.setModoEdicion(true);
            if (this.asignatura_en_uso.getArbol_dominio() != null)
            {
                this.arbolVisual.setArbol(this.asignatura_en_uso
                                              .getArbol_dominio()
                                              .clone());

            }

        }
        this.habilitaModificarEliminar(false);
        this.btnNueva.setEnabled(false);

    }

    private void aceptar()
    {
        if (!this.modificar)
            this.asignatura_en_uso = new Asignatura();


        this.asignatura_en_uso.setNombre(this.textFieldNombre.getText());
        if (this.arbolVisual.getArbol() != null)
            this.arbolVisual
                .getArbol()
                .setNombre(this.textFieldDominio.getText());
        this.asignatura_en_uso.setArbol_dominio(this.arbolVisual.getArbol());


        if (this.modificar)
        {
            try
            {
                this.modelo_abm_asignatura.actualizar_Asignatura(this.asignatura_en_uso);
                JOptionPane.showMessageDialog(this, "Asignatura actualizada");
            } catch (SerialException e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (SQLException e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            this.modificar = false;
        } else
        {
            this.asignatura_en_uso.setCodigo(this.textFieldCodigo
                                                 .getText()
                                                 .trim());
            try
            {
                this.modelo_abm_asignatura.almacenar_asignatura(this.asignatura_en_uso);
                JOptionPane.showMessageDialog(this, "Asignatura almacenada");
            } catch (SerialException e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (SQLException e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            

        }


        this.cargar_jList_asignaturas();
        this.modoEdicion(false);
        this.btnNueva.setEnabled(true);

    }

    private void modoEdicion(boolean b)
    {
        this.textFieldDominio.setEnabled(b);
        this.textFieldNombre.setEnabled(b);


        this.arbolVisual.setModoEdicion(b);
        this.listAsignaturas.setEnabled(!b);
    }

    public String getNombreDominio()
    {
        return this.textFieldDominio.getText();
    }

    private void nuevaAsignatura()
    {
        this.modoEdicion(true);
        this.asignatura_en_uso = null;

        this.modificar = false;
        this.textFieldDominio.setEnabled(true);
        this.textFieldNombre.setEnabled(true);
        this.textFieldCodigo.setEnabled(true);
        this.arbolVisual.setModoEdicion(true);
        this.textFieldCodigo.setEnabled(true);
        this.arbolVisual.setArbol(null);
        this.textFieldDominio.setText("");
        this.textFieldNombre.setText("");
        this.textFieldCodigo.setText("");
        this.textFieldCodigo.requestFocus();

        this.habilitaModificarEliminar(false);
        this.btnNueva.setEnabled(false);
    }
}
