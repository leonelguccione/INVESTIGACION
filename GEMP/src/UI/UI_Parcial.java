
package UI;


import arbol_perturbacion_visual.AEvaluableVisual;
import arbol_perturbacion_visual.ArbolPerturbacionVisual;


import arbolvisual.ArbolVisual;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultTreeModel;

import modelo.ArbolPerturbacion;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Modelo;
import modelo.NodoPerturbacionEvaluable;
import modelo.Parcial;

/**
 *
 * @author guille
 */
public class UI_Parcial extends javax.swing.JInternalFrame
{
    private Modelo modelo;
    private AEvaluableVisual jtree_arbol;
    private ArbolPerturbacion arbolPodado;

    private DefaultListModel listModel_cursadas = new DefaultListModel();
    private DefaultListModel listModel_asignaturas = new DefaultListModel();
    private DefaultListModel listModel_parciales = new DefaultListModel();

    private Asignatura asignatura_en_uso = null;
    private Cursada cursada_en_uso = null;
    private boolean modo_edicion = false;

    /** Creates new form UI_Parcial */
    public UI_Parcial(Modelo m)
    {

        this.modelo = m;
        initComponents();
        this.jList_asignaturas.setModel(listModel_asignaturas);
        this.jList_cursadas.setModel(listModel_cursadas);


        this.jList_parciales.setModel(listModel_parciales);
        this.actualizar_jList_asignaturas();
        this.jtree_arbol = (AEvaluableVisual) this.jScrollPane_jTreeVisual;
        this.jButton_ocultar_nodo.setEnabled(false);

        this.jtree_arbol.setLineasRectas(true);
        this.jtree_arbol.addMouseListener(new MouseAdapter()
                                          
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                UI_Parcial.this.jButton_ocultar_nodo.setEnabled(UI_Parcial.this.jtree_arbol.getNodoSeleccionado() !=
                                                                 null && UI_Parcial.this.cursada_en_uso != null);
            }
        });
        this.jtree_arbol.setMuestraNodosOcultos(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents()//GEN-BEGIN:initComponents
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane_jTreeVisual = new AEvaluableVisual();
        jButton_guardar_ap = new javax.swing.JButton();
        jButton_ocultar_nodo = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jButton_Agregar_nuevo = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jText_Asignatura = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jText_Nombre = new javax.swing.JTextField();
        jText_Nombre_Cursada = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList_asignaturas = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList_cursadas = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList_parciales = new javax.swing.JList();
        jButtonCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButtonVerArbol = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        setClosable(true);
        setTitle("Gestión de Parciales");
        setNormalBounds(new java.awt.Rectangle(0, 0, 950, 700));
        setPreferredSize(new java.awt.Dimension(950, 700));

        jScrollPane_jTreeVisual.setBorder(javax.swing.BorderFactory.createTitledBorder("Árbol Evaluado"));

        jButton_guardar_ap.setText("Guardar Parcial");
        jButton_guardar_ap.setEnabled(false);
        jButton_guardar_ap.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_guardar_apActionPerformed(evt);
            }
        });

        jButton_ocultar_nodo.setText("Ocultar Nodo");
        jButton_ocultar_nodo.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_ocultar_nodoActionPerformed(evt);
            }
        });

        jPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel15.setLayout(new java.awt.GridBagLayout());

        jButton_Agregar_nuevo.setText("Agregar nuevo parcial");
        jButton_Agregar_nuevo.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_Agregar_nuevoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel15.add(jButton_Agregar_nuevo, gridBagConstraints);

        jLabel41.setText("Asig.:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jLabel41, gridBagConstraints);

        jText_Asignatura.setEditable(false);
        jText_Asignatura.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 172;
        jPanel15.add(jText_Asignatura, gridBagConstraints);

        jLabel44.setText("Cursada:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jLabel44, gridBagConstraints);

        jText_Nombre.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 172;
        jPanel15.add(jText_Nombre, gridBagConstraints);

        jText_Nombre_Cursada.setEditable(false);
        jText_Nombre_Cursada.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 172;
        jPanel15.add(jText_Nombre_Cursada, gridBagConstraints);

        jLabel45.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel15.add(jLabel45, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jList_asignaturas.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Asignaturas"));
        jList_asignaturas.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList_asignaturas.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                jList_asignaturasValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList_asignaturas);

        jPanel1.add(jScrollPane2);

        jList_cursadas.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Cursadas"));
        jList_cursadas.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList_cursadas.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                jList_cursadasValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jList_cursadas);

        jPanel1.add(jScrollPane3);

        jList_parciales.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Parciales"));
        jList_parciales.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList_parciales.setFocusable(false);
        jList_parciales.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                jList_parcialesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList_parciales);

        jPanel1.add(jScrollPane1);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setEnabled(false);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonVerArbol.setText("Ver arbol actual");
        jButtonVerArbol.setEnabled(false);
        jButtonVerArbol.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonVerArbolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonVerArbol)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonVerArbol)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Ver nodos ocultos");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane_jTreeVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton_ocultar_nodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1)
                        .addGap(29, 29, 29)
                        .addComponent(jButtonCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_guardar_ap)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane_jTreeVisual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_guardar_ap)
                            .addComponent(jButton_ocultar_nodo)
                            .addComponent(jButtonCancelar)
                            .addComponent(jCheckBox1))))
                .addContainerGap())
        );

        pack();
    }//GEN-END:initComponents

    private void jButtonVerArbolActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonVerArbolActionPerformed
    {//GEN-HEADEREND:event_jButtonVerArbolActionPerformed
        
           UI_Arbol_Evaluable ui_arbol =
            new UI_Arbol_Evaluable(this.arbolPodado);  
        
       
       
        
        
        
        
    }//GEN-LAST:event_jButtonVerArbolActionPerformed

    private void jButton_Agregar_nuevoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_Agregar_nuevoActionPerformed
    {//GEN-HEADEREND:event_jButton_Agregar_nuevoActionPerformed
    if (this.asignatura_en_uso.getArbol_dominio() != null)
    {
        this.arbolPodado = this.asignatura_en_uso.getArbol_dominio().toEvaluable();

        jtree_arbol.setModel(this.arbolPodado.getTreeModel());
    }
        this.modo_edicion = true;
        this.verificaEnabled();
    }//GEN-LAST:event_jButton_Agregar_nuevoActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelarActionPerformed
    {//GEN-HEADEREND:event_jButtonCancelarActionPerformed
        this.modo_edicion = false;
        limpiar();
     
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButton_guardar_apActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_guardar_apActionPerformed
    {//GEN-HEADEREND:event_jButton_guardar_apActionPerformed
    try
    {
        this.modelo.getModelo_abm_parcial().AgregarParcial(this.cursada_en_uso,
                                                           new Parcial(0, this.jText_Nombre.getText(),
                                                                       this.arbolPodado));

        this.modelo.recuperarParciales(this.cursada_en_uso);
        this.modo_edicion = false;
        limpiar();
        JOptionPane.showMessageDialog(this, "Parcial agregado");
    } catch (SQLException e)
    {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
    
    }//GEN-LAST:event_jButton_guardar_apActionPerformed

    private void jButton_ocultar_nodoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_ocultar_nodoActionPerformed
    {//GEN-HEADEREND:event_jButton_ocultar_nodoActionPerformed
        NodoPerturbacionEvaluable nodoSeleccionado = (NodoPerturbacionEvaluable) jtree_arbol.getNodoSeleccionado();
        DefaultTreeModel dtm = (DefaultTreeModel) this.jtree_arbol.getModel();
        if (nodoSeleccionado != null&& !nodoSeleccionado.isRoot())
        {
                this.jtree_arbol.setOculto(nodoSeleccionado,nodoSeleccionado.isEvaluado());
             this.jtree_arbol.repaint();
             //   this.jtree_arbol.verificaOcultos();
            }
        
        
        
    }//GEN-LAST:event_jButton_ocultar_nodoActionPerformed

    private void jList_asignaturasValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_jList_asignaturasValueChanged
    {//GEN-HEADEREND:event_jList_asignaturasValueChanged
    if (this.jList_asignaturas.getSelectedValue() != null&&!this.modo_edicion)
    {
        this.asignatura_en_uso = (Asignatura) jList_asignaturas.getSelectedValue();
        this.jText_Asignatura.setText(this.asignatura_en_uso.toString());
        this.cursada_en_uso = null;
        this.actualizar_jList_cursadas();
        this.actualizar_jList_parciales();
        this.jButton_ocultar_nodo.setEnabled(false);

        if (this.asignatura_en_uso.getArbol_dominio() != null)
        {
            this.arbolPodado = this.asignatura_en_uso.getArbol_dominio().toEvaluable();

            jtree_arbol.setModel(this.arbolPodado.getTreeModel());
        }
        this.verificaEnabled();
    }
    }//GEN-LAST:event_jList_asignaturasValueChanged

    private void jList_cursadasValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_jList_cursadasValueChanged
    {//GEN-HEADEREND:event_jList_cursadasValueChanged
    if (this.jList_cursadas.getSelectedValue() != null&&!this.modo_edicion)
    {
        this.cursada_en_uso = (Cursada) this.jList_cursadas.getSelectedValue();
        this.jText_Nombre_Cursada.setText(String.valueOf(this.cursada_en_uso.getNombreCursada()));
        this.actualizar_jList_parciales();
        if (this.asignatura_en_uso.getArbol_dominio() != null)
        {
            this.arbolPodado = this.asignatura_en_uso.getArbol_dominio().toEvaluable();

            jtree_arbol.setModel(this.arbolPodado.getTreeModel());
        }
        this.verificaEnabled();
        this.jButton_ocultar_nodo.setEnabled(false);
    }
    }//GEN-LAST:event_jList_cursadasValueChanged

    private void jList_parcialesValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_jList_parcialesValueChanged
    {//GEN-HEADEREND:event_jList_parcialesValueChanged
    if(this.jList_parciales.getSelectedValue()!=null&&!this.modo_edicion)      
    {
            Parcial parcial = (Parcial) this.jList_parciales.getSelectedValue();
            this.arbolPodado = parcial.getArbol_podado();
            jtree_arbol.setModel(this.arbolPodado.getTreeModel());
        }
    
    }//GEN-LAST:event_jList_parcialesValueChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jCheckBox1ActionPerformed
    {//GEN-HEADEREND:event_jCheckBox1ActionPerformed
        this.jtree_arbol.setMuestraNodosOcultos(this.jCheckBox1.isSelected());
    }//GEN-LAST:event_jCheckBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonVerArbol;
    private javax.swing.JButton jButton_Agregar_nuevo;
    private javax.swing.JButton jButton_guardar_ap;
    private javax.swing.JButton jButton_ocultar_nodo;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JList jList_asignaturas;
    private javax.swing.JList jList_cursadas;
    private javax.swing.JList jList_parciales;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane_jTreeVisual;
    private javax.swing.JTextField jText_Asignatura;
    private javax.swing.JTextField jText_Nombre;
    private javax.swing.JTextField jText_Nombre_Cursada;
    // End of variables declaration//GEN-END:variables
    private void actualizar_jList_asignaturas()
    {
        listModel_asignaturas.clear();
        Iterator iterator_asignaturas = modelo.getAsignaturas().values().iterator();
        //Recorrer el contenido del Iterator
        while (iterator_asignaturas.hasNext())
        {
            Asignatura as = (Asignatura) iterator_asignaturas.next();
            listModel_asignaturas.addElement(as);
        }
    }

    private void actualizar_jList_cursadas()
    {
        this.listModel_cursadas.clear();
        if (this.asignatura_en_uso != null)
        {
            ArrayList<Cursada> cursadas_actuales = this.asignatura_en_uso.getCursadas();
            for (int i = 0; i < cursadas_actuales.size(); i++)
            {
                this.listModel_cursadas.addElement(cursadas_actuales.get(i));
            }
        }
    }

    private void actualizar_jList_parciales()
    {
        this.listModel_parciales.clear();
        if (this.cursada_en_uso != null)
        {
            ArrayList<Parcial> parciales_actuales = this.cursada_en_uso.getParciales();
            for (int i = 0; i < parciales_actuales.size(); i++)
            {
                this.listModel_parciales.addElement(parciales_actuales.get(i));
            }
        }
    }


    private void verificaEnabled()
    {
        boolean valida =
            (this.cursada_en_uso != null && this.asignatura_en_uso != null && this.jtree_arbol.getModel() != null);
        this.jButton_Agregar_nuevo.setEnabled(valida);
        this.jText_Nombre.setEnabled(valida && this.modo_edicion);
        this.jButton_guardar_ap.setEnabled(valida && this.modo_edicion);
        this.jButtonCancelar.setEnabled(valida && this.modo_edicion);
        this.jButtonVerArbol.setEnabled(this.jtree_arbol.getModel() != null);
        this.jList_asignaturas.setEnabled(!this.modo_edicion);
        this.jList_cursadas.setEnabled(!this.modo_edicion);
        this.jList_parciales.setEnabled(!this.modo_edicion);
    }

    /*    private void eliminar_arbol_completo()
    {

        this.jtree_arbol.setModel(null);
        jtree_arbol.paintAll(this.jtree_arbol.getGraphics());
        this.jButton_eliminar_nodo.setEnabled(false);

    } */

    private void limpiar()
    {
        this.asignatura_en_uso = null;
        this.cursada_en_uso = null;
        this.jtree_arbol.setModel(null);
        this.jText_Asignatura.setText("");
        this.jText_Nombre.setText("");
        this.jText_Nombre_Cursada.setText("");
        this.actualizar_jList_cursadas();
        this.actualizar_jList_parciales();
        this.jButton_ocultar_nodo.setEnabled(false);
        this.verificaEnabled();


    }

}