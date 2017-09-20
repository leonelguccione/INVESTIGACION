
package UI;

import Excepciones.NotSemejanteException;


import arbol_perturbacion_visual.AEvaluableVisual;
import arbol_perturbacion_visual.ArbolPerturbacionVisual;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import modelo.ArbolPerturbacion;

import modelo.Asignatura;
import modelo.Cursada;
import modelo.Examen;
import modelo.Instancia_Evaluacion;
import modelo.Modelo;
import modelo.NodoPerturbacion;

import modelo.Parcial;

/**
 *
 * @author Raquel
 */
public class UI_Promedio extends javax.swing.JInternalFrame
{

    private Modelo modelo;

    private DefaultComboBoxModel<Asignatura> comboBoxModelAsignatura = new DefaultComboBoxModel<Asignatura>();
    private DefaultComboBoxModel<Cursada> comboBoxModelCursada = new DefaultComboBoxModel<Cursada>();
    private DefaultComboBoxModel<Parcial> comboBoxModelParciales = new DefaultComboBoxModel<Parcial>();
    private DefaultComboBoxModel<Instancia_Evaluacion> comboBoxModelInstEvaluacion =
        new DefaultComboBoxModel<Instancia_Evaluacion>();
    private DefaultListModel listModelexamenes = new DefaultListModel();
    private DefaultListModel listModelexamenespromediadios = new DefaultListModel();

    private NodoPerturbacion nodo_seleccionado = null;
    private Asignatura asignatura_seleccionada = null;
    private Cursada cursada_seleccionada = null;
    private Parcial parcial_seleccionado = null;
    private Instancia_Evaluacion instancia_seleccionada = null;
    private Examen examen_seleccionado = null;
    private ArbolPerturbacionVisual jTree_Arbol_Perturbacion = null;
    private ArbolPerturbacion arbol_promedio = null;


    /** Creates new form UI_Correccion */
    public UI_Promedio(Modelo modelo)
    {
        initComponents();
        this.jTree_Arbol_Perturbacion = (ArbolPerturbacionVisual) this.jScrollPane_arbol;
        this.jTree_Arbol_Perturbacion.setLineasRectas(true);
        this.jTree_Arbol_Perturbacion.setMuestraNodosOcultos(true);
        
        this.modelo = modelo;

        this.jComboBox_Asignatura.setModel((ComboBoxModel) comboBoxModelAsignatura);
        this.jComboBox_Cursada.setModel((ComboBoxModel) this.comboBoxModelCursada);
        this.jComboBox_Parcial.setModel((ComboBoxModel) this.comboBoxModelParciales);
        this.jComboBox_Inst_Evaluacion.setModel((ComboBoxModel) this.comboBoxModelInstEvaluacion);
        this.jLista_Examenes.setModel(listModelexamenes);
        this.actualizar_combo_asignatura();


    }

    private void actualizar_combo_asignatura()
    {
        verificar_enabled();
        this.comboBoxModelAsignatura.removeAllElements();
        Iterator iterator_asignaturas = modelo.getAsignaturas()
                                              .values()
                                              .iterator();
        //Recorrer el contenido del Iterator
        while (iterator_asignaturas.hasNext())
        {
            Asignatura asignatura = (Asignatura) iterator_asignaturas.next();
            this.comboBoxModelAsignatura.addElement(asignatura);
        }

    }


    private void actualizar_combo_cursadas()
    {

        this.comboBoxModelCursada.removeAllElements();
        ArrayList<Cursada> cursadas;
        if (this.asignatura_seleccionada != null)

        {
            verificar_enabled();
            cursadas = this.asignatura_seleccionada.getCursadas();
            for (int i = 0; i < cursadas.size(); i++)
                this.comboBoxModelCursada.addElement(cursadas.get(i));

        }
    }


    private void actualizar_combo_parciales()
    {

        this.comboBoxModelParciales.removeAllElements();
        ArrayList<Parcial> parciales;
        if (this.cursada_seleccionada != null)
        {
            verificar_enabled();
            parciales = this.cursada_seleccionada.getParciales();
            for (int i = 0; i < parciales.size(); i++)
                this.comboBoxModelParciales.addElement(parciales.get(i));

        }
    }


    private void actualizar_combo_instancias()
    {

        this.comboBoxModelInstEvaluacion.removeAllElements();
        ArrayList<Instancia_Evaluacion> instancias;
        if (this.parcial_seleccionado != null)
        {
            verificar_enabled();
            instancias = this.parcial_seleccionado.getInstancias_evaluaciones();
            for (int i = 0; i < instancias.size(); i++)
                this.comboBoxModelInstEvaluacion.addElement(instancias.get(i));
        }
    }


