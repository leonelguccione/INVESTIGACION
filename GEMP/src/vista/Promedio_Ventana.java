package vista;

import excepciones.NotSemejanteException;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.DefaultTableModel;

import modelo.ArbolPerturbacion;
import modelo.Examen;
import modelo.Modelo;
import modelo.NodoConError;
import modelo.NodoPerturbacion;
import modelo.RelacionImpacto;
import modelo.ResultadoAnalisisArbol;


public class Promedio_Ventana extends JInternalFrame implements ActionListener, ListSelectionListener
{

    private JPanel contentPane;
    private Promedio_Panel_Arbol panelArbol;
    private DefaultListModel<Examen> listModel_examenes = new DefaultListModel<Examen>();
    private DefaultListModel<Examen> listModel_promediados = new DefaultListModel<Examen>();
    private JPanel panel_izquierda;
    private Modelo modelo;
    private PanelCombo4 panel_iz_sup;

    private JScrollPane scrollPaneExamenesEvaluados;
    private JList<Examen> jList_examenes;
    private JList<Examen> jList_promediados;

    private Promedio_Arbol_No_Modal ventanaModal = null;
    private JScrollPane scrollPaneExamenesPromediados;
    private JPanel panelDerechaSuperior;
    private JButton btnMaximizar;
    private JButton btnPromediar;

    private static final String MAXIMIZAR = "MAXIMIZAR";
    private static final String PROMEDIAR = "PROMEDIAR";
    private ArbolPerturbacion arbol_promedio = null;
    private Interface_Arbol_Promedio interface_Arbol;
    ResultadoAnalisisArbol resultadoAnalisis = null;
    private JTable table;
    private JScrollPane scrollPaneTable;


    public Promedio_Ventana(Modelo modelo)
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

        this.panelDerechaSuperior = new JPanel();

