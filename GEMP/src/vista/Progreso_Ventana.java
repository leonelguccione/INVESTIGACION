package vista;

import excepciones.NotSemejanteException;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

import javax.swing.BorderFactory;
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
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import modelo.Alumno;
import modelo.ArbolPerturbacion;
import modelo.Examen;
import modelo.Instancia_Evaluacion;
import modelo.Modelo;
import modelo.NodoPerturbacion;


public class Progreso_Ventana extends JInternalFrame implements ActionListener, ListSelectionListener
{

    private JPanel contentPane;
    private Promedio_Panel_Arbol panelArbol;
    private DefaultListModel<Alumno> listModel_alumnos = new DefaultListModel<Alumno>();
   // private DefaultListModel<Examen> listModel_promediados = new DefaultListModel<Examen>();
    private DefaultListModel<Instancia_Evaluacion> listModel_instancias = new DefaultListModel<Instancia_Evaluacion>();
    private JPanel panel_izquierda;
    private Modelo modelo;
    private PanelCombo3 panel_iz_sup;
    private JScrollPane scrollPaneExamenesEvaluados;
    private JList<Alumno> jList_alumnos;
   // private JList<Examen> jList_promediados;
    private JList<Instancia_Evaluacion> jList_instancias;
    private Promedio_Arbol_No_Modal ventanaModal = null;
    //private JScrollPane scrollPaneExamenesPromediados;
    private JScrollPane scrollPaneInstancias;
    private JButton btnMaximizar;
    //private JButton btnPromediar;
    private static final String MAXIMIZAR_ARBOL = "MAXIMIZAR_ARBOL";
   // private static final String MAXIMIZAR_TABLA = "MAXIMIZAR_TABLA";
   // private static final String PROMEDIAR = "PROMEDIAR";
    private ArbolPerturbacion arbol_resultante = null;
    private Interface_Arbol_Promedio interface_Arbol;
    //private JTable table;
   // private JScrollPane scrollPaneTable;
   // private JPanel panelTable;
    //private DefaultTableModel miTableModel;
    //private Promedio_Tabla_No_Modal ptnm = null;
    private JPanel panelDerechaSuperior;
    private Instancia_Evaluacion primeraInstancia = null;
    private Instancia_Evaluacion segundaInstancia = null;

    public Progreso_Ventana(Modelo modelo)
    {
        this.modelo = modelo;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.iniciaGeometria();
        this.actualizaInstancias();

    }


