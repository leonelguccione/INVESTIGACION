package UI;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.FlowLayout;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import javax.swing.JTextField;

import modelo.Asignatura;
import modelo.Cursada;
import modelo.Instancia_Evaluacion;
import modelo.Modelo;
import modelo.Parcial;

public class Correccion_Panel_Valores extends JPanel
{
    private JPanel panel;
    private JPanel panel_1;
    private JLabel lblAsignatura;
    private JPanel panel_2;
    private JPanel panel_3;
    private JLabel label;
    private JPanel panel_4;
    private JLabel label_1;
    private JPanel panel_5;
    private JLabel label_2;
    private JTextField desconocido = new JTextField();
    private JTextField parcialmente = new JTextField(); ;
    private JTextField conocido = new JTextField(); ;
    private JTextField aprendido = new JTextField(); ;


    private JPanel panel_6;
    private JPanel panel_7;

    private JPanel panel_8;

    private JPanel panel_9;


    /**
     * Create the panel.
     */
    public Correccion_Panel_Valores()
    {
        this.iniciaGeometria();


    }


    private void iniciaGeometria()
    {


        this.panel = new JPanel();


        this.panel_1 = new JPanel();
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                       .addGroup(groupLayout.createSequentialGroup()
                                                                                                              .addContainerGap()
                                                                                                              .addComponent(this.panel,
                                                                                                                            GroupLayout.PREFERRED_SIZE,
                                                                                                                            87, GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(ComponentPlacement.UNRELATED)
                                                            .addComponent(this.panel_1, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                                                            .addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                     .addGroup(Alignment.TRAILING,
                                               groupLayout.createSequentialGroup()
                                                                                                                                .addContainerGap()
                                                                                                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                                                                                                                     .addComponent(this.panel_1,
                                                                                                                                                                   Alignment.LEADING,
                                                                                                                                                                   GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                   278,
                                                                                                                                                                   Short.MAX_VALUE)
                                                                                                                                                     .addComponent(this.panel,
                                                                                                                                                                   Alignment.LEADING,
                                                                                                                                                                   GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                   278, Short.MAX_VALUE))
                                                          .addContainerGap()));
        this.panel_1.setLayout(new GridLayout(0, 1, 0, 0));

        this.panel_8 = new JPanel();
        this.panel_1.add(this.panel_8);


        GroupLayout gl_panel_8 = new GroupLayout(this.panel_8);
        gl_panel_8.setHorizontalGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
                                      .addGroup(gl_panel_8.createSequentialGroup()
                                                                                                           .addContainerGap()
                                                                                                           .addComponent(this.desconocido,
                                                                                                                         0,
                                                                                                                         299, Short.MAX_VALUE)
                                                          .addContainerGap()));
        gl_panel_8.setVerticalGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
                                    .addGroup(gl_panel_8.createSequentialGroup()
                                                                                                         .addGap(5)
                                                                                                         .addComponent(this.desconocido,
                                                                                                                       GroupLayout.PREFERRED_SIZE,
                                                                                                                       GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addContainerGap(50, Short.MAX_VALUE)));
        this.panel_8.setLayout(gl_panel_8);

        this.panel_9 = new JPanel();
        this.panel_1.add(this.panel_9);


        GroupLayout gl_panel_9 = new GroupLayout(this.panel_9);
        gl_panel_9.setHorizontalGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
                                      .addGroup(gl_panel_9.createSequentialGroup()
                                                                                                           .addContainerGap()
                                                                                                           .addComponent(this.parcialmente,
                                                                                                                         0,
                                                                                                                         299, Short.MAX_VALUE)
                                                          .addContainerGap()));
        gl_panel_9.setVerticalGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
                                    .addGroup(gl_panel_9.createSequentialGroup()
                                                                                                         .addGap(5)
                                                                                                         .addComponent(this.parcialmente,
                                                                                                                       GroupLayout.PREFERRED_SIZE,
                                                                                                                       GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addContainerGap(50, Short.MAX_VALUE)));
        this.panel_9.setLayout(gl_panel_9);

        this.panel_6 = new JPanel();
        this.panel_1.add(this.panel_6);


