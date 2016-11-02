
package UI;

import arbol_visual.ArbolVisual;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import modelo.Asignatura;
import modelo.Cursada;
import modelo.EtiquetaBean;
import modelo.Examen;
import modelo.Instancia_Evaluacion;
import modelo.Modelo;
import modelo.Nodo_Perturbacion;
import modelo.Parcial;

/**
 *
 * @author leonel
 */
public class UI_Correccion extends javax.swing.JInternalFrame {
    private Modelo modelo;
    private DefaultListModel listModelEvaluaciones = new DefaultListModel();
    private DefaultComboBoxModel<Asignatura> comboBoxModelAsignatura = new DefaultComboBoxModel<Asignatura>();
    private DefaultComboBoxModel<Cursada> comboBoxModelCursada = new DefaultComboBoxModel<Cursada>();
    private DefaultComboBoxModel<Parcial> comboBoxModelParciales = new DefaultComboBoxModel<Parcial>();
    private DefaultComboBoxModel<Instancia_Evaluacion> comboBoxModelInstEvaluacion =
        new DefaultComboBoxModel<Instancia_Evaluacion>();
    private DefaultListModel listModelexamenes = new DefaultListModel();
    private Nodo_Perturbacion nodo_seleccionado = null;
    private Asignatura asignatura_seleccionada = null;
    private Cursada cursada_seleccionada = null;
    private Parcial parcial_seleccionado = null;
    private Instancia_Evaluacion instancia_seleccionada = null;
    private Examen examen_seleccionado = null;
    private ArbolVisual jTree_Arbol_Perturbacion = null;
    private MouseAdapter mouseA;

