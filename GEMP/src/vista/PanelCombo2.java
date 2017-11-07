package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import modelo.Asignatura;
import modelo.Cursada;
import modelo.Modelo;

public class PanelCombo2 extends JPanel implements ActionListener
{
    protected JPanel panel_izquierda;
    protected JPanel panel_derecha;


    private JPanel panel_Label_Asignatura;
    private JLabel label_Asignatura;
    private JPanel panel_Label_Cursada;
    private JLabel label_Cursada;

    private JPanel panel_Combo_Asignatura;
    private JComboBox comboBox_Asignatura;
    private JPanel panel_Combo_Cursada;
    protected JComboBox comboBox_Cursada;
    protected Modelo modelo;

    protected DefaultComboBoxModel<Asignatura> comboBoxModelAsignatura = new DefaultComboBoxModel<Asignatura>();
    protected DefaultComboBoxModel<Cursada> comboBoxModelCursada = new DefaultComboBoxModel<Cursada>();

  


    protected static final String ACCION_ASIGNATURA = "ACCION_ASIGNATURA";
    protected static final String ACCION_CURSADA = "ACCION_CURSADA";
    public static final String CAMBIOS_PANEL_COMBO2 = "CAMBIOS_PANEL_COMBO2";


    protected ActionListener actionLister;


    /**
     * Create the panel.
     */
    public PanelCombo2(Modelo modelo, ActionListener al)
    {


        this.modelo = modelo;
        this.actionLister = al;
        this.iniciaGeometria();
        this.asignaListener();

        this.actualizar_combo_asignatura();

    }


    private void actualizar_combo_asignatura()
    {

        this.comboBoxModelAsignatura.removeAllElements();
        Iterator iterator_asignaturas = modelo.getAsignaturas()
                                              .values()
                                              .iterator();
        //Recorrer el contenido del Iterator
        while (iterator_asignaturas.hasNext())
        {
            Asignatura asignatura = (Asignatura) iterator_asignaturas.next();
            this.comboBoxModelAsignatura.addElement(asignatura);
        }

    }


