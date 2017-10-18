package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Alumno;
import modelo.Examen;
import modelo.Modelo;


public class Correccion_Ventana extends JInternalFrame implements ActionListener,ListSelectionListener
{

    private JPanel contentPane;
    private JPanel panel_derecha;
    private DefaultListModel<Examen> listModel_examenes = new DefaultListModel<Examen>();
    private JPanel panel_izquierda;
    private Modelo modelo;
    private PanelCombo4 panel_iz_sup;
    //private Parcial_Panel_Datos panel_iz_inf;
    private JScrollPane scrollPane;
    private JList jList_examenes;
    //private Parcial_Arbol_Modal ventanaModal = null;
    
    
    public Correccion_Ventana(Modelo modelo)
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

        this.panel_iz_sup = new PanelCombo4(this.modelo, this);

        //this.panel_iz_inf = new Parcial_Panel_Datos(this);

        this.scrollPane = new JScrollPane();
        GroupLayout gl_panel_izquierda = new GroupLayout(this.panel_izquierda);
        gl_panel_izquierda.setHorizontalGroup(gl_panel_izquierda.createParallelGroup(Alignment.TRAILING)
                                              .addGroup(gl_panel_izquierda.createSequentialGroup()
                                                                                                                                    .addContainerGap()
                                                                                                                                    .addGroup(gl_panel_izquierda.createParallelGroup(Alignment.TRAILING)
                                                                                                                                                                
                                                                                                                                                                .addComponent(this.scrollPane,
                                                                                                                                                                              Alignment.LEADING,
                                                                                                                                                                              GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                              248,
                                                                                                                                                                              Short.MAX_VALUE)
                                                                                                                                      .addComponent(this.panel_iz_sup,
                                                                                                                                                    Alignment.LEADING,
                                                                                                                                                    GroupLayout.DEFAULT_SIZE,
                                                                                                                                                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                          .addContainerGap()));
        gl_panel_izquierda.setVerticalGroup(gl_panel_izquierda.createParallelGroup(Alignment.LEADING)
                                            .addGroup(gl_panel_izquierda.createSequentialGroup()
                                                                                                                                 .addGap(5)
                                                                                                                                 .addComponent(this.panel_iz_sup,
                                                                                                                                               GroupLayout.PREFERRED_SIZE,
                                                                                                                                               151, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(5)
                                                                        .addComponent(this.scrollPane,
                                                                                      GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                                                        .addGap(5)));
        this.panel_izquierda.setLayout(gl_panel_izquierda);

        this.panel_derecha = new JPanel();
        GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                          .addGroup(gl_contentPane.createSequentialGroup()
                                                                                                                       .addComponent(this.panel_izquierda,
                                                                                                                                     GroupLayout.PREFERRED_SIZE,
                                                                                                                                     268, GroupLayout.PREFERRED_SIZE)
                                                                  .addPreferredGap(ComponentPlacement.RELATED)
                                                                  .addComponent(this.panel_derecha,
                                                                                GroupLayout.DEFAULT_SIZE, 340,
                                                                                Short.MAX_VALUE)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                                                                      .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                                                                                              .addComponent(this.panel_derecha,
                                                                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                                                                            419,
                                                                                                                                                            Short.MAX_VALUE)
                                                                                                                                              .addComponent(this.panel_izquierda,
                                                                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                                                                            419, Short.MAX_VALUE))
                                                                .addContainerGap()));
        jList_examenes = new javax.swing.JList();
        this.scrollPane.setViewportView(jList_examenes);
        jList_examenes.setBorder(javax.swing
                                       .BorderFactory
                                       .createTitledBorder("Alumnos Evaluados:"));
        this.jList_examenes.setModel(this.listModel_examenes);
        this.contentPane.setLayout(gl_contentPane);
        jList_examenes.addListSelectionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println(e.getActionCommand());
        if(this.panel_iz_sup!=null && e.getActionCommand().equals(PanelCombo4.CAMBIOS_PANEL_COMBO4)){
        System.out.println("Asignatura: " + this.panel_iz_sup.getAsignatura_seleccionada());
        System.out.println("Cursada: " + this.panel_iz_sup.getCursada_seleccionada());
        System.out.println("Parcial: " + this.panel_iz_sup.getParcial_seleccionado());
        System.out.println("Instancia: " + this.panel_iz_sup.getInstancia_seleccionada());}

        if(this.panel_iz_sup!=null &&        this.panel_iz_sup.getInstancia_seleccionada()!=null)
        {
            this.listModel_examenes.removeAllElements();
            ArrayList<Examen> examenes=this.panel_iz_sup.getInstancia_seleccionada().getExamenes();
            for(int i =0;i<examenes.size();i++)
            this.listModel_examenes.addElement(examenes.get(i));
            
            }
     
    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        // TODO Implement this method
    }
}