        this.scrollPaneExamenesEvaluados = new JScrollPane();
        GroupLayout gl_panel_izquierda = new GroupLayout(this.panel_izquierda);
        gl_panel_izquierda.setHorizontalGroup(gl_panel_izquierda.createParallelGroup(Alignment.TRAILING)
                                              .addGroup(gl_panel_izquierda.createSequentialGroup()
                                                                                                                                    .addContainerGap()
                                                                                                                                    .addGroup(gl_panel_izquierda.createParallelGroup(Alignment.TRAILING)
                                                                                                                                                                .

                                                                                                                                                                addComponent(this.scrollPaneExamenesEvaluados,
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
                                                                        .addComponent(this.scrollPaneExamenesEvaluados,
                                                                                      GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                                                        .addGap(5)));
        this.panel_izquierda.setLayout(gl_panel_izquierda);

        this.panelArbol = new Promedio_Panel_Arbol();

        JPanel panel_derecha = new JPanel();
        GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                          .addGroup(gl_contentPane.createSequentialGroup()
                                                                                                                       .addComponent(panel_izquierda,
                                                                                                                                     GroupLayout.PREFERRED_SIZE,
                                                                                                                                     268, GroupLayout.PREFERRED_SIZE)
                                                                  .addPreferredGap(ComponentPlacement.RELATED)
                                                                  .addComponent(panelArbol, GroupLayout.DEFAULT_SIZE,
                                                                                148, Short.MAX_VALUE)
                                                                  .addPreferredGap(ComponentPlacement.RELATED)
                                                                  .addComponent(panel_derecha,
                                                                                GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                                                  .addContainerGap()));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                      .addGroup(gl_contentPane.createSequentialGroup()
                                                                              .addGap(1)
                                                                              .addComponent(panel_derecha,
                                                                                            GroupLayout.DEFAULT_SIZE,
                                                                                            435,
                                                                                            Short.MAX_VALUE))
                                                      .addGroup(gl_contentPane.createSequentialGroup()
                                                                              .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                                                      .addComponent(panelArbol,
                                                                                                                    GroupLayout.DEFAULT_SIZE,
                                                                                                                    425,
                                                                                                                    Short.MAX_VALUE)
                                                                                                      .addComponent(panel_izquierda,
                                                                                                                    GroupLayout.DEFAULT_SIZE,
                                                                                                                    425, Short.MAX_VALUE))
                                                                              .addContainerGap()));
        panel_derecha.setLayout(new BorderLayout(0, 0));

        scrollPaneExamenesPromediados = new JScrollPane();
        this.panelDerechaSuperior.setLayout(new GridLayout(2, 1));
        this.panelDerechaSuperior.add(scrollPaneExamenesPromediados);
        JPanel panelTable = new JPanel();
        panelTable.setBorder(javax.swing
                                  .BorderFactory
                                  .createTitledBorder("Nodos con errores:"));

        this.panelDerechaSuperior.add(panelTable);

        this.table = new JTable();
        this.scrollPaneTable = new JScrollPane();
        this.scrollPaneTable.setViewportView(this.table);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.table.setBorder(null);
        this.table.setModel(new DefaultTableModel(new Object[][] {


                } , new String[] { "Nodo", "Cant. Errores" }));
        panelTable.setLayout(new BorderLayout());
        panelTable.add(this.scrollPaneTable);

        panel_derecha.add(this.panelDerechaSuperior, BorderLayout.CENTER);
        this.jList_promediados = new JList<Examen>();

        scrollPaneExamenesPromediados.setViewportView(this.jList_promediados);

        JPanel panel = new JPanel();
        panel_derecha.add(panel, BorderLayout.SOUTH);

        this.btnPromediar = new JButton("Promediar");
        this.btnPromediar.setEnabled(false);
        panel.add(btnPromediar);

        this.btnMaximizar = new JButton("Maximizar");
        panel.add(btnMaximizar);
        jList_examenes = new javax.swing.JList<Examen>();
        this.scrollPaneExamenesEvaluados.setViewportView(jList_examenes);
        jList_examenes.setBorder(javax.swing
                                      .BorderFactory
                                      .createTitledBorder("Alumnos Evaluados:"));
        this.jList_examenes.setModel(this.listModel_examenes);
        this.jList_promediados.setModel(this.listModel_promediados);
        this.contentPane.setLayout(gl_contentPane);
        this.interface_Arbol = this.panelArbol;
        this.interface_Arbol.setArbol(null);
        this.jList_promediados.setBorder(javax.swing
                                              .BorderFactory
                                              .createTitledBorder("Examenes Promediados:"));

        this.btnMaximizar.setActionCommand(Promedio_Ventana.MAXIMIZAR);
        this.btnPromediar.setActionCommand(Promedio_Ventana.PROMEDIAR);
        this.btnMaximizar.addActionListener(this);
        this.btnPromediar.addActionListener(this);
        this.jList_examenes.addListSelectionListener(this);

        ListSelectionModel lsm = this.table.getSelectionModel();
        lsm.addListSelectionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.panel_iz_sup)
        {
            this.actualizaExamenes();
        }
        if (e.getActionCommand().equals(Promedio_Ventana.PROMEDIAR))
            this.promediar();