        GroupLayout gl_panel_6 = new GroupLayout(this.panel_6);
        gl_panel_6.setHorizontalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
                                      .addGroup(gl_panel_6.createSequentialGroup()
                                                                                                           .addContainerGap()
                                                                                                           .addComponent(this.conocido,
                                                                                                                         0,
                                                                                                                         299, Short.MAX_VALUE)
                                                          .addContainerGap()));
        gl_panel_6.setVerticalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
                                    .addGroup(gl_panel_6.createSequentialGroup()
                                                                                                         .addGap(5)
                                                                                                         .addComponent(this.conocido,
                                                                                                                       GroupLayout.PREFERRED_SIZE,
                                                                                                                       GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addContainerGap(50, Short.MAX_VALUE)));
        this.panel_6.setLayout(gl_panel_6);

        this.panel_7 = new JPanel();
        this.panel_1.add(this.panel_7);


        GroupLayout gl_panel_7 = new GroupLayout(this.panel_7);
        gl_panel_7.setHorizontalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
                                      .addGroup(gl_panel_7.createSequentialGroup()
                                                                                                           .addContainerGap()
                                                                                                           .addComponent(this.aprendido,
                                                                                                                         0,
                                                                                                                         299, Short.MAX_VALUE)
                                                          .addContainerGap()));
        gl_panel_7.setVerticalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
                                    .addGroup(gl_panel_7.createSequentialGroup()
                                                                                                         .addGap(5)
                                                                                                         .addComponent(this.aprendido,
                                                                                                                       GroupLayout.PREFERRED_SIZE,
                                                                                                                       GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addContainerGap(50, Short.MAX_VALUE)));
        this.panel_7.setLayout(gl_panel_7);
        this.panel.setLayout(new GridLayout(0, 1, 0, 0));

        this.panel_4 = new JPanel();
        this.panel.add(this.panel_4);

        this.label_1 = new JLabel("Desconocido:");
        GroupLayout gl_panel_4 = new GroupLayout(this.panel_4);
        gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
                                      .addGroup(gl_panel_4.createSequentialGroup()
                                                                                                           .addContainerGap()
                                                                                                           .addComponent(this.label_1)
                                                                                                           .addContainerGap(65,
                                                                                                                            Short.MAX_VALUE)));
        gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
                                    .addGroup(gl_panel_4.createSequentialGroup()
                                                                                                         .addContainerGap()
                                                                                                         .addComponent(this.label_1)
                                                                                                         .addContainerGap(52,
                                                                                                                          Short.MAX_VALUE)));
        this.panel_4.setLayout(gl_panel_4);

        this.panel_5 = new JPanel();
        this.panel.add(this.panel_5);

        this.label_2 = new JLabel("Parcialmente Conocido:");
        GroupLayout gl_panel_5 = new GroupLayout(this.panel_5);
        gl_panel_5.setHorizontalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
                                      .addGroup(gl_panel_5.createSequentialGroup()
                                                                                                           .addContainerGap()
                                                                                                           .addComponent(this.label_2)
                                                                                                           .addContainerGap(77,
                                                                                                                            Short.MAX_VALUE)));
        gl_panel_5.setVerticalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
                                    .addGroup(gl_panel_5.createSequentialGroup()
                                                                                                         .addContainerGap()
                                                                                                         .addComponent(this.label_2)
                                                                                                         .addContainerGap(52,
                                                                                                                          Short.MAX_VALUE)));
        this.panel_5.setLayout(gl_panel_5);

        this.panel_3 = new JPanel();
        this.panel.add(this.panel_3);

        this.label = new JLabel("Conocido:");
        GroupLayout gl_panel_3 = new GroupLayout(this.panel_3);
        gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
                                      .addGroup(gl_panel_3.createSequentialGroup()
                                                                                                           .addContainerGap()
                                                                                                           .addComponent(this.label)
                                                                                                           .addContainerGap(86,
                                                                                                                            Short.MAX_VALUE)));
        gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
                                    .addGroup(gl_panel_3.createSequentialGroup()
                                                                                                         .addContainerGap()
                                                                                                         .addComponent(this.label)
                                                                                                         .addContainerGap(52,
                                                                                                                          Short.MAX_VALUE)));
        this.panel_3.setLayout(gl_panel_3);

        this.panel_2 = new JPanel();
        this.panel.add(this.panel_2);

        this.lblAsignatura = new JLabel("Aprendido:");
        GroupLayout gl_panel_2 = new GroupLayout(this.panel_2);
        gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                                      .addGroup(gl_panel_2.createSequentialGroup()
                                                                                                           .addContainerGap()
                                                                                                           .addComponent(this.lblAsignatura)
                                                                                                           .addContainerGap(54,
                                                                                                                            Short.MAX_VALUE)));
        gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                                    .addGroup(gl_panel_2.createSequentialGroup()
                                                                                                         .addContainerGap()
                                                                                                         .addComponent(this.lblAsignatura)
                                                                                                         .addContainerGap(52,
                                                                                                                          Short.MAX_VALUE)));
        this.panel_2.setLayout(gl_panel_2);
        setLayout(groupLayout);
    }
}
