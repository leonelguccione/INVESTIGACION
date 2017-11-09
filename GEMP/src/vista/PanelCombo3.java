package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Instancia_Evaluacion;
import modelo.Modelo;
import modelo.Parcial;

public class PanelCombo3 extends PanelCombo2
{
    private JPanel panel_Label_Parcial;
    private JLabel label_Parcial;

    private JPanel panel_Combo_Parcial;
    private JComboBox comboBox_Parcial;
    protected DefaultComboBoxModel<Parcial> comboBoxModelParcial;
    

    protected static final String ACCION_PARCIAL = "ACCION_PARCIAL";
    public static final String CAMBIOS_PANEL_COMBO3 = "CAMBIOS_PANEL_COMBO3";

    public PanelCombo3(Modelo modelo, ActionListener actionListener)
    {
        super(modelo, actionListener);
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


        this.comboBoxModelParcial = new DefaultComboBoxModel<Parcial>();

        this.comboBox_Parcial.setModel(this.comboBoxModelParcial);


    }

    @Override
    protected void asignaListener()
    {
        super.asignaListener();
        this.comboBox_Parcial.setActionCommand(PanelCombo3.ACCION_PARCIAL);
        this.comboBox_Parcial.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        super.actionPerformed(e);
       

        if (e.getActionCommand().equals(PanelCombo2.ACCION_CURSADA))
        {
      
            this.actualizar_combo_parciales();
        }
        
        this.notificarListener(PanelCombo3.CAMBIOS_PANEL_COMBO3);
    }

    protected void actualizar_combo_parciales()
    {
        ArrayList<Parcial> parciales;
        this.comboBoxModelParcial.removeAllElements();
        if (this.getCursada_seleccionada() != null)
        {
            parciales = this.getCursada_seleccionada().getParciales();
            for (int i = 0; i < parciales.size(); i++)
                this.comboBoxModelParcial.addElement(parciales.get(i));
        }
    }

    public Parcial getParcial_seleccionado()
    {
        return (Parcial) this.comboBox_Parcial.getSelectedItem();
    }

    @Override
    protected void actualizar_combo_cursadas()
    {
        // TODO Implement this method
        super.actualizar_combo_cursadas();
        this.actualizar_combo_parciales();
    }


}