    private void actualizar_lista_examenes()
    {
        this.listModelexamenes.clear();

        ArrayList<Examen> examenes;
        if (this.instancia_seleccionada != null)
        {
            verificar_enabled();
            examenes = this.instancia_seleccionada.getExamenes();
            for (int i = 0; i < examenes.size(); i++)
                this.listModelexamenes.addElement(examenes.get(i));

        }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents()//GEN-BEGIN:initComponents
    {

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
        jButton_Promedio = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane_arbol = new AEvaluableVisual();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList_examenes_promediados = new javax.swing.JList();

        setClosable(true);

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

        jButton_Promedio.setText("Promedio");
        jButton_Promedio.setEnabled(false);
        jButton_Promedio.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_PromedioActionPerformed(evt);
            }
        });

        jButton1.setText("Ver Arbol");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_Promedio, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
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
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton_Promedio)
                        .addGap(50, 50, 50)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jList_examenes_promediados.setBorder(javax.swing.BorderFactory.createTitledBorder("Examenes promediados"));
        jList_examenes_promediados.setModel(this.listModelexamenespromediadios);
        jScrollPane1.setViewportView(jList_examenes_promediados);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane_arbol, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane_arbol, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }//GEN-END:initComponents

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

    private void jComboBox_Inst_EvaluacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Inst_EvaluacionActionPerformed

        this.instancia_seleccionada = (Instancia_Evaluacion) this.jComboBox_Inst_Evaluacion.getSelectedItem();
        this.examen_seleccionado = null;
        this.actualizar_lista_examenes();

        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_Inst_EvaluacionActionPerformed

    private void jComboBox_ParcialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_ParcialActionPerformed

        this.parcial_seleccionado = (Parcial) this.jComboBox_Parcial.getSelectedItem();
        this.instancia_seleccionada = null;
        this.examen_seleccionado = null;
        this.actualizar_combo_instancias();
    }//GEN-LAST:event_jComboBox_ParcialActionPerformed

    private void jButton_PromedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PromedioActionPerformed
        String titulo="Alumnos promediados: ";
        List<Examen> seleccionados = this.jLista_Examenes.getSelectedValuesList();
        ArbolPerturbacion arbol_actual;
        ArrayList<ArbolPerturbacion> arboles = new ArrayList<ArbolPerturbacion>();
        this.listModelexamenespromediadios.clear();
        this.jTree_Arbol_Perturbacion.setModel(null);
        for (int i = 0; i < seleccionados.size(); i++)
        {
            arbol_actual = seleccionados.get(i).getArbol_podado_particular();
            if (arbol_actual.isCorregido())
            {
                arboles.add(arbol_actual);
                this.listModelexamenespromediadios.addElement(seleccionados.get(i));
                titulo=titulo+" - "+seleccionados.get(i).getAlumno().getApellido();

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
                arbol_promedio = ArbolPerturbacion.promedio(arboles);
                this.arbol_promedio.setNombre(titulo);
                this.jTree_Arbol_Perturbacion.setModel(arbol_promedio.getTreeModel());
                this.jTree_Arbol_Perturbacion.repaint();

            } catch (ArithmeticException e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (NotSemejanteException e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            





    }//GEN-LAST:event_jButton_PromedioActionPerformed

    private void jLista_ExamenesValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_jLista_ExamenesValueChanged
    {//GEN-HEADEREND:event_jLista_ExamenesValueChanged
        this.verificar_enabled();     
    
    }//GEN-LAST:event_jLista_ExamenesValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed

        if (this.arbol_promedio != null)
        {
            UI_Arbol_Evaluable ui_arbol =

                new UI_Arbol_Evaluable(this.arbol_promedio);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_Promedio;
    private javax.swing.JComboBox<Asignatura> jComboBox_Asignatura;
    private javax.swing.JComboBox<Cursada> jComboBox_Cursada;
    private javax.swing.JComboBox<Instancia_Evaluacion> jComboBox_Inst_Evaluacion;
    private javax.swing.JComboBox<Parcial> jComboBox_Parcial;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList_examenes_promediados;
    private javax.swing.JList jLista_Examenes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane_arbol;
    // End of variables declaration//GEN-END:variables


    private void verificar_enabled()
    {
        boolean habilitado;
        habilitado = this.parcial_seleccionado != null && this.jLista_Examenes
                                                              .getSelectedValuesList()
                                                              .size() > 1;

        this.jButton_Promedio.setEnabled(habilitado);
    }

}