        if (e.getActionCommand().equals(Promedio_Ventana.MAXIMIZAR))
        {

            this.ventanaModal = Promedio_Arbol_No_Modal.getInstance();
            this.ventanaModal.setVisible(true);


            this.interface_Arbol = this.ventanaModal;
            this.interface_Arbol.setArbol(arbol_promedio);
            this.panelArbol.setVisible(false);
            this.ventanaModal.addWindowListener(new WindowAdapter()
            {


                @Override
                public void windowClosing(WindowEvent e)
                {


                    Promedio_Ventana.this.setVisible(true);
                    Promedio_Ventana.this.interface_Arbol = Promedio_Ventana.this.panelArbol;
                    Promedio_Ventana.this.interface_Arbol.setArbol(arbol_promedio);
                    Promedio_Ventana.this.panelArbol.setVisible(true);
                }
            });


        }
    }
    /*
    @Override
    public void valueChanged(ListSelectionEvent e)
    {

        this.examen_seleccionado = (Examen) this.jList_examenes.getSelectedValue();
        if (this.examen_seleccionado != null)
        {
            this.panel_centro.limpiarZona();
            this.panel_centro.setExamen(this.examen_seleccionado);

            this.panel_centro.setBorder(new TitledBorder("Examen: " + this.examen_seleccionado.toString()));
        }
        // TODO Implement this method
    }*/
    private void verificar_enabled()
    {
        boolean habilitado;
        habilitado = this.jList_examenes
                         .getSelectedValuesList()
                         .size() > 1;

        this.btnPromediar.setEnabled(habilitado);
    }


    @Override
    public void valueChanged(ListSelectionEvent e)
    {

        this.verificar_enabled();
        if (e.getSource() == this.table.getSelectionModel())
        {
            int j = this.table.getSelectedRow();
            if (j != -1)
            {
                NodoPerturbacion nodosel = (NodoPerturbacion) this.table
                                                                  .getModel()
                                                                  .getValueAt(j, 0);

                this.panelArbol.setNodoSeleccionado(nodosel);
            }

        }
    }


    private void promediar()
    {
        String titulo = "Alumnos promediados: ";
        List<Examen> seleccionados = this.jList_examenes.getSelectedValuesList();
        ArbolPerturbacion arbol_actual;
        ArrayList<ArbolPerturbacion> arboles = new ArrayList<ArbolPerturbacion>();
        this.listModel_promediados.clear();
        this.interface_Arbol.setArbol(null);
        for (int i = 0; i < seleccionados.size(); i++)
        {
            arbol_actual = seleccionados.get(i).getArbol_podado_particular();
            if (arbol_actual.isCorregido())
            {
                arboles.add(arbol_actual);
                this.listModel_promediados.addElement(seleccionados.get(i));
                titulo = titulo + " - " + seleccionados.get(i)
                                                       .getAlumno()
                                                       .getApellido();

            } else
            {
                JOptionPane.showMessageDialog(this,
                                              "El examen de " + seleccionados.get(i)
                                                                                   .getAlumno()
                                                                                   .toString() +
                                        " no esta totalmente corregido");
            }
        }
        if (arboles.size() > 0)
            try
            {
                this.resultadoAnalisis = ArbolPerturbacion.promedio(arboles);
                arbol_promedio = resultadoAnalisis.getArbol();
                this.arbol_promedio.setNombre(titulo);

                this.interface_Arbol.setArbol(arbol_promedio);
                this.actualizaVisualmenteResultado();

            } catch (ArithmeticException e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (NotSemejanteException e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }


    }

    private void actualizaExamenes()
    {
        this.listModel_examenes.removeAllElements();
        if (this.panel_iz_sup != null && this.panel_iz_sup.getInstancia_seleccionada() != null)
        {
            ArrayList<Examen> examenes = this.panel_iz_sup
                                             .getInstancia_seleccionada()
                                             .getExamenes();
            for (int i = 0; i < examenes.size(); i++)
                this.listModel_examenes.addElement(examenes.get(i));
        }
    }

    private void actualizaVisualmenteResultado()
    {
        if (this.resultadoAnalisis != null)
        {
            Iterator<NodoConError> it = this.resultadoAnalisis
                                            .getNodosCOnError()
                                            .descendingIterator();
            DefaultTableModel model = (DefaultTableModel) this.table.getModel();
            model.setRowCount(0);

            while (it.hasNext())
            {
                NodoConError ne = it.next();

                Object[] array =
                {
                    ne.getNodo(), ne.getCantidad()
                };

                model.addRow(array);
            }
        }
    }
}

