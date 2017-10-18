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

import modelo.Instancia_Evaluacion;
import modelo.Modelo;
import modelo.Parcial;

public class PanelCombo4 extends PanelCombo3
{
    private JPanel panel_Label_Instancia;
    private JLabel label_Instancia;

    private JPanel panel_Combo_Instancia;
    private JComboBox comboBox_Instancia;
    protected DefaultComboBoxModel<Instancia_Evaluacion> comboBoxModelInstancia;
    private Instancia_Evaluacion instancia_seleccionada;
    private static final String ACCION_INSTANCIA = "ACCION_INSTANCIA";
    public static final String CAMBIOS_PANEL_COMBO4 = "CAMBIOS_PANEL_COMBO4";

    public PanelCombo4(Modelo modelo, ActionListener actionListener)
    {
        super(modelo, actionListener);
    }


    public Instancia_Evaluacion getInstancia_seleccionada()
    {
        return instancia_seleccionada;
    }

    @Override
    protected void iniciaGeometria()
    {
        // TODO Auto-generated method stub
        super.iniciaGeometria();

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


        this.comboBoxModelInstancia = new DefaultComboBoxModel<Instancia_Evaluacion>();
        this.comboBox_Instancia.setModel(this.comboBoxModelInstancia);

    }

    @Override
    protected void asignaListener()
    {
        // TODO Implement this method
        super.asignaListener();
        this.comboBox_Instancia.setActionCommand(PanelCombo4.ACCION_INSTANCIA);
        this.comboBox_Instancia.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // TODO Implement this method
        super.actionPerformed(e);
        
    
        if (e.getActionCommand().equals(PanelCombo3.ACCION_PARCIAL))
        {
            this.actualizar_combo_instancias();
        }

        this.actualizaSeleccionados();

        this.notificarListener(PanelCombo4.CAMBIOS_PANEL_COMBO4);
        
    }

    @Override
    protected void actualizar_combo_parciales()
    {
        // TODO Implement this method
        super.actualizar_combo_parciales();
        this.actualizar_combo_instancias();
    }

    protected void actualizar_combo_instancias()
    {
        this.comboBoxModelInstancia.removeAllElements();
        ArrayList<Instancia_Evaluacion> instancias;
        if (this.getParcial_seleccionado() != null)

        {
            instancias = this.getParcial_seleccionado().getInstancias_evaluaciones();
            for (int i = 0; i < instancias.size(); i++)
                this.comboBoxModelInstancia.addElement(instancias.get(i));

        }


    }

    @Override
    protected void actualizaSeleccionados()
    {
        // TODO Implement this method
        super.actualizaSeleccionados();
        this.instancia_seleccionada = (Instancia_Evaluacion) this.comboBox_Instancia.getSelectedItem();
    }

}
