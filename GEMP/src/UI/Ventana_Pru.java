package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

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

   

    class MyPopupMenuListener implements PopupMenuListener
            {
                    public void popupMenuCanceled(PopupMenuEvent popupMenuEvent)
                    {
                            System.out.println("Canceled");
                    }

                    public void popupMenuWillBecomeInvisible(PopupMenuEvent popupMenuEvent)
                    {
                            System.out.println("Becoming Invisible");
                    }

                    public void popupMenuWillBecomeVisible(PopupMenuEvent popupMenuEvent)
                    {
                            System.out.println("Becoming Visible");
                    }
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
        gl_panel_izquierda.setHorizontalGroup(gl_panel_izquierda.createParallelGroup(Alignment.TRAILING)
                                              .addGroup(gl_panel_izquierda.createSequentialGroup()
                                                                                                                                    .addContainerGap()
                                                                                                                                    .addGroup(gl_panel_izquierda.createParallelGroup(Alignment.TRAILING)
                                                                                                                                                                .addComponent(this.panel_iz_inf,
                                                                                                                                                                              Alignment.LEADING,
                                                                                                                                                                              GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                              248,
                                                                                                                                                                              Short.MAX_VALUE)
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
                                                                                                                                               101, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(5)
                                                                        .addComponent(this.scrollPane,
                                                                                      GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                                                        .addGap(5)
                                                                        .addComponent(this.panel_iz_inf,
                                                                                      GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(5)));
        this.panel_izquierda.setLayout(gl_panel_izquierda);

        this.panel_derecha = new UI_JPanel_Parcial(null, this,true);
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
        jList_parciales = new javax.swing.JList();
        this.scrollPane.setViewportView(jList_parciales);
        jList_parciales.setBorder(javax.swing
                                       .BorderFactory
                                       .createTitledBorder("Listado de Parciales"));
        this.jList_parciales.setModel(this.listModel_parciales);
        this.contentPane.setLayout(gl_contentPane);
        jList_parciales.addListSelectionListener(this);
        this.creaPopup();
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


                if (this.panel_iz_sup
                        .getAsignatura_seleccionada()
                        .getArbol_dominio() != null)
                {
                    this.panel_derecha.setArbol(this.panel_iz_sup
                                                    .getAsignatura_seleccionada()
                                                    .getArbol_dominio()
                                                    .toEvaluable());
                } else
                    this.panel_derecha.setArbol(null);
                this.panel_iz_inf.setBotonNuevoEnabled(this.panel_iz_sup.getCursada_seleccionada() != null &&
                                                       this.panel_iz_sup
                                                                                                                  .getAsignatura_seleccionada()
                                                                                                                  .getArbol_dominio() !=
null);
            }
        if (e.getActionCommand().equals(Parcial_Panel_Datos.NUEVO_PARCIAL))
        {
            this.panel_derecha.setModoEdicion(true);
            this.panel_iz_sup.setEnabled(false);
            this.jList_parciales.setEnabled(false);
            this.jList_parciales.clearSelection();
            this.panel_derecha.setArbol(this.panel_iz_sup
                                            .getAsignatura_seleccionada()
                                            .getArbol_dominio()
                                            .toEvaluable());
            this.panel_iz_inf.setTextoAsignatura(this.panel_iz_sup
                                                     .getAsignatura_seleccionada()
                                                     .getNombre());
            this.panel_iz_inf.setTextoCursada(this.panel_iz_sup
                                                  .getCursada_seleccionada()
                                                  .getNombreCursada());

        }

        if (e.getActionCommand().equals(UI_JPanel_Parcial.CANCELAR))
        {
            this.limpiar();
        }
        if (e.getActionCommand().equals(UI_JPanel_Parcial.GUARDAR))
        {
            if (this.panel_iz_inf
                    .getTextoNombre()
                    .isEmpty())
            {
                JOptionPane.showMessageDialog(this, "El nuevo parcial debe tener un nombre");
            } else

                try
                {
                    this.modelo
                        .getModelo_abm_parcial()
                        .AgregarParcial(this.panel_iz_sup.getCursada_seleccionada(),
                                        new Parcial(0, this.panel_iz_inf.getTextoNombre(),
                                                    this.panel_derecha.getArbol()));

                    this.modelo.recuperarParciales(this.panel_iz_sup.getCursada_seleccionada());
                    this.listModel_parciales.clear();
                    this.actualizaListadoParciales();

                    this.limpiar();
                    JOptionPane.showMessageDialog(this, "Parcial agregado");
                } catch (SQLException f)
                {
                    JOptionPane.showMessageDialog(this, f.getMessage());
                }


        }
        
        if(e.getActionCommand().equals(UI_JPanel_Parcial.MAXIMIZAR))
        {
            Class1 c1=new Class1("",this.panel_derecha.getArbol(),this);
            }

    }


    private void limpiar()
    {
        this.panel_derecha.setModoEdicion(false);
        this.panel_iz_sup.setEnabled(true);
        this.jList_parciales.setEnabled(true);
        this.panel_iz_inf.clearTextoNombre();
        this.panel_iz_inf.setTextNombreEnabled(false);
        this.panel_iz_inf.setTextoAsignatura("");
        this.panel_iz_inf.setTextoCursada("");

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
        ArrayList<Parcial> parciales_actuales = this.panel_iz_sup
                                                    .getCursada_seleccionada()
                                                    .getParciales();
        for (int i = 0; i < parciales_actuales.size(); i++)
        {
            this.listModel_parciales.addElement(parciales_actuales.get(i));
        }
    }

    private void creaPopup()
    {System.out.println("Cree el popup");
        //this.panel_derecha.addMouseListener(new MiMouseListener());
        JPopupMenu popupMenu = new JPopupMenu("Title");
                PopupMenuListener popupMenuListener = new MyPopupMenuListener();

                popupMenu.addPopupMenuListener(popupMenuListener);

                JMenuItem cutMenuItem = new JMenuItem("Cut");
                popupMenu.add(cutMenuItem);

                JMenuItem copyMenuItem = new JMenuItem("Copy");
                popupMenu.add(copyMenuItem);

                JMenuItem pasteMenuItem = new JMenuItem("Paste");
                pasteMenuItem.setEnabled(false);
                popupMenu.add(pasteMenuItem);

                popupMenu.addSeparator();

                JMenuItem findMenuItem = new JMenuItem("Find");
                popupMenu.add(findMenuItem);
                
                this.panel_derecha.setComponentPopupMenu(popupMenu);
                this.panel_derecha.setInheritsPopupMenu(true);
                
                
                
                
                        
                        

    }
}
