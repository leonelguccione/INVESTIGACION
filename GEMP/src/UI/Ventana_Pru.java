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

import modelo.Modelo;
import modelo.Parcial;

public class Ventana_Pru extends JInternalFrame implements ActionListener, ListSelectionListener
{

    private JPanel contentPane;
    private UI_JPanel_Parcial panel_derecha;
    private DefaultListModel<Parcial> listModel_parciales = new DefaultListModel<Parcial>();
    private JPanel panel_izquierda;
    private Modelo modelo;
    private PanelCombo2 panel_iz_sup;
    private Parcial_Panel_Datos panel_iz_inf;
    private JScrollPane scrollPane;
    private JList jList_parciales;


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

        this.panel_iz_sup = new PanelCombo2(this.modelo, this);

        this.panel_iz_inf = new Parcial_Panel_Datos(this);

        this.scrollPane = new JScrollPane();
        GroupLayout gl_panel_izquierda = new GroupLayout(this.panel_izquierda);
        gl_panel_izquierda.setHorizontalGroup(gl_panel_izquierda.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_izquierda.createSequentialGroup().addContainerGap().addGroup(gl_panel_izquierda.createParallelGroup(Alignment.TRAILING).addComponent(this.panel_iz_inf,
                                                                                                                                                                                                                                                                Alignment.LEADING,
                                                                                                                                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                248,
                                                                                                                                                                                                                                                                Short.MAX_VALUE).addComponent(this.scrollPane,
                                                                                                                                                                                                                                                                                              Alignment.LEADING,
                                                                                                                                                                                                                                                                                              GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                              248,
                                                                                                                                                                                                                                                                                              Short.MAX_VALUE).addComponent(this.panel_iz_sup,
                                                                                                                                                                                                                                                                                                                            Alignment.LEADING,
                                                                                                                                                                                                                                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                            Short.MAX_VALUE)).addContainerGap()));
        gl_panel_izquierda.setVerticalGroup(gl_panel_izquierda.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_izquierda.createSequentialGroup().addGap(5).addComponent(this.panel_iz_sup,
                                                                                                                                                                                 GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                 101,
                                                                                                                                                                                 GroupLayout.PREFERRED_SIZE).addGap(5).addComponent(this.scrollPane,
                                                                                                                                                                                                                                    GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                    77,
                                                                                                                                                                                                                                    Short.MAX_VALUE).addGap(5).addComponent(this.panel_iz_inf,
                                                                                                                                                                                                                                                                            GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                            126,
                                                                                                                                                                                                                                                                            GroupLayout.PREFERRED_SIZE).addGap(5)));
        this.panel_izquierda.setLayout(gl_panel_izquierda);

        this.panel_derecha = new UI_JPanel_Parcial(null);
        GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup().addComponent(this.panel_izquierda,
                                                                                                                                                             GroupLayout.PREFERRED_SIZE,
                                                                                                                                                             268,
                                                                                                                                                             GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.panel_derecha,
                                                                                                                                                                                                                                                  GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                  340,
                                                                                                                                                                                                                                                  Short.MAX_VALUE)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(this.panel_derecha,
                                                                                                                                                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                            419,
                                                                                                                                                                                                                            Short.MAX_VALUE).addComponent(this.panel_izquierda,
                                                                                                                                                                                                                                                          GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                          419,
                                                                                                                                                                                                                                                          Short.MAX_VALUE)).addContainerGap()));
        jList_parciales = new javax.swing.JList();
        this.scrollPane.setViewportView(jList_parciales);
        jList_parciales.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Parciales"));
        this.jList_parciales.setModel(this.listModel_parciales);
        this.contentPane.setLayout(gl_contentPane);
        jList_parciales.addListSelectionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
       
        if (e.getActionCommand().equals(PanelCombo2.CAMBIOS_PANEL_COMBO))

            if (this.panel_derecha != null && this.panel_iz_sup != null &&
                this.panel_iz_sup.getAsignatura_seleccionada() != null)
            {
                this.listModel_parciales.clear();
                if (this.panel_iz_sup.getCursada_seleccionada() != null)
                    this.actualizaListadoParciales();


                if (this.panel_iz_sup.getAsignatura_seleccionada().getArbol_dominio() != null)
                {
                    this.panel_derecha.setArbol(this.panel_iz_sup.getAsignatura_seleccionada().getArbol_dominio().toEvaluable());
                } else
                    this.panel_derecha.setArbol(null);
                this.panel_iz_inf.setBotonNuevoEnabled(this.panel_iz_sup.getCursada_seleccionada() != null &&
                                                       this.panel_iz_sup.getAsignatura_seleccionada().getArbol_dominio() !=
                                                       null);
            }
        if (e.getActionCommand().equals(Parcial_Panel_Datos.NUEVO_PARCIAL))
        {
            this.panel_derecha.setModoEdicion(true);
          //FALTARIA DESHABILITAR TODO EL COMBO PANEL
            }
        


    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {

        Parcial parcial = (Parcial) this.jList_parciales.getSelectedValue();
        if (parcial != null)
            this.panel_derecha.setArbol(parcial.getArbol_podado());


    }

    private void actualizaListadoParciales()
    {
        ArrayList<Parcial> parciales_actuales = this.panel_iz_sup.getCursada_seleccionada().getParciales();
        for (int i = 0; i < parciales_actuales.size(); i++)
        {
            this.listModel_parciales.addElement(parciales_actuales.get(i));
        }
    }
}