    private void iniciaGeometria()
    {

        setBounds(100, 100, 634, 475);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        this.panel_izquierda = new JPanel();

        this.panel_iz_sup = new PanelCombo3(this.modelo, this);

        this.panelDerechaSuperior = new JPanel();
        this.scrollPaneInstancias = new JScrollPane();
        this.scrollPaneExamenesEvaluados = new JScrollPane();


        GroupLayout groupLayout = new GroupLayout(this.panel_izquierda);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                       .addGroup(groupLayout.createSequentialGroup()
                                                                                                              .addContainerGap()
                                                                                                              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                                                                                   .addGroup(groupLayout.createSequentialGroup()
                                                                                                                                                        .addComponent(this.scrollPaneExamenesEvaluados,
                                                                                                                                                                      GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                      180,
                                                                                                                                                                      Short.MAX_VALUE)
                                                                                                                                                        .addGap(14))
                                                                                                                                   .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                                                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                                                                                                             .addComponent(this.scrollPaneInstancias,
                                                                                                                                                                                           GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                           180,
                                                                                                                                                                                           Short.MAX_VALUE)
                                                                                                                                                                             .addGap(14))
                                                                                                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                                                                                                             .addComponent(this.panel_iz_sup,
                                                                                                                                                                                           GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                           184,
                                                                                                                                                                                           Short.MAX_VALUE)
                                                                                                                                                                             .addGap(14))))));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                     .addGroup(groupLayout.createSequentialGroup()
                                                                                                            .addContainerGap()
                                                                                                            .addComponent(this.panel_iz_sup,
                                                                                                                          GroupLayout.PREFERRED_SIZE,
                                                                                                                          120, GroupLayout.PREFERRED_SIZE)
                                                          .addPreferredGap(ComponentPlacement.RELATED)
                                                          .addComponent(this.scrollPaneInstancias,
                                                                        GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
                                                          .addPreferredGap(ComponentPlacement.RELATED)
                                                          .addComponent(this.scrollPaneExamenesEvaluados,
                                                                        GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                                                          .addContainerGap()));
        this.panel_izquierda.setLayout(groupLayout);


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

        //scrollPaneExamenesPromediados = new JScrollPane();
        this.panelDerechaSuperior.setLayout(new GridLayout(2, 1));
        //this.panelDerechaSuperior.add(scrollPaneExamenesPromediados);
            /*     this.panelTable = new JPanel();
        panelTable.setBorder(javax.swing
                                  .BorderFactory
                                  .createTitledBorder("Nodos con errores:"));

        this.panelDerechaSuperior.add(panelTable);

        this.table = new JTable();
        this.scrollPaneTable = new JScrollPane();
        this.scrollPaneTable.setViewportView(this.table);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.table.setBorder(null);
        this.miTableModel = new DefaultTableModel(new Object[][] { }, new String[] { "Nodo", "Cant. Errores" })
        {

            @Override
            public boolean isCellEditable(int row, int column)
            {
                //all cells false
                return false;
            };
        };


        this.table.setModel(this.miTableModel);
        panelTable.setLayout(new BorderLayout());
        panelTable.add(this.scrollPaneTable);

        panel_derecha.add(this.panelDerechaSuperior, BorderLayout.CENTER);
        this.jList_promediados = new JList<Examen>();

        scrollPaneExamenesPromediados.setViewportView(this.jList_promediados); */

        JPanel panel = new JPanel();
        panel_derecha.add(panel, BorderLayout.SOUTH);

        /*   this.btnPromediar = new JButton("Promediar");
        this.btnPromediar.setEnabled(false);
        panel.add(btnPromediar);  */

        this.btnMaximizar = new JButton("Maximizar");
        panel.add(btnMaximizar);

        jList_alumnos = new javax.swing.JList<Alumno>();
        this.scrollPaneExamenesEvaluados.setViewportView(jList_alumnos);
        jList_alumnos.setBorder(javax.swing
                                     .BorderFactory
                                     .createTitledBorder("Alumnos Evaluados:"));
        this.jList_alumnos.setModel(this.listModel_alumnos);

        this.jList_instancias = new JList<Instancia_Evaluacion>();
        this.scrollPaneInstancias.setViewportView(this.jList_instancias);
        this.jList_instancias.setBorder(javax.swing
                                             .BorderFactory
                                             .createTitledBorder("Instancias de Evaluación:"));
        this.jList_instancias.setModel(this.listModel_instancias);

       // this.jList_promediados.setModel(this.listModel_promediados);
        this.contentPane.setLayout(gl_contentPane);
        this.interface_Arbol = this.panelArbol;
        this.interface_Arbol.setArbol(null);
        /*  this.jList_promediados.setBorder(javax.swing
                                              .BorderFactory
                                              .createTitledBorder("Examenes Promediados:")); */

        this.btnMaximizar.setActionCommand(Progreso_Ventana.MAXIMIZAR_ARBOL);
     //   this.btnPromediar.setActionCommand(Progreso_Ventana.PROMEDIAR);
        this.btnMaximizar.addActionListener(this);
       // this.btnPromediar.addActionListener(this);
        this.jList_alumnos.addListSelectionListener(this);
        this.jList_instancias.addListSelectionListener(this);
      //  ListSelectionModel lsm = this.table.getSelectionModel();
      //  lsm.addListSelectionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.panel_iz_sup)
        {
            this.actualizaInstancias();
        }


        if (e.getActionCommand().equals(Progreso_Ventana.MAXIMIZAR_ARBOL))
        {

            this.ventanaModal = Promedio_Arbol_No_Modal.getInstance();
            this.ventanaModal.setVisible(true);


            this.interface_Arbol = this.ventanaModal;
            this.interface_Arbol.setArbol(arbol_resultante);
            this.panelArbol.setVisible(false);
            this.ventanaModal.addWindowListener(new WindowAdapter()
            {


                @Override
                public void windowClosing(WindowEvent e)
                {


                    Progreso_Ventana.this.setVisible(true);
                    Progreso_Ventana.this.interface_Arbol = Progreso_Ventana.this.panelArbol;
                    Progreso_Ventana.this.interface_Arbol.setArbol(arbol_resultante);
                    Progreso_Ventana.this.panelArbol.setVisible(true);
                }
            });


        }
        /*  if (e.getActionCommand().equals(Progreso_Ventana.MAXIMIZAR_TABLA))
        {
            this.ptnm = new Promedio_Tabla_No_Modal(this.miTableModel, this);
        } */
    }

    private void obtiene_alumnos_en_comun()
    {
        boolean habilitado;
        this.listModel_alumnos.clear();
        this.arbol_resultante=null;
        this.interface_Arbol.setArbol(this.arbol_resultante);
        
        if (this.jList_instancias
                .getSelectedValuesList()
                .size() == 2)
        {

            Instancia_Evaluacion i0 = this.jList_instancias
                                          .getSelectedValuesList()
                                          .get(0);
            Instancia_Evaluacion i1 = this.jList_instancias
                                          .getSelectedValuesList()
                                          .get(1);
            if (i0.getFecha().compareTo(i1.getFecha()) < 0)
            {
                this.primeraInstancia = i0;
                this.segundaInstancia = i1;
            } else
            {
                this.primeraInstancia = i1;
                this.segundaInstancia = i0;
            }

            for (int i = 0; i < i0.getAlumnos_evaluados().size(); i++)
                if (i1.getAlumnos_evaluados().contains(i0.getAlumnos_evaluados().get(i)))
                    this.listModel_alumnos.addElement(i0.getAlumnos_evaluados().get(i));


        }
    }


    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        if (e.getSource() == this.jList_instancias)
            this.obtiene_alumnos_en_comun();
        if (e.getSource() == this.jList_alumnos)
            this.muestra_arbol_resultante();
        /*        if (e.getSource() == this.table.getSelectionModel() ||
            (this.ptnm != null && e.getSource() == this.ptnm.getSlectionModel()))

        {

            JTable latabletocada;
            if (e.getSource() == this.table.getSelectionModel())
                latabletocada = this.table;
            else
                latabletocada = this.ptnm.getTable();

            int j = latabletocada.getSelectedRow();
            if (j != -1)
            {
                Object o = this.miTableModel.getValueAt(j, 0);

                NodoPerturbacion nodosel = (NodoPerturbacion) o;

                this.panelArbol.setNodoSeleccionado(nodosel);

            }

        } */
    }


    private void actualizaInstancias()
    {
        this.listModel_alumnos.removeAllElements();
        this.listModel_instancias.removeAllElements();
        if (this.panel_iz_sup != null && this.panel_iz_sup.getParcial_seleccionado() != null)
        {
            ArrayList<Instancia_Evaluacion> instancias = this.panel_iz_sup
                                                             .getParcial_seleccionado()
                                                             .getInstancias_evaluaciones();
            this.listModel_instancias.removeAllElements();
            for (int i = 0; i < instancias.size(); i++)
                this.listModel_instancias.addElement(instancias.get(i));
        }
    }


    private void muestra_arbol_resultante()
    {
        Examen examen1, examen2;
        Alumno alumnoElegido = this.jList_alumnos.getSelectedValue();
        if (alumnoElegido != null)
        {
            examen1 = this.primeraInstancia.getExamen(alumnoElegido);
            examen2 = this.segundaInstancia.getExamen(alumnoElegido);
            if (examen1 != null && examen1.getArbol_podado_particular().isCorregido() && examen2 != null &&
                examen2.getArbol_podado_particular().isCorregido())
            {
                this.arbol_resultante = examen2.getArbol_podado_particular().clone();
                try
                {
                    this.arbol_resultante.resta(examen1.getArbol_podado_particular());
                } catch (NotSemejanteException e)
                {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
String titulo="Asignatura: "+this.panel_iz_sup.getAsignatura_seleccionada().getNombre()+" Cursada: "+this.panel_iz_sup.getCursada_seleccionada()+" Parcial: "+this.panel_iz_sup.getParcial_seleccionado()+" Alumno: "+alumnoElegido.toString()+" Progreso desde la instancia : "+this.primeraInstancia+" hacia : "+this.segundaInstancia ;
this.arbol_resultante.setNombre(titulo);
                this.interface_Arbol.setArbol(this.arbol_resultante);
            } else
            {
                this.arbol_resultante = null;
                this.interface_Arbol.setArbol(this.arbol_resultante);
                String cartelito = "El examen de " + alumnoElegido.toString() + " no esta corregido en la instancia ";
                if (!examen1.getArbol_podado_particular().isCorregido())
                    cartelito = cartelito + this.primeraInstancia;

                else
                    cartelito = cartelito + this.segundaInstancia;

                JOptionPane.showMessageDialog(this, cartelito);


            }

        }
    }
}