    /** Creates new form UI_Correccion */
    public UI_Correccion(Modelo modelo) {
        initComponents();
        jTree_Arbol_Perturbacion = (ArbolVisual) this.jScrollPane_arbol;
        this.modelo = modelo;

        this.jComboBox_Asignatura.setModel((ComboBoxModel) comboBoxModelAsignatura);
        this.jComboBox_Cursada.setModel((ComboBoxModel) this.comboBoxModelCursada);
        this.jComboBox_Parcial.setModel((ComboBoxModel) this.comboBoxModelParciales);
        this.jComboBox_Inst_Evaluacion.setModel((ComboBoxModel) this.comboBoxModelInstEvaluacion);
        this.jLista_Examenes.setModel(listModelexamenes);
        this.actualizar_combo_asignatura();
        this.mouseA = new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me) {
                jtree_arbolMouseClicked();
            }
        };
        this.jTree_Arbol_Perturbacion.addMouseListener(mouseA);
    }

    private void actualizar_combo_asignatura() {
        this.comboBoxModelAsignatura.removeAllElements();
        Iterator iterator_asignaturas = modelo.getAsignaturas().values().iterator();
        //Recorrer el contenido del Iterator
        while (iterator_asignaturas.hasNext())
        {
            Asignatura asignatura = (Asignatura) iterator_asignaturas.next();
            this.comboBoxModelAsignatura.addElement(asignatura);
        }
    }

    private void actualizar_combo_cursadas() {
        this.comboBoxModelCursada.removeAllElements();
        Iterator iterator_cursadas;
        if(this.asignatura_seleccionada!=null)
        
        {
            iterator_cursadas =this.asignatura_seleccionada.getCursadas().iterator();
            while (iterator_cursadas.hasNext())
            {
                Cursada cursada = (Cursada) iterator_cursadas.next();
                this.comboBoxModelCursada.addElement(cursada);
            }
        } 
    }
    
    
    
    private void actualizar_combo_parciales() {
        this.comboBoxModelParciales.removeAllElements();
        Iterator iterator_parciales;
        if(this.cursada_seleccionada!=null)
        {
            iterator_parciales = this.cursada_seleccionada.getParciales().iterator();
            while (iterator_parciales.hasNext())
            {
                Parcial parcial= (Parcial) iterator_parciales.next();
                this.comboBoxModelParciales.addElement(parcial);
            }
        } 
    }


    private void actualizar_combo_instancias() {
        this.comboBoxModelInstEvaluacion.removeAllElements();
        Iterator iterator_instancias;
        if(this.parcial_seleccionado!=null){
            iterator_instancias = this.parcial_seleccionado.getInstancias_evaluaciones().iterator();
                
            while (iterator_instancias.hasNext())
            {
                Instancia_Evaluacion instancia= (Instancia_Evaluacion) iterator_instancias.next();
                this.comboBoxModelInstEvaluacion.addElement(instancia);
            }
        } 
    }

    @SuppressWarnings("unchecked")
    private void initComponents()//GEN-BEGIN:initComponents
    {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jT_Identificador = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jT_Asignatura = new javax.swing.JTextField();
        jT_Anio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jT_Cuatrimestre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLista_Examenes = new javax.swing.JList();
        jLabel18 = new javax.swing.JLabel();
        jT_Fecha = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jT_Descripcion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jT_Alumno = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jT_id_arbol_perturbacion = new javax.swing.JTextField();
        jT_descripcion_arbol_perturbacion = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTextDesconocido = new javax.swing.JTextField();
        jTextParcialmente = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextConocido = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextAprendido = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jBAceptar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jText_estado_correccion = new javax.swing.JTextPane();
        jScrollPane_arbol = new ArbolVisual();
        jB_Salir = new javax.swing.JButton();
        jB_Guardar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jComboBox_Asignatura = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox_Cursada = new javax.swing.JComboBox<>();
        jComboBox_Inst_Evaluacion = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox_Parcial = new javax.swing.JComboBox<>();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(930, 630));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Actualización de Evaluaciones");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Una Evaluacion"));

        jLabel3.setText("Identificador:");

        jT_Identificador.setEditable(false);
        jT_Identificador.setFocusable(false);

        jLabel4.setText("Asignatura:");

        jT_Asignatura.setEditable(false);
        jT_Asignatura.setFocusable(false);

        jT_Anio.setEditable(false);
        jT_Anio.setFocusable(false);

        jLabel5.setText("Año:");

        jLabel6.setText("Cuatrimestre:");

        jT_Cuatrimestre.setEditable(false);
        jT_Cuatrimestre.setFocusable(false);

        jLabel7.setText("Listado de Alumnos:");

        jLista_Examenes.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jLista_Examenes.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jLista_ExamenesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jLista_Examenes);

        jLabel18.setText("Fecha:");

        jT_Fecha.setEditable(false);
        jT_Fecha.setFocusable(false);

        jLabel19.setText("Descripcion:");

        jT_Descripcion.setEditable(false);
        jT_Descripcion.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jT_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jT_Identificador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jT_Asignatura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jT_Anio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jT_Cuatrimestre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jT_Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jT_Identificador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jT_Asignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jT_Anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jT_Cuatrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jT_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jT_Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Un Examen"));

        jLabel10.setText("Alumno:");

        jT_Alumno.setEditable(false);
        jT_Alumno.setFocusable(false);

        jLabel11.setText("Árbol de Perturbación");

        jT_id_arbol_perturbacion.setText("<Nombre>");

        jT_descripcion_arbol_perturbacion.setText("<Descripción>");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        jLabel13.setText("Desconocido:");

        jTextDesconocido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextDesconocido.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jTextDesconocidoKeyReleased(evt);
            }
        });

        jTextParcialmente.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextParcialmente.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jTextParcialmenteKeyReleased(evt);
            }
        });

        jLabel14.setText("Parcialmente Conocido:");

        jTextConocido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextConocido.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jTextConocidoKeyReleased(evt);
            }
        });

        jLabel15.setText("Conocido:");

        jTextAprendido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextAprendido.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jTextAprendidoKeyReleased(evt);
            }
        });

        jLabel16.setText("Aprendido:");

        jBAceptar.setText("Aceptar");
        jBAceptar.setEnabled(false);
        jBAceptar.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jBAceptarMouseClicked(evt);
            }
        });
        jBAceptar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextConocido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(jTextParcialmente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextDesconocido, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextAprendido)))
                    .addComponent(jBAceptar))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextDesconocido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextParcialmente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextConocido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jTextAprendido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBAceptar)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado"));

        jScrollPane4.setViewportView(jText_estado_correccion);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(2, 2, 2)
                                .addComponent(jT_Alumno, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11)
                            .addComponent(jT_id_arbol_perturbacion)
                            .addComponent(jT_descripcion_arbol_perturbacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane_arbol, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jT_Alumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jT_id_arbol_perturbacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jT_descripcion_arbol_perturbacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane_arbol, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jB_Salir.setText("Salir");

        jB_Guardar.setText("Guardar");
        jB_Guardar.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jB_GuardarMouseClicked(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Evaluaciones"));

        jComboBox_Asignatura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_Asignatura.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBox_AsignaturaActionPerformed(evt);
            }
        });

        jLabel2.setText("Asignatura:");

        jLabel8.setText("Cursada:");

        jComboBox_Cursada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_Cursada.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBox_CursadaActionPerformed(evt);
            }
        });

        jComboBox_Inst_Evaluacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_Inst_Evaluacion.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBox_Inst_EvaluacionActionPerformed(evt);
            }
        });

        jLabel9.setText("Inst. Evaluación:");

        jLabel12.setText("Parcial:");

        jComboBox_Parcial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_Parcial.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBox_ParcialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox_Asignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox_Cursada, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox_Parcial, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_Inst_Evaluacion, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox_Asignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_Cursada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_Parcial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_Inst_Evaluacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jB_Guardar)
                        .addGap(18, 18, 18)
                        .addComponent(jB_Salir)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jB_Salir)
                            .addComponent(jB_Guardar)))))
        );

        pack();
    }//GEN-END:initComponents

    private void jTextDesconocidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDesconocidoKeyReleased
        // TODO add your handling code here:
        this.jBAceptar.setEnabled(this.nodo_seleccionado != null && this.construyeEtiqueta().isValid());
        
    }//GEN-LAST:event_jTextDesconocidoKeyReleased

    private void jTextParcialmenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextParcialmenteKeyReleased
        // TODO add your handling code here:
        this.jBAceptar.setEnabled(this.nodo_seleccionado != null && this.construyeEtiqueta().isValid());
        
    }//GEN-LAST:event_jTextParcialmenteKeyReleased

    private void jTextConocidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextConocidoKeyReleased
        // TODO add your handling code here:
        this.jBAceptar.setEnabled(this.nodo_seleccionado != null && this.construyeEtiqueta().isValid());
        
    }//GEN-LAST:event_jTextConocidoKeyReleased

    private void jTextAprendidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAprendidoKeyReleased
        // TODO add your handling code here:
        this.jBAceptar.setEnabled(this.nodo_seleccionado != null && this.construyeEtiqueta().isValid());
        
    }//GEN-LAST:event_jTextAprendidoKeyReleased

    private void jB_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jB_GuardarMouseClicked
        // TODO add your handling code here:
        Instancia_Evaluacion ev;
        ArrayList<Examen> listadeexamenes;
        Examen ex;
        /*    for (int i = 0; i < this.jLista_Evaluaciones.getModel().getSize(); i++)
        {
            ev = (Instancia_Evaluacion) this.jLista_Evaluaciones.getModel().getElementAt(i);
            listadeexamenes = ev.getExamenes();
            for (int j = 0; j < listadeexamenes.size(); j++)
            {
                ex = listadeexamenes.get(j);
                if (ex.isModificado())
                {
                    try
                    {
                        ex.getArbol_podado_particular().procesar();
                    }
                    catch (Exception e)
                    {JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                    //OJO, MIRAR QUE VA ACA
                   // modelo.getModelo_abm_evaluacion().actualizar_examen(ev, ex);
                    ex.setModificado(false);
                }
            }
        }*/
    }//GEN-LAST:event_jB_GuardarMouseClicked

    private void jBAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBAceptarActionPerformed

    private void jBAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBAceptarMouseClicked
        this.actualizaEtiqueta();
    }//GEN-LAST:event_jBAceptarMouseClicked

    private void jLista_ExamenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLista_ExamenesMouseClicked

        // TODO add your handling code here:
        this.examen_seleccionado = (Examen) this.jLista_Examenes.getSelectedValue();
        if (this.examen_seleccionado != null && this.jLista_Examenes.isEnabled())
        {
            this.jTree_Arbol_Perturbacion.setModel(null);
            this.jTree_Arbol_Perturbacion.setModel(this.examen_seleccionado.getArbol_podado_particular().getTreeModel());
            this.jTree_Arbol_Perturbacion.paintAll(this.jTree_Arbol_Perturbacion.getGraphics());
            this.jT_Alumno.setText(this.examen_seleccionado.getAlumno().toString());
            this.nodo_seleccionado = null;
            jT_id_arbol_perturbacion.setText(this.examen_seleccionado.getArbol_podado_particular().getNombre());
            jT_descripcion_arbol_perturbacion.setText(this.examen_seleccionado.getArbol_podado_particular().getDescripcion());
        }
    }//GEN-LAST:event_jLista_ExamenesMouseClicked

    private void jComboBox_AsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_AsignaturaActionPerformed
        
        if (this.jComboBox_Asignatura.getSelectedItem() != null)
        {
            this.asignatura_seleccionada = (Asignatura) this.jComboBox_Asignatura.getSelectedItem();
            this.cursada_seleccionada = null;
            this.parcial_seleccionado = null;
            this.instancia_seleccionada = null;
            this.examen_seleccionado = null;
            this.actualizar_combo_cursadas();

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_AsignaturaActionPerformed

    private void jComboBox_CursadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_CursadaActionPerformed

    if (this.jComboBox_Cursada.getSelectedItem() != null)
    {
        this.cursada_seleccionada = (Cursada) this.jComboBox_Cursada.getSelectedItem();
        this.parcial_seleccionado = null;
        this.instancia_seleccionada = null;
        this.examen_seleccionado = null;
        this.actualizar_combo_parciales();

    }

        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_CursadaActionPerformed

    private void jComboBox_ParcialActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBox_ParcialActionPerformed
    {//GEN-HEADEREND:event_jComboBox_ParcialActionPerformed
       
       this.parcial_seleccionado = (Parcial) this.jComboBox_Parcial.getSelectedItem();
        this.instancia_seleccionada = null;
        this.examen_seleccionado = null;
        this.actualizar_combo_instancias();
    }//GEN-LAST:event_jComboBox_ParcialActionPerformed

    private void jComboBox_Inst_EvaluacionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBox_Inst_EvaluacionActionPerformed
    {//GEN-HEADEREND:event_jComboBox_Inst_EvaluacionActionPerformed

    this.instancia_seleccionada = (Instancia_Evaluacion) this.jComboBox_Inst_Evaluacion.getSelectedItem();
    this.examen_seleccionado = null;
    // actualiza la lista de examenes que no exsite this.actualizar_combo_instancias();
//despues borra esto
    if(this.instancia_seleccionada!=null){
    System.out.println(this.instancia_seleccionada);
    for(int i=0;i<this.instancia_seleccionada.getAlumnos_evaluados().size();i++)
    System.out.println(this.instancia_seleccionada.getAlumnos_evaluados().get(i));
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_Inst_EvaluacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAceptar;
    private javax.swing.JButton jB_Guardar;
    private javax.swing.JButton jB_Salir;
    private javax.swing.JComboBox<String> jComboBox_Asignatura;
    private javax.swing.JComboBox<String> jComboBox_Cursada;
    private javax.swing.JComboBox<String> jComboBox_Inst_Evaluacion;
    private javax.swing.JComboBox<String> jComboBox_Parcial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jLista_Examenes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane_arbol;
    private javax.swing.JTextField jT_Alumno;
    private javax.swing.JTextField jT_Anio;
    private javax.swing.JTextField jT_Asignatura;
    private javax.swing.JTextField jT_Cuatrimestre;
    private javax.swing.JTextField jT_Descripcion;
    private javax.swing.JTextField jT_Fecha;
    private javax.swing.JTextField jT_Identificador;
    private javax.swing.JTextField jT_descripcion_arbol_perturbacion;
    private javax.swing.JTextField jT_id_arbol_perturbacion;
    private javax.swing.JTextField jTextAprendido;
    private javax.swing.JTextField jTextConocido;
    private javax.swing.JTextField jTextDesconocido;
    private javax.swing.JTextField jTextParcialmente;
    private javax.swing.JTextPane jText_estado_correccion;
    // End of variables declaration//GEN-END:variables

    private void jtree_arbolMouseClicked() {
        nodo_seleccionado = (Nodo_Perturbacion) jTree_Arbol_Perturbacion.getLastSelectedPathComponent();
        if (nodo_seleccionado != null && this.jTree_Arbol_Perturbacion.isEnabled())
        {
            EtiquetaBean etiqueta;
            etiqueta = nodo_seleccionado.getDato().getEtiquetaBean();

            if (nodo_seleccionado.esHoja())
            {
                this.setModoEdicion(true);
                jTextDesconocido.setText(String.valueOf(etiqueta.getDesconocido()));
                jTextParcialmente.setText(String.valueOf(etiqueta.getParcialmenteConocido()));
                jTextConocido.setText(String.valueOf(etiqueta.getConocido()));
                jTextAprendido.setText(String.valueOf(etiqueta.getAprendido()));
            } else
            {
                this.setModoEdicion(false);
                if (etiqueta.isCero())
                {
                    jTextDesconocido.setText("");
                    jTextParcialmente.setText("");
                    jTextConocido.setText("");
                    jTextAprendido.setText("");
                } else
                {
                    jTextDesconocido.setText(String.valueOf(etiqueta.getDesconocido()));
                    jTextParcialmente.setText(String.valueOf(etiqueta.getParcialmenteConocido()));
                    jTextConocido.setText(String.valueOf(etiqueta.getConocido()));
                    jTextAprendido.setText(String.valueOf(etiqueta.getAprendido()));

                }
            }

        }

    }

    private void setModoEdicion(boolean param) {
        jTextDesconocido.setEditable(param);
        jTextParcialmente.setEditable(param);
        jTextConocido.setEditable(param);
        jTextAprendido.setEditable(param);
    }


    private void actualizaEtiqueta() {
        this.nodo_seleccionado.getDato().setEtiquetaBean(this.construyeEtiqueta());
        this.examen_seleccionado.setModificado(true);
    }

    private EtiquetaBean construyeEtiqueta() {
        double aprendido = 0, conocido = 0, parcialmente = 0, desconocido = 0;
        EtiquetaBean etiqueta = new EtiquetaBean();
        try
        {
            aprendido = Double.parseDouble(jTextAprendido.getText());
            conocido = Double.parseDouble(jTextConocido.getText());
            desconocido = Double.parseDouble(jTextDesconocido.getText());
            parcialmente = Double.parseDouble(jTextParcialmente.getText());

            etiqueta.setAprendido(aprendido);
            etiqueta.setConocido(conocido);
            etiqueta.setDesconocido(desconocido);
            etiqueta.setParcialmenteConocido(parcialmente);
        } catch (Exception e)
        {
            etiqueta.inicializar();


        }


        return etiqueta;

    }

    public void limpiar_zona_unaPrueba() {
        jT_Alumno.setText("");
        jT_id_arbol_perturbacion.setText("");
        jT_descripcion_arbol_perturbacion.setText("");
        this.jTree_Arbol_Perturbacion.setModel(null);
        jTextDesconocido.setText("");
        jTextParcialmente.setText("");
        jTextConocido.setText("");
        jTextAprendido.setText("");
        jText_estado_correccion.setText("");
    }
}
