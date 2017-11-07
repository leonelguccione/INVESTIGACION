package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;


public class Parcial_Panel_Datos extends JPanel
{
    private JPanel izquierda;
    private JButton jbNuevo;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JPanel panel_6;
    private JPanel panel_7;
    private JPanel panel_8;
    private JPanel panel_3;
    private JPanel derecha;
    private JPanel panel_1;
    private JTextField jtfNombre;
    private JPanel panel_2;
    private JTextField jtfCursada;
    private JPanel panel_4;
    private JTextField jtfAsigntura;
    private ActionListener listener;
    public static final String NUEVO_PARCIAL="NUEVO_PARCIAL";

    /**
     * Create the panel.
     */
    public Parcial_Panel_Datos(ActionListener listener)
    {
        this.listener = listener;

        this.iniciaGeometria();
    }

    private void iniciaGeometria()
    {
        this.izquierda = new JPanel();

        this.panel_3 = new JPanel();

        this.derecha = new JPanel();
        this.derecha.setLayout(new GridLayout(0, 1, 0, 0));

        this.panel_4 = new JPanel();
        this.derecha.add(this.panel_4);

        this.jtfAsigntura = new JTextField();
        this.jtfAsigntura.setEditable(false);
        this.jtfAsigntura.setEnabled(false);
        this.jtfAsigntura.setColumns(10);
        GroupLayout gl_panel_4 = new GroupLayout(this.panel_4);
        gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addGap(0, 422,
                                                                                               Short.MAX_VALUE).addGap(0,
                                                                                                                       422,
                                                                                                                       Short.MAX_VALUE).addGroup(gl_panel_4.createSequentialGroup().addContainerGap().addComponent(this.jtfAsigntura,
                                                                                                                                                                                                                   GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                   402,
                                                                                                                                                                                                                   Short.MAX_VALUE).addContainerGap()));
        gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addGap(0, 62,
                                                                                             Short.MAX_VALUE).addGap(0,
                                                                                                                     125,
                                                                                                                     Short.MAX_VALUE).addGroup(gl_panel_4.createSequentialGroup().addComponent(this.jtfAsigntura,
                                                                                                                                                                                               GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                               GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                               GroupLayout.PREFERRED_SIZE).addContainerGap(21,
                                                                                                                                                                                                                                           Short.MAX_VALUE)));
        this.panel_4.setLayout(gl_panel_4);

        this.panel_2 = new JPanel();
        this.derecha.add(this.panel_2);

        this.jtfCursada = new JTextField();
        this.jtfCursada.setEditable(false);
        this.jtfCursada.setEnabled(false);
        this.jtfCursada.setColumns(10);
        GroupLayout gl_panel_2 = new GroupLayout(this.panel_2);
        gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGap(0, 422,
                                                                                               Short.MAX_VALUE).addGroup(gl_panel_2.createSequentialGroup().addContainerGap().addComponent(this.jtfCursada,
                                                                                                                                                                                           GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                           402,
                                                                                                                                                                                           Short.MAX_VALUE).addContainerGap()));
        gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGap(0, 125,
                                                                                             Short.MAX_VALUE).addGroup(gl_panel_2.createSequentialGroup().addComponent(this.jtfCursada,
                                                                                                                                                                       GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                       GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                       GroupLayout.PREFERRED_SIZE).addContainerGap(21,
                                                                                                                                                                                                                   Short.MAX_VALUE)));
        this.panel_2.setLayout(gl_panel_2);

        this.panel_1 = new JPanel();
        this.derecha.add(this.panel_1);

        this.jtfNombre = new JTextField();
        this.jtfNombre.setEditable(false);
        this.jtfNombre.setEnabled(false);
        this.jtfNombre.setColumns(10);
        GroupLayout gl_panel_1 = new GroupLayout(this.panel_1);
        gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addComponent(this.jtfNombre,
                                                                                                                                                                   GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                   402,
                                                                                                                                                                   Short.MAX_VALUE).addContainerGap()));
        gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup().addComponent(this.jtfNombre,
                                                                                                                                               GroupLayout.PREFERRED_SIZE,
                                                                                                                                               GroupLayout.DEFAULT_SIZE,
                                                                                                                                               GroupLayout.PREFERRED_SIZE).addContainerGap(131,
                                                                                                                                                                                           Short.MAX_VALUE)));
        this.panel_1.setLayout(gl_panel_1);
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addComponent(this.panel_3,
                                                                                                                                                                                                                                                                               GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                               490,
                                                                                                                                                                                                                                                                               Short.MAX_VALUE).addGap(7)).addGroup(groupLayout.createSequentialGroup().addComponent(this.izquierda,
                                                                                                                                                                                                                                                                                                                                                                     GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                                                                                                                     GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                     GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.derecha,
                                                                                                                                                                                                                                                                                                                                                                                                                                                          GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                          422,
                                                                                                                                                                                                                                                                                                                                                                                                                                                          Short.MAX_VALUE).addContainerGap()))));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
                                                                                                 groupLayout.createSequentialGroup().addGap(4).addComponent(this.panel_3,
                                                                                                                                                            GroupLayout.PREFERRED_SIZE,
                                                                                                                                                            33,
                                                                                                                                                            GroupLayout.PREFERRED_SIZE).addGap(4).addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(this.derecha,
                                                                                                                                                                                                                                                                           0,
                                                                                                                                                                                                                                                                           0,
                                                                                                                                                                                                                                                                           Short.MAX_VALUE).addComponent(this.izquierda,
                                                                                                                                                                                                                                                                                                         GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                         100,
                                                                                                                                                                                                                                                                                                         Short.MAX_VALUE)).addContainerGap()));

        this.jbNuevo = new JButton("Nuevo Parcial");
        this.panel_3.add(this.jbNuevo);
        this.izquierda.setLayout(new GridLayout(0, 1, 0, 0));

        this.panel_6 = new JPanel();
        this.izquierda.add(this.panel_6);

        this.lblNewLabel = new JLabel("Asignatura:");
        this.panel_6.add(this.lblNewLabel);

        this.panel_7 = new JPanel();
        this.izquierda.add(this.panel_7);

        this.lblNewLabel_2 = new JLabel("Cursada:");
        this.panel_7.add(this.lblNewLabel_2);

        this.panel_8 = new JPanel();
        this.izquierda.add(this.panel_8);

        this.lblNewLabel_1 = new JLabel("Nombre:");
        this.panel_8.add(this.lblNewLabel_1);
        setLayout(groupLayout);
        this.jbNuevo.setEnabled(false);
        
        this.jbNuevo.setActionCommand(Parcial_Panel_Datos.NUEVO_PARCIAL);
        this.jbNuevo.addActionListener(this.listener);
        this.jbNuevo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setTextNombreEnabled(true);
                jbNuevo.setEnabled(false);
            }
        });
        
    }


    public void setBotonNuevoEnabled(boolean valor)
    {
        this.jbNuevo.setEnabled(valor);
    }

    public void setTextNombreEnabled(boolean valor)
    {
        this.jtfNombre.setEnabled(valor);
        this.jtfNombre.setEditable(valor);
    }

    public void setTextoAsignatura(String arg)
    {
        this.jtfAsigntura.setText(arg);
    }

    public void setTextoCursada(String arg)
    {
        this.jtfCursada.setText(arg);
    }

    public String getTextoNombre()
    {
        return this.jtfNombre.getText();
    }
    public void clearTextoNombre()
    {
       this.jtfNombre.setText("");
    }


}
