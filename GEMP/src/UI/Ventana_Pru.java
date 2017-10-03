package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.LayoutStyle.ComponentPlacement;

import modelo.Asignatura;
import modelo.Cursada;

import modelo.Modelo;
import modelo.Parcial;

public class Ventana_Pru extends JInternalFrame implements ActionListener
{

    private JPanel contentPane;
    private UI_JPanel_Parcial panel_derecha;
    
    private JPanel panel_izquierda;
    private JPanel panel_iz_inf;
    private Modelo modelo;
    private PanelCombo2 panel_iz_sup ;
    
    


    /**
     * Create the frame.
     */
    public Ventana_Pru(Modelo modelo)
    {
        this.modelo = modelo;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.iniciaGeometria();
       
    }


    private void iniciaGeometria()
    {
        
            setBounds(100, 100, 634, 475);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);

            this.panel_izquierda = new JPanel();

           this.panel_iz_sup = new PanelCombo2(this.modelo,this);

            this.panel_iz_inf = new JPanel();
            GroupLayout gl_panel_izquierda = new GroupLayout(this.panel_izquierda);
            gl_panel_izquierda.setHorizontalGroup(
                    gl_panel_izquierda.createParallelGroup(Alignment.TRAILING)
                            .addGroup(gl_panel_izquierda.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(gl_panel_izquierda.createParallelGroup(Alignment.TRAILING)
                                            .addComponent(this.panel_iz_inf, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                                            .addComponent(panel_iz_sup, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap())
            );
            gl_panel_izquierda.setVerticalGroup(
                    gl_panel_izquierda.createParallelGroup(Alignment.LEADING)
                            .addGroup(gl_panel_izquierda.createSequentialGroup()
                                    .addGap(5)
                                    .addComponent(panel_iz_sup, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addComponent(this.panel_iz_inf, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                                    .addContainerGap())
            );
            this.panel_izquierda.setLayout(gl_panel_izquierda);

            this.panel_derecha = new UI_JPanel_Parcial(null);
            GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
            gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                              .addGroup(gl_contentPane.createSequentialGroup()
                                                                                                                           .addComponent(this.panel_izquierda,
                                                                                                                                         GroupLayout.PREFERRED_SIZE,
                                                                                                                                         344, GroupLayout.PREFERRED_SIZE)
                                                                      .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                      .addComponent(this.panel_derecha, GroupLayout.DEFAULT_SIZE,
                                                                                    126, Short.MAX_VALUE)));
            gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                            .addGroup(Alignment.TRAILING,
                                                      gl_contentPane.createSequentialGroup()
                                                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                                                                .addComponent(this.panel_derecha,
                                                                                                                              Alignment.LEADING,
                                                                                                                              GroupLayout.DEFAULT_SIZE,
                                                                                                                              508,
                                                                                                                              Short.MAX_VALUE)
                                                                                                                .addComponent(this.panel_izquierda,
                                                                                                                              GroupLayout.DEFAULT_SIZE,
                                                                                                                              520, Short.MAX_VALUE))
                                                                    .addContainerGap()));
            this.contentPane.setLayout(gl_contentPane);
        
        }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(this.panel_derecha!=null && this.panel_iz_sup!=null){
       
        
    if(this.panel_iz_sup.getAsignatura_seleccionada()!=null&&this.panel_iz_sup.getAsignatura_seleccionada().getArbol_dominio()!=null)
    {this.panel_derecha.setArbol(this.panel_iz_sup.getAsignatura_seleccionada().getArbol_dominio().toEvaluable());
    System.out.println(this.panel_iz_sup.getAsignatura_seleccionada());
     }
        }
    }
}