    protected void iniciaGeometria()
    {
        this.panel_izquierda = new JPanel();

        this.panel_derecha = new JPanel();
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                       .addGroup(groupLayout.createSequentialGroup()
                                                                                                              .addContainerGap()
                                                                                                              .addComponent(this.panel_izquierda,
                                                                                                                            GroupLayout.PREFERRED_SIZE,
                                                                                                                            58, GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(ComponentPlacement.RELATED)
                                                            .addComponent(this.panel_derecha, GroupLayout.DEFAULT_SIZE,
                                                                          164, Short.MAX_VALUE)
                                                            .addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                     .addGroup(groupLayout.createSequentialGroup()
                                                                                                             .addContainerGap()
                                                                                                             .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                                                                                                  .addComponent(this.panel_derecha,
                                                                                                                                                Alignment.LEADING,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                278,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                  .addComponent(this.panel_izquierda,
                                                                                                                                                Alignment.LEADING,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                278, Short.MAX_VALUE))
                                                          .addContainerGap()));
        this.panel_derecha.setLayout(new GridLayout(0, 1, 0, 0));

        this.panel_Combo_Asignatura = new JPanel();
        this.panel_derecha.add(this.panel_Combo_Asignatura);

        this.comboBox_Asignatura = new JComboBox();
        GroupLayout gl_panel_Combo_Asignatura = new GroupLayout(this.panel_Combo_Asignatura);
        gl_panel_Combo_Asignatura.setHorizontalGroup(gl_panel_Combo_Asignatura.createParallelGroup(Alignment.LEADING)
                                                     .addGroup(gl_panel_Combo_Asignatura.createSequentialGroup()
                                                                                                                                                        .addContainerGap()
                                                                                                                                                        .addComponent(this.comboBox_Asignatura,
                                                                                                                                                                      0,
                                                                                                                                                                      299, Short.MAX_VALUE)
                                                                                        .addContainerGap()));
        gl_panel_Combo_Asignatura.setVerticalGroup(gl_panel_Combo_Asignatura.createParallelGroup(Alignment.LEADING)
                                                   .addGroup(gl_panel_Combo_Asignatura.createSequentialGroup()
                                                                                                                                                      .addGap(5)
                                                                                                                                                      .addComponent(this.comboBox_Asignatura,
                                                                                                                                                                    GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                      .addContainerGap(50,
                                                                                                       Short.MAX_VALUE)));
        this.panel_Combo_Asignatura.setLayout(gl_panel_Combo_Asignatura);

        this.panel_Combo_Cursada = new JPanel();
        this.panel_derecha.add(this.panel_Combo_Cursada);

        this.comboBox_Cursada = new JComboBox();
        GroupLayout gl_panel_Combo_Cursada = new GroupLayout(this.panel_Combo_Cursada);
        gl_panel_Combo_Cursada.setHorizontalGroup(gl_panel_Combo_Cursada.createParallelGroup(Alignment.LEADING)
                                                  .addGroup(gl_panel_Combo_Cursada.createSequentialGroup()
                                                                                                                                               .addContainerGap()
                                                                                                                                               .addComponent(this.comboBox_Cursada,
                                                                                                                                                             0,
                                                                                                                                                             299, Short.MAX_VALUE)
                                                                                  .addContainerGap()));
        gl_panel_Combo_Cursada.setVerticalGroup(gl_panel_Combo_Cursada.createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_panel_Combo_Cursada.createSequentialGroup()
                                                                                                                                             .addGap(5)
                                                                                                                                             .addComponent(this.comboBox_Cursada,
                                                                                                                                                           GroupLayout.PREFERRED_SIZE,
                                                                                                                                                           GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addContainerGap(50, Short.MAX_VALUE)));
        this.panel_Combo_Cursada.setLayout(gl_panel_Combo_Cursada);


        this.panel_izquierda.setLayout(new GridLayout(0, 1, 0, 0));

        this.panel_Label_Asignatura = new JPanel();
        this.panel_izquierda.add(this.panel_Label_Asignatura);

        this.label_Asignatura = new JLabel("Asignatura:");
        GroupLayout gl_panel_Label_Asignatura = new GroupLayout(this.panel_Label_Asignatura);
        gl_panel_Label_Asignatura.setHorizontalGroup(gl_panel_Label_Asignatura.createParallelGroup(Alignment.LEADING)
                                                     .addGroup(gl_panel_Label_Asignatura.createSequentialGroup()
                                                                                                                                                        .addComponent(this.label_Asignatura)
                                                                                                                                                        .addContainerGap(31,
                                                                                                                                                                         Short.MAX_VALUE)));
        gl_panel_Label_Asignatura.setVerticalGroup(gl_panel_Label_Asignatura.createParallelGroup(Alignment.LEADING)
                                                   .addGroup(gl_panel_Label_Asignatura.createSequentialGroup()
                                                                                                                                                      .addContainerGap()
                                                                                                                                                      .addComponent(this.label_Asignatura)
                                                                                                                                                      .addContainerGap(113,
                                                                                                                                                                       Short.MAX_VALUE)));
        this.panel_Label_Asignatura.setLayout(gl_panel_Label_Asignatura);


        this.panel_Label_Cursada = new JPanel();
        this.panel_izquierda.add(this.panel_Label_Cursada);

        this.label_Cursada = new JLabel("Cursada:");
        GroupLayout gl_panel_Label_Cursada = new GroupLayout(this.panel_Label_Cursada);
        gl_panel_Label_Cursada.setHorizontalGroup(gl_panel_Label_Cursada.createParallelGroup(Alignment.LEADING)
                                                  .addGroup(gl_panel_Label_Cursada.createSequentialGroup()
                                                                                                                                               .addComponent(this.label_Cursada)
                                                                                                                                               .addContainerGap(23,
                                                                                                                                                                Short.MAX_VALUE)));
        gl_panel_Label_Cursada.setVerticalGroup(gl_panel_Label_Cursada.createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_panel_Label_Cursada.createSequentialGroup()
                                                                                                                                             .addContainerGap()
                                                                                                                                             .addComponent(this.label_Cursada)
                                                                                                                                             .addContainerGap(113,
                                                                                                                                                              Short.MAX_VALUE)));
        this.panel_Label_Cursada.setLayout(gl_panel_Label_Cursada);


        setLayout(groupLayout);
        this.comboBox_Asignatura.setModel(this.comboBoxModelAsignatura);
        this.comboBox_Cursada.setModel(comboBoxModelCursada);


    }


    protected void actualizar_combo_cursadas()
    {

        this.comboBoxModelCursada.removeAllElements();
        ArrayList<Cursada> cursadas;
        if (this.getAsignatura_seleccionada() != null)

        {

            cursadas = this.getAsignatura_seleccionada().getCursadas();
            for (int i = 0; i < cursadas.size(); i++)
                this.comboBoxModelCursada.addElement(cursadas.get(i));

        }
    }

    protected void asignaListener()
    {
        this.comboBox_Asignatura.setActionCommand(PanelCombo2.ACCION_ASIGNATURA);
        this.comboBox_Asignatura.addActionListener(this);
        this.comboBox_Cursada.setActionCommand(PanelCombo2.ACCION_CURSADA);
        this.comboBox_Cursada.addActionListener(this);
    }

    protected void notificarListener(String s)
    {

        this.actionLister.actionPerformed(new ActionEvent(this, 0,s));
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals(PanelCombo2.ACCION_ASIGNATURA))
        
            this.actualizar_combo_cursadas();
      
        
        this.notificarListener(PanelCombo2.CAMBIOS_PANEL_COMBO2);
    }


    public Cursada getCursada_seleccionada()
    {
        return (Cursada) this.comboBox_Cursada.getSelectedItem();
           
    }

    public Asignatura getAsignatura_seleccionada()
    {
        return (Asignatura) this.comboBox_Asignatura.getSelectedItem();
        
    }


    @Override
    public void setEnabled(boolean valor)
    {
        super.setEnabled(valor);

        this.comboBox_Asignatura.setEnabled(valor);
        this.comboBox_Cursada.setEnabled(valor);
        this.label_Asignatura.setEnabled(valor);
        this.label_Cursada.setEnabled(valor);
    }
    
}
