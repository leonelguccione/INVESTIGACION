package UI;

import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Modelo;

public class PanelCombo3 extends PanelCombo2
{
    private JPanel panel_Label_Parcial;
    private JLabel label_Parcial;

    private JPanel panel_Combo_Parcial;
    private JComboBox comboBox_Parcial;

    public PanelCombo3(Modelo modelo, ActionListener al)
    {
        super(modelo, al);
    }

    @Override
    protected void iniciaGeometria()
    {
        // TODO Implement this method
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

        this.label_Parcial = new JLabel("Asignatura:");
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

    }

}
