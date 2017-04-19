
package UI;

import Excepciones.NoCompletoException;
import Excepciones.RaizNulaException;

import arbol_perturbacion_visual.ArbolPerturbacionVisual;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

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
    private ArbolPerturbacionVisual jTree_Arbol_Perturbacion = null;
    private MouseAdapter mouseA;

    /** Creates new form UI_Correccion */
    public UI_Correccion(Modelo modelo) {
        initComponents();
        jTree_Arbol_Perturbacion = (ArbolPerturbacionVisual) this.jScrollPane_arbol;
        this.modelo = modelo;

        this.jComboBox_Asignatura.setModel((ComboBoxModel) comboBoxModelAsignatura);
        this.jComboBox_Cursada.setModel((ComboBoxModel) this.comboBoxModelCursada);
        this.jComboBox_Parcial.setModel((ComboBoxModel) this.comboBoxModelParciales);
        this.jComboBox_Inst_Evaluacion.setModel((ComboBoxModel) this.comboBoxModelInstEvaluacion);
        this.jLista_Examenes.setModel(listModelexamenes);
        this.actualizar_combo_asignatura();

        this.mouseA = new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                jtree_arbolMouseClicked();
            }
        };
        this.jTree_Arbol_Perturbacion.addMouseListener(mouseA);
        this.jTree_Arbol_Perturbacion.setLineasRectas(true);
    }

    private void actualizar_combo_asignatura() {
        limpiar_zona_correccion();
        this.comboBoxModelAsignatura.removeAllElements();
        Iterator iterator_asignaturas = modelo.getAsignaturas()
                                              .values()
                                              .iterator();
        //Recorrer el contenido del Iterator
        while (iterator_asignaturas.hasNext()) {
            Asignatura asignatura = (Asignatura) iterator_asignaturas.next();
            this.comboBoxModelAsignatura.addElement(asignatura);
        }

    }

    private void actualizar_combo_cursadas() {

        this.comboBoxModelCursada.removeAllElements();
        ArrayList<Cursada> cursadas;
        if (this.asignatura_seleccionada != null)

        {
            limpiar_zona_correccion();
            cursadas = this.asignatura_seleccionada.getCursadas();
            for (int i = 0; i < cursadas.size(); i++)
                this.comboBoxModelCursada.addElement(cursadas.get(i));

        }
    }


    private void actualizar_combo_parciales() {

        this.comboBoxModelParciales.removeAllElements();
        ArrayList<Parcial> parciales;
        if (this.cursada_seleccionada != null) {
            limpiar_zona_correccion();
            parciales = this.cursada_seleccionada.getParciales();
            for (int i = 0; i < parciales.size(); i++)
                this.comboBoxModelParciales.addElement(parciales.get(i));

        }
    }


    private void actualizar_combo_instancias() {

        this.comboBoxModelInstEvaluacion.removeAllElements();
        ArrayList<Instancia_Evaluacion> instancias;
        if (this.parcial_seleccionado != null) {
            limpiar_zona_correccion();
            instancias = this.parcial_seleccionado.getInstancias_evaluaciones();
            for (int i = 0; i < instancias.size(); i++)
                this.comboBoxModelInstEvaluacion.addElement(instancias.get(i));
        }
    }


    private void actualizar_lista_examenes() {
        this.listModelexamenes.clear();

        ArrayList<Examen> examenes;
        if (this.instancia_seleccionada != null) {
            limpiar_zona_correccion();
            examenes = this.instancia_seleccionada.getExamenes();
            for (int i = 0; i < examenes.size(); i++)
                this.listModelexamenes.addElement(examenes.get(i));

        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents()//GEN-BEGIN:initComponents
    {

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
        jB_Guardar = new javax.swing.JButton();
        jScrollPane_arbol = new ArbolPerturbacionVisual();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel_porcentaje_corregido = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jComboBox_Asignatura = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox_Cursada = new javax.swing.JComboBox<>();
        jComboBox_Inst_Evaluacion = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox_Parcial = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLista_Examenes = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(930, 630));

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
                jTextKeyRelease(evt);
            }
        });

        jTextParcialmente.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextParcialmente.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jTextKeyRelease(evt);
            }
        });

        jLabel14.setText("Parcialmente Conocido:");

        jTextConocido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextConocido.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jTextKeyRelease(evt);
            }
        });

        jLabel15.setText("Conocido:");

        jTextAprendido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextAprendido.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jTextKeyRelease(evt);
            }
        });

        jLabel16.setText("Aprendido:");

        jBAceptar.setText("Aceptar");
        jBAceptar.setEnabled(false);
        jBAceptar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBAceptarActionPerformed(evt);
            }
        });

        jB_Guardar.setText("Guardar");
        jB_Guardar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jB_GuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jBAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jB_Guardar)))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBAceptar)
                    .addComponent(jB_Guardar))
                .addContainerGap())
        );

        jCheckBox1.setText("Modificado");
        jCheckBox1.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane_arbol, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(2, 2, 2)
                                .addComponent(jT_Alumno, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11)
                            .addComponent(jT_id_arbol_perturbacion)
                            .addComponent(jT_descripcion_arbol_perturbacion))
                        .addGap(69, 69, 69)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(jLabel_porcentaje_corregido))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jT_Alumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jT_id_arbol_perturbacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_porcentaje_corregido))
                .addGap(18, 18, 18)
                .addComponent(jT_descripcion_arbol_perturbacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane_arbol, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Evaluaciones"));

        jComboBox_Asignatura.setModel(this.comboBoxModelAsignatura);
        jComboBox_Asignatura.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBox_AsignaturaActionPerformed(evt);
            }
        });

        jLabel2.setText("Asignatura:");

        jLabel8.setText("Cursada:");

        jComboBox_Cursada.setModel(this.comboBoxModelCursada);
        jComboBox_Cursada.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBox_CursadaActionPerformed(evt);
            }
        });

        jComboBox_Inst_Evaluacion.setModel(this.comboBoxModelInstEvaluacion);
        jComboBox_Inst_Evaluacion.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBox_Inst_EvaluacionActionPerformed(evt);
            }
        });

        jLabel9.setText("Inst. Evaluación:");

        jLabel12.setText("Parcial:");

        jComboBox_Parcial.setModel(this.comboBoxModelParciales);
        jComboBox_Parcial.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBox_ParcialActionPerformed(evt);
            }
        });

        jLista_Examenes.setModel(this.listModelexamenes);
        jLista_Examenes.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                jLista_ExamenesValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jLista_Examenes);

        jLabel7.setText("Listado de Alumnos:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }//GEN-END:initComponents

    private void jTextKeyRelease(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextKeyRelease
        // TODO add your handling code here:
        this.jBAceptar.setEnabled(this.nodo_seleccionado != null && this.construyeEtiqueta().isValid());
        
    }//GEN-LAST:event_jTextKeyRelease



   
    private void jBAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAceptarActionPerformed
        this.actualizaEtiqueta();
        this.actualiza_jtree();
    }//GEN-LAST:event_jBAceptarActionPerformed

    private void jComboBox_AsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_AsignaturaActionPerformed
        
        if (this.jComboBox_Asignatura.getSelectedItem() != null) {
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

        if (this.jComboBox_Cursada.getSelectedItem() != null) {
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
        this.actualizar_lista_examenes();


        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_Inst_EvaluacionActionPerformed

    private void jB_GuardarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jB_GuardarActionPerformed
    {//GEN-HEADEREND:event_jB_GuardarActionPerformed
    Examen ex = this.examen_seleccionado;
    if (ex != null && ex.isModificado()) {

        try {
            ex.getArbol_podado_particular().procesar();
        } catch (NoCompletoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        this.actualiza_jtree();

        try {
            modelo.getModelo_abm_evaluacion().actualizar_examen(ex);
            ex.setModificado(false);
            this.verifica_modificado();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }
       
    }//GEN-LAST:event_jB_GuardarActionPerformed

    private void jLista_ExamenesValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_jLista_ExamenesValueChanged
    {//GEN-HEADEREND:event_jLista_ExamenesValueChanged
    this.examen_seleccionado = (Examen) this.jLista_Examenes.getSelectedValue();
    if (this.examen_seleccionado != null && this.jLista_Examenes.isEnabled()) {
        limpiar_zona_correccion();
        this.jTree_Arbol_Perturbacion.setModel(this.examen_seleccionado
                                                   .getArbol_podado_particular()
                                                   .getTreeModel());
        this.actualiza_jtree();
        this.jT_Alumno.setText(this.examen_seleccionado
                                   .getAlumno()
                                   .toString());
        this.nodo_seleccionado = null;
        jT_id_arbol_perturbacion.setText(this.examen_seleccionado
                                             .getArbol_podado_particular()
                                             .getNombre());
        jT_descripcion_arbol_perturbacion.setText(this.examen_seleccionado
                                                      .getArbol_podado_particular()
                                                      .getDescripcion());
        this.verifica_modificado();
    }
    }//GEN-LAST:event_jLista_ExamenesValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAceptar;
    private javax.swing.JButton jB_Guardar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<Asignatura> jComboBox_Asignatura;
    private javax.swing.JComboBox<Cursada> jComboBox_Cursada;
    private javax.swing.JComboBox<Instancia_Evaluacion> jComboBox_Inst_Evaluacion;
    private javax.swing.JComboBox<Parcial> jComboBox_Parcial;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_porcentaje_corregido;
    private javax.swing.JList jLista_Examenes;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane_arbol;
    private javax.swing.JTextField jT_Alumno;
    private javax.swing.JTextField jT_descripcion_arbol_perturbacion;
    private javax.swing.JTextField jT_id_arbol_perturbacion;
    private javax.swing.JTextField jTextAprendido;
    private javax.swing.JTextField jTextConocido;
    private javax.swing.JTextField jTextDesconocido;
    private javax.swing.JTextField jTextParcialmente;
    // End of variables declaration//GEN-END:variables

    private void jtree_arbolMouseClicked() {
        nodo_seleccionado = (Nodo_Perturbacion) jTree_Arbol_Perturbacion.getLastSelectedPathComponent();
        if (nodo_seleccionado != null && this.jTree_Arbol_Perturbacion.isEnabled()) {
            EtiquetaBean etiqueta;
            etiqueta = nodo_seleccionado.getDato().getEtiquetaBean();

            if (nodo_seleccionado.esHoja()) {
                this.setModoEdicion(true);
                jTextDesconocido.setText(String.valueOf(etiqueta.getDesconocido()));
                jTextParcialmente.setText(String.valueOf(etiqueta.getParcialmenteConocido()));
                jTextConocido.setText(String.valueOf(etiqueta.getConocido()));
                jTextAprendido.setText(String.valueOf(etiqueta.getAprendido()));
            } else {
                this.setModoEdicion(false);
                if (etiqueta.isCero()) {
                    jTextDesconocido.setText("");
                    jTextParcialmente.setText("");
                    jTextConocido.setText("");
                    jTextAprendido.setText("");
                } else {
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
        this.nodo_seleccionado
            .getDato()
            .setEtiquetaBean(this.construyeEtiqueta());
        this.examen_seleccionado.setModificado(true);
        this.verifica_modificado();

    }

    private EtiquetaBean construyeEtiqueta() {
        double aprendido = 0, conocido = 0, parcialmente = 0, desconocido = 0;
        EtiquetaBean etiqueta = new EtiquetaBean();
        try {
            aprendido = Double.parseDouble(jTextAprendido.getText());
            conocido = Double.parseDouble(jTextConocido.getText());
            desconocido = Double.parseDouble(jTextDesconocido.getText());
            parcialmente = Double.parseDouble(jTextParcialmente.getText());

            etiqueta.setAprendido(aprendido);
            etiqueta.setConocido(conocido);
            etiqueta.setDesconocido(desconocido);
            etiqueta.setParcialmenteConocido(parcialmente);
        } catch (Exception e) {
            etiqueta.inicializar();
        }
        return etiqueta;
    }

    private void limpiar_zona_correccion() {
        jT_Alumno.setText("");
        jT_id_arbol_perturbacion.setText("");
        jT_descripcion_arbol_perturbacion.setText("");
        this.jTree_Arbol_Perturbacion.setModel(null);
        jTextDesconocido.setText("");
        jTextParcialmente.setText("");
        jTextConocido.setText("");
        jTextAprendido.setText("");
        this.verifica_modificado();
        this.setModoEdicion(false);
        this.jBAceptar.setEnabled(false);
    }

    private void verifica_modificado() {
        if (this.examen_seleccionado != null) {
            this.jCheckBox1.setSelected(this.examen_seleccionado.isModificado());
            this.jB_Guardar.setEnabled(this.examen_seleccionado.isModificado());
        } else {
            this.jCheckBox1.setSelected(false);
            this.jB_Guardar.setEnabled(false);
        }
    }
    private void actualiza_jtree()
    {
        this.jTree_Arbol_Perturbacion.repaint();
        if(this.examen_seleccionado!=null&&this.examen_seleccionado.getArbol_podado_particular()!=null)
            try {
                this.jLabel_porcentaje_corregido.setText("Porcentaje de correccion: " +
                                                         String.format("%.2f",
                                                                       this.examen_seleccionado
                                                                                                                  .getArbol_podado_particular()
                                                                                                                  .getPorcentajeCorreccion()));
            } catch (RaizNulaException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
    }
}
