package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Asignatura;
import modelo.Cursada;
import modelo.Instancia_Evaluacion;
import modelo.Modelo;
import modelo.Parcial;

public class PanelCombo33 extends PanelCombo2
{
    private JPanel panel_Label_Parcial;
    private JLabel label_Parcial;

    private JPanel panel_Combo_Parcial;
    private JComboBox comboBox_Parcial;

    private JPanel panel_Label_Instancia;
    private JLabel label_Instancia;

    private JPanel panel_Combo_Instancia;
    private JComboBox comboBox_Instancia;

    protected DefaultComboBoxModel<Parcial> comboBoxModelParcial;
    protected DefaultComboBoxModel<Instancia_Evaluacion> comboBoxModelInstancia;
    private Parcial parcial_seleccionado;
    private Instancia_Evaluacion instancia_seleccionada;


    private static final String ACCION_PARCIAL = "ACCION_PARCIAL";
    private static final String ACCION_INSTANCIA = "ACCION_INSTANCIA";
    private static int ccc = 0;

    public PanelCombo33(Modelo modelo, ActionListener al)
    {
        super(modelo, al);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void iniciaGeometria()
    {
        // TODO Auto-generated method stub
        super.iniciaGeometria();
        this.panel_Combo_Parcial = new JPanel();
        this.panel_derecha.add(this.panel_Combo_Parcial);

        this.comboBox_Parcial = new JComboBox();
        GroupLayout gl_panel_Combo_Parcial = new GroupLayout(this.panel_Combo_Parcial);
        gl_panel_Combo_Parcial.setHorizontalGroup(gl_panel_Combo_Parcial.createParallelGroup(Alignment.LEADING)
                                                  .addGroup(gl_panel_Combo_Parcial.createSequentialGroup()
                                                                                                                                               .addContainerGap()
                                                                                                                                               .addComponent(this.comboBox_Parcial,
                                                                                                                                                             0,
                                                                                                                                                             299, Short.MAX_VALUE)
                                                                                  .addContainerGap()));
        gl_panel_Combo_Parcial.setVerticalGroup(gl_panel_Combo_Parcial.createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_panel_Combo_Parcial.createSequentialGroup()
                                                                                                                                             .addGap(5)
                                                                                                                                             .addComponent(this.comboBox_Parcial,
                                                                                                                                                           GroupLayout.PREFERRED_SIZE,
                                                                                                                                                           GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addContainerGap(50, Short.MAX_VALUE)));
        this.panel_Combo_Parcial.setLayout(gl_panel_Combo_Parcial);
        this.panel_Label_Parcial = new JPanel();
        this.panel_izquierda.add(this.panel_Label_Parcial);

        this.label_Parcial = new JLabel("Parcial:");
        GroupLayout gl_panel_Label_Parcial = new GroupLayout(this.panel_Label_Parcial);
        gl_panel_Label_Parcial.setHorizontalGroup(gl_panel_Label_Parcial.createParallelGroup(Alignment.LEADING)
                                                  .addGroup(gl_panel_Label_Parcial.createSequentialGroup()
                                                                                                                                               .addContainerGap()
                                                                                                                                               .addComponent(this.label_Parcial)
                                                                                                                                               .addContainerGap(65,
                                                                                                                                                                Short.MAX_VALUE)));
        gl_panel_Label_Parcial.setVerticalGroup(gl_panel_Label_Parcial.createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_panel_Label_Parcial.createSequentialGroup()
                                                                                                                                             .addContainerGap()
                                                                                                                                             .addComponent(this.label_Parcial)
                                                                                                                                             .addContainerGap(52,
                                                                                                                                                              Short.MAX_VALUE)));
        this.panel_Label_Parcial.setLayout(gl_panel_Label_Parcial);


        this.panel_Combo_Instancia = new JPanel();
        this.panel_derecha.add(this.panel_Combo_Instancia);

        this.comboBox_Instancia = new JComboBox();
     
        GroupLayout gl_panel_Combo_Instancia = new GroupLayout(this.panel_Combo_Instancia);
        gl_panel_Combo_Instancia.setHorizontalGroup(gl_panel_Combo_Instancia.createParallelGroup(Alignment.LEADING)
                                                    .addGroup(gl_panel_Combo_Instancia.createSequentialGroup()
                                                                                                                                                     .addContainerGap()
                                                                                                                                                     .addComponent(this.comboBox_Instancia,
                                                                                                                                                                   0,
                                                                                                                                                                   299, Short.MAX_VALUE)
                                                                                      .addContainerGap()));
        gl_panel_Combo_Instancia.setVerticalGroup(gl_panel_Combo_Instancia.createParallelGroup(Alignment.LEADING)
                                                  .addGroup(gl_panel_Combo_Instancia.createSequentialGroup()
                                                                                                                                                   .addGap(5)
                                                                                                                                                   .addComponent(this.comboBox_Instancia,
                                                                                                                                                                 GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                 GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                    .addContainerGap(50,
                                                                                                     Short.MAX_VALUE)));
        this.panel_Combo_Instancia.setLayout(gl_panel_Combo_Instancia);

        this.panel_Label_Instancia = new JPanel();
        this.panel_izquierda.add(this.panel_Label_Instancia);

        this.label_Instancia = new JLabel("Inst. Ev:");
        GroupLayout gl_panel_Label_Instancia = new GroupLayout(this.panel_Label_Instancia);
        gl_panel_Label_Instancia.setHorizontalGroup(gl_panel_Label_Instancia.createParallelGroup(Alignment.LEADING)
                                                    .addGroup(gl_panel_Label_Instancia.createSequentialGroup()
                                                                                                                                                     .addContainerGap()
                                                                                                                                                     .addComponent(this.label_Instancia)
                                                                                                                                                     .addContainerGap(65,
                                                                                                                                                                      Short.MAX_VALUE)));
        gl_panel_Label_Instancia.setVerticalGroup(gl_panel_Label_Instancia.createParallelGroup(Alignment.LEADING)
                                                  .addGroup(gl_panel_Label_Instancia.createSequentialGroup()
                                                                                                                                                   .addContainerGap()
                                                                                                                                                   .addComponent(this.label_Instancia)
                                                                                                                                                   .addContainerGap(52,
                                                                                                                                                                    Short.MAX_VALUE)));
        this.panel_Label_Instancia.setLayout(gl_panel_Label_Instancia);


        this.comboBoxModelParcial = new DefaultComboBoxModel<Parcial>();
        this.comboBoxModelInstancia = new DefaultComboBoxModel<Instancia_Evaluacion>();
        this.comboBox_Parcial.setModel(this.comboBoxModelParcial);
        this.comboBox_Instancia.setModel(this.comboBoxModelInstancia);

    }


    @Override
    protected void asignaListener()
    {
        // TODO Implement this method

        super.asignaListener();
        this.comboBox_Parcial.setActionCommand(PanelCombo33.ACCION_PARCIAL);
        this.comboBox_Parcial.addActionListener(this);
        this.comboBox_Instancia.setActionCommand(PanelCombo33.ACCION_INSTANCIA);
        this.comboBox_Instancia.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        // TODO Implement this method
        super.actionPerformed(e);
        ccc++;
        System.out.println(ccc);
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals(PanelCombo2.ACCION_CURSADA))

          
            {
                this.parcial_seleccionado = null;
                this.actualizar_combo_parciales();
            }

        if (e.getActionCommand().equals(PanelCombo33.ACCION_PARCIAL))
        {
            if (this.comboBox_Parcial.getSelectedItem() != null)
            {
                this.parcial_seleccionado = (Parcial) this.comboBox_Parcial.getSelectedItem();
                this.instancia_seleccionada = null;
                this.actualizar_combo_instancias();

            }

        }
        if (e.getActionCommand().equals(PanelCombo33.ACCION_INSTANCIA))
        {
            if (this.comboBox_Instancia.getSelectedItem() != null)
            {
                this.instancia_seleccionada = (Instancia_Evaluacion) this.comboBox_Instancia.getSelectedItem();
            }
        }


    }

    private void actualizar_combo_parciales()
    {

        ArrayList<Parcial> parciales;
        System.out.println("Cursada: " + this.getCursada_seleccionada());
        if (this.getCursada_seleccionada() != null)

        {
            this.comboBoxModelParcial.removeAllElements();
            parciales = this.getCursada_seleccionada().getParciales();
            for (int i = 0; i < parciales.size(); i++)
            {
                this.comboBoxModelParcial.addElement(parciales.get(i));
                System.out.println(parciales.get(i));
            }
        }
    }

    private void actualizar_combo_instancias()
    {
        this.comboBoxModelInstancia.removeAllElements();
        ArrayList<Instancia_Evaluacion> instancias;
        if (this.parcial_seleccionado != null)

        {

            instancias = this.parcial_seleccionado.getInstancias_evaluaciones();
            for (int i = 0; i < instancias.size(); i++)
                this.comboBoxModelInstancia.addElement(instancias.get(i));

        }


    }


}
