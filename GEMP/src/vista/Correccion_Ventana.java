package vista;

import excepciones.NoCompletoException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Examen;
import modelo.Modelo;


public class Correccion_Ventana extends JInternalFrame implements ActionListener, ListSelectionListener
{

    private JPanel contentPane;
    private Correccion_Panel_Arbol panel_derecha;
    private DefaultListModel<Examen> listModel_examenes = new DefaultListModel<Examen>();
    private JPanel panel_izquierda;
    private Modelo modelo;
    private PanelCombo4 panel_iz_sup;
    private JScrollPane scrollPane;
    private JList jList_examenes;
    private Examen examen_seleccionado = null;

    private Correccion_Arbol_Modal ventanaModal = null;


    public Correccion_Ventana(Modelo modelo)
    {
        this.modelo = modelo;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.iniciaGeometria();
        this.actualizaExamenes();
    }


    private void iniciaGeometria()
    {

        setBounds(100, 100, 634, 475);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        this.panel_izquierda = new JPanel();

        this.panel_iz_sup = new PanelCombo4(this.modelo, this);

        

        this.scrollPane = new JScrollPane();
        GroupLayout gl_panel_izquierda = new GroupLayout(this.panel_izquierda);
        gl_panel_izquierda.setHorizontalGroup(gl_panel_izquierda.createParallelGroup(Alignment.TRAILING)
                                              .addGroup(gl_panel_izquierda.createSequentialGroup()
                                                                                                                                    .addContainerGap()
                                                                                                                                    .addGroup(gl_panel_izquierda.createParallelGroup(Alignment.TRAILING)
                                                                                                                                                                .

                                                                                                                                                                addComponent(this.scrollPane,
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

        this.panel_derecha = new Correccion_Panel_Arbol(null, this, true);
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
        this.panel_derecha.setBorder(new TitledBorder("No hay examen seleccionado"));

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.panel_iz_sup)
        {this.actualizaExamenes();
        }

        if (e.getActionCommand().equals(Correccion_Panel_Arbol.GUARDAR))

        {

            if (this.examen_seleccionado != null && this.examen_seleccionado.isModificado())
            {

                try
                {
                    this.examen_seleccionado
                        .getArbol_podado_particular()
                        .procesar();
                    JOptionPane.showMessageDialog(this, "El árbol se ha corregido");
                } catch (NoCompletoException ee)
                {
                    JOptionPane.showMessageDialog(this, ee.getMessage());
                }


                try
                {
                    modelo.getModelo_abm_evaluacion().actualizar_examen(this.examen_seleccionado);
                    this.examen_seleccionado.setModificado(false);
                    
                    this.panel_derecha.actualiza_jtree();
                    this.panel_derecha.verifica_modificado();
                    
                    
                    if(this.ventanaModal!=null)
                    {
                        this.ventanaModal.refresh();
                        }
                    JOptionPane.showMessageDialog(this, "Se guardaron los cambios");
                } catch (SQLException ee)
                {
                    JOptionPane.showMessageDialog(this, ee.getMessage());
                }

            }
        }
        if (e.getActionCommand().equals(Correccion_Panel_Arbol.MAXIMIZAR))
        {
         
            this.ventanaModal = new Correccion_Arbol_Modal("Examen: " + this.examen_seleccionado.toString(), this.panel_derecha.getArbol(), this);
            this.ventanaModal.setExamen(this.examen_seleccionado);
            this.setVisible(false);
            this.ventanaModal.addWindowListener(new WindowAdapter()
                                                
            {
                @Override
                public void windowDeactivated(WindowEvent arg0)
                {

                    Correccion_Ventana.this.ventanaModal.requestFocus();
                    Correccion_Ventana.this.ventanaModal.toFront();
                }

                @Override
                public void windowClosing(WindowEvent e)
                {
                
                    Correccion_Ventana.this.panel_derecha.setArbol(Correccion_Ventana.this.ventanaModal.getArbol());
                   
                    Correccion_Ventana.this.setVisible(true);
                }
            });
            

        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {

        this.examen_seleccionado = (Examen) this.jList_examenes.getSelectedValue();
        if (this.examen_seleccionado != null)
        {
            this.panel_derecha.limpiarZona();
            this.panel_derecha.setExamen(this.examen_seleccionado);

            this.panel_derecha.setBorder(new TitledBorder("Examen: " + this.examen_seleccionado.toString()));


        }


        // TODO Implement this method
    }

    private void actualizaExamenes()
    {
        this.listModel_examenes.removeAllElements();
        if (this.panel_derecha != null)
        {
            this.panel_derecha.setExamen(null);
            this.panel_derecha.setBorder(new TitledBorder("No hay examen seleccionado"));
        }

        if (this.panel_iz_sup != null && this.panel_iz_sup.getInstancia_seleccionada() != null)
        {

            ArrayList<Examen> examenes = this.panel_iz_sup
                                             .getInstancia_seleccionada()
                                             .getExamenes();
            for (int i = 0; i < examenes.size(); i++)
                this.listModel_examenes.addElement(examenes.get(i));

        }
        
    }
}
