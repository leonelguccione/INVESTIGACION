
package UI;


import arbol_perturbacion_visual.ANoEvaluableVisual;
import arbol_perturbacion_visual.ArbolPerturbacionVisual;


import arbolvisual.ArbolVisual;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.SQLException;

import java.util.Iterator;

import javax.sql.rowset.serial.SerialException;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import modelo.ArbolPerturbacion;

import modelo.Asignatura;
import modelo.Modelo;
import modelo.Modelo_ABM_Asignatura;
import modelo.NodoPerturbacion;
import modelo.RelacionImpacto;


//TODO: falta el tema de agregar el listener para que escuche cuando se toca un nodo y actualice
//el jTextFiel del nodo padre y esas cosas.
//Ver PanelArbol y PanelAlta


/**
 *
 * @author leonel
 */
public class UI_Asignatura extends javax.swing.JInternalFrame
{
    Modelo modelo;
    Modelo_ABM_Asignatura modelo_abm_asignatura;


    /**modelo vinculado a JList_asignatura
     * listado de asignaturas creadas
     */
    DefaultListModel listModel_asignaturas = new DefaultListModel();
    Asignatura asignatura_en_uso = null;
    ArbolPerturbacion arbol_auxiliar = null;
    ArbolPerturbacionVisual jtree_arbol;
    NodoPerturbacion nodoOrigen = null;
    NodoPerturbacion nodoDestino = null;

    /**modificar es true: si se habilita para modificar datos de una asignatura previamente ingresada
     * modificar es false: si se habilita para crear una nueva asignaturar
     * */
    private boolean modificar = false;
    private boolean modoEdicion = false;
    private MouseAdapter mouseA;

    /** Creates new form UI_Asignatura
     * Se referencia al objeto modelo
     * */
    public UI_Asignatura(Modelo modelo)
    {

        initComponents();
        this.modelo = modelo;
        this.modelo_abm_asignatura = modelo.getModelo_abm_asignatura();
        this.jList_asignaturas.setModel(listModel_asignaturas);
        this.mouseA = new MouseAdapter()
        {

            @Override
            public void mousePressed(MouseEvent e)
            {
                // TODO Implement this method
                super.mousePressed(e);
                DefaultMutableTreeNode dmtn = jtree_arbol.getNodoSeleccionado();
                if (dmtn != null)
                {
                    jTextField_padre.setText(dmtn.toString());
                }
                jButton_eliminar_nodo.setEnabled(dmtn != null && modoEdicion);
            }


        };
        jtree_arbol = (ArbolPerturbacionVisual) jScrollPane_jTreeVisual;
        this.asignatura_en_uso = null;
        listModel_asignaturas.clear();
        this.cargar_jList_asignaturas();
        this.jtree_arbol.setModel(null);
        this.jtree_arbol.setLineasRectas(true);
        this.setModoEdicion(false);
        this.jtree_arbol.addMouseListener(mouseA);
    }


    public void setModoEdicion(boolean v)
    {
        this.modoEdicion = v;
        this.jTextField_codigo_asignatura.setEnabled(v);
        this.jTextField_nombre_asignatura.setEnabled(v);
        this.jTextField_nombre_arbol_dominio.setEnabled(v);
        this.jTextField_nuevo.setEnabled(v);
        this.jButton_aceptar.setEnabled(v);
        this.jButton_cancelar.setEnabled(v);
        this.jButton_agregar_nuevo_nodo.setEnabled(v);
        this.jButton_eliminar_nodo.setEnabled(false);
        this.jButton_habilitar_modificacion_asignatura.setEnabled(!v);
        this.jButton_habilitar_nueva_asignatura.setEnabled(!v);
        this.jButton_eliminar_asignatura.setEnabled(!v);
        this.jList_asignaturas.setEnabled(!v);


    }


    private void eliminar_arbol_completo()
    {
        this.asignatura_en_uso.setArbol_dominio(null);
        this.jtree_arbol.setModel(null);
        jtree_arbol.repaint();

    }

    private void bt_raizActionPerformed() // se crea el ÃƒÂ¡rbol de dominio para una asignatura nueva.
    {

        this.arbol_auxiliar =
            new ArbolPerturbacion(jTextField_nombre_arbol_dominio.getText().trim(), "< sin descripción >");
        this.arbol_auxiliar.setRaiz(jTextField_nuevo.getText().trim());
        jtree_arbol.setModel(this.arbol_auxiliar.getTreeModel());
        //TODO: probar esto en el constructor
        jtree_arbol.addMouseListener(mouseA);
        this.jTextField_nuevo.setText("");
        this.jTextField_padre.setText("");
    }

    private void bt_nuevo_nodoActionPerformed()
    {
        NodoPerturbacion nodo_seleccionado_padre = (NodoPerturbacion) this.jtree_arbol.getNodoSeleccionado();
        this.arbol_auxiliar.agregarNodo(nodo_seleccionado_padre, this.jTextField_nuevo
                                                                     .getText()
                                                                     .trim());
        jtree_arbol.repaint();
        //jtree_arbol.expandPath(this.jtree_arbol.getSelectionPath());
        this.jTextField_nuevo.setText("");
        this.jTextField_padre.setText("");
    }


    public void cargar_jList_asignaturas()
    {

        Iterator iterator_asignaturas = this.modelo
                                            .getAsignaturas()
                                            .values()
                                            .iterator();
        while (iterator_asignaturas.hasNext())
        {
            Asignatura asignatura_ste = (Asignatura) iterator_asignaturas.next();
            listModel_asignaturas.addElement(asignatura_ste);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jList_asignaturas = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_nombre_asignatura = new javax.swing.JTextField();
        jTextField_codigo_asignatura = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_nombre_arbol_dominio = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane_jTreeVisual = new ANoEvaluableVisual();
        jButton_aceptar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel_padre = new javax.swing.JLabel();
        jTextField_padre = new javax.swing.JTextField();
        jLabel_nuevo = new javax.swing.JLabel();
        jTextField_nuevo = new javax.swing.JTextField();
        jButton_agregar_nuevo_nodo = new javax.swing.JButton();
        jButton_eliminar_nodo = new javax.swing.JButton();
        jButton_cancelar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton_ver_arbol = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton_eliminar_asignatura = new javax.swing.JButton();
        jButton_habilitar_modificacion_asignatura = new javax.swing.JButton();
        jButton_habilitar_nueva_asignatura = new javax.swing.JButton();

        setClosable(true);
        setTitle("Asignaturas");
        setName("Asignaturas"); // NOI18N
        setNormalBounds(new java.awt.Rectangle(0, 0, 1010, 710));
        try
        {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1)
        {
            e1.printStackTrace();
        }
        setVisible(true);

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
        jScrollPane1.setViewportView(jList_asignaturas);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Crear o Modificar una Asignatura"));

        jLabel2.setText("Nombre:");

        jLabel3.setText("Código:");

        jLabel4.setText("Dominio: ");
        jLabel4.setToolTipText("Nombre del árbol de dominio");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_nombre_arbol_dominio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(jTextField_codigo_asignatura, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField_nombre_asignatura, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_codigo_asignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_nombre_asignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_nombre_arbol_dominio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Alta de Árbol de Dominio"));

        jScrollPane_jTreeVisual.setBorder(javax.swing.BorderFactory.createTitledBorder("Árbol de Dominio"));

        jButton_aceptar.setText("Aceptar");
        jButton_aceptar.setEnabled(false);
        jButton_aceptar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_aceptarActionPerformed(evt);
            }
        });

        jLabel_padre.setText("Padre:");

        jTextField_padre.setEnabled(false);

        jLabel_nuevo.setText("Nuevo:");

        jTextField_nuevo.setEnabled(false);

        jButton_agregar_nuevo_nodo.setText("Agregar nuevo");
        jButton_agregar_nuevo_nodo.setEnabled(false);
        jButton_agregar_nuevo_nodo.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_agregar_nuevo_nodoActionPerformed(evt);
            }
        });

        jButton_eliminar_nodo.setText("Eliminar Nodo");
        jButton_eliminar_nodo.setEnabled(false);
        jButton_eliminar_nodo.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_eliminar_nodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_padre)
                    .addComponent(jLabel_nuevo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(jTextField_padre))
                .addGap(196, 196, 196)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_agregar_nuevo_nodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_eliminar_nodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_padre)
                    .addComponent(jTextField_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_agregar_nuevo_nodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_eliminar_nodo)
                    .addComponent(jLabel_nuevo)
                    .addComponent(jTextField_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jButton_cancelar.setText("Cancelar");
        jButton_cancelar.setEnabled(false);
        jButton_cancelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_cancelarActionPerformed(evt);
            }
        });

        jButton2.setText("Origen");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Destino");
        jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Crear Relacion");
        jButton4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton4ActionPerformed(evt);
            }
        });

        jButton_ver_arbol.setText("Ver Árbol");
        jButton_ver_arbol.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_ver_arbolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane_jTreeVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(26, 26, 26)
                .addComponent(jButton4)
                .addGap(28, 28, 28)
                .addComponent(jButton_ver_arbol)
                .addGap(26, 26, 26)
                .addComponent(jButton_cancelar)
                .addGap(28, 28, 28)
                .addComponent(jButton_aceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane_jTreeVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_aceptar)
                        .addComponent(jButton_cancelar))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton3)
                        .addComponent(jButton4)
                        .addComponent(jButton_ver_arbol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(110, 110, 110))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton_eliminar_asignatura.setText("Eliminar");
        jButton_eliminar_asignatura.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_eliminar_asignaturaActionPerformed(evt);
            }
        });

        jButton_habilitar_modificacion_asignatura.setText("Modificar");
        jButton_habilitar_modificacion_asignatura.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_habilitar_modificacion_asignaturaActionPerformed(evt);
            }
        });

        jButton_habilitar_nueva_asignatura.setText("Nueva Asignatura");
        jButton_habilitar_nueva_asignatura.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_habilitar_nueva_asignaturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_habilitar_nueva_asignatura)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_eliminar_asignatura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_habilitar_modificacion_asignatura)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_eliminar_asignatura)
                    .addComponent(jButton_habilitar_modificacion_asignatura))
                .addGap(18, 18, 18)
                .addComponent(jButton_habilitar_nueva_asignatura)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(116, 116, 116))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }//GEN-END:initComponents

    private void jButton_ver_arbolActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_ver_arbolActionPerformed
    {//GEN-HEADEREND:event_jButton_ver_arbolActionPerformed
        
        ArbolPerturbacion a = null;
        if (this.asignatura_en_uso!=null&& this.asignatura_en_uso.getArbol_dominio() != null)
            a = this.asignatura_en_uso.getArbol_dominio();
        else if (this.arbol_auxiliar != null)
            a = this.arbol_auxiliar;

        if (a != null)
        {
            UI_Arbol_Perturbacion ui_arbol = new UI_Arbol_Perturbacion(a);
        }
    }//GEN-LAST:event_jButton_ver_arbolActionPerformed

    private void jButton_aceptarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_aceptarActionPerformed
    {//GEN-HEADEREND:event_jButton_aceptarActionPerformed
        if (this.modificar)
        {
            this.asignatura_en_uso.setArbol_dominio(this.arbol_auxiliar);

            try
            {
                this.modelo_abm_asignatura.actualizar_arbol_perturbacion(this.asignatura_en_uso);
                JOptionPane.showMessageDialog(this, "Asignatura actualizada");
            } catch (SerialException e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (SQLException e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            this.modificar = false;
        } else
        {
            String codigo_asignatura = jTextField_codigo_asignatura.getText().trim();
            String nombre_asignatura = jTextField_nombre_asignatura.getText().trim();
            String nombre_arbol_dominio = jTextField_nombre_arbol_dominio.getText().trim();
            this.asignatura_en_uso = new Asignatura(nombre_asignatura, codigo_asignatura, this.arbol_auxiliar);
            JOptionPane.showMessageDialog(this, "Asignatura guardada");
            try
            {
                this.modelo.agrega_asignatura(this.asignatura_en_uso);
            } catch (SQLException e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            this.limpiar_campos();

        }
        this.setModoEdicion(false);
        this.arbol_auxiliar = null;
       
    }//GEN-LAST:event_jButton_aceptarActionPerformed

    private void jButton_cancelarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_cancelarActionPerformed
    {//GEN-HEADEREND:event_jButton_cancelarActionPerformed
       
        this.limpiar_campos();
        this.arbol_auxiliar = null;
        this.setModoEdicion(false);
        this.modificar = false;
             
    }//GEN-LAST:event_jButton_cancelarActionPerformed

    private void jButton_eliminar_asignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_eliminar_asignaturaActionPerformed
    {//GEN-HEADEREND:event_jButton_eliminar_asignaturaActionPerformed
        try
        {
            this.modelo_abm_asignatura.borrar_asignatura(this.asignatura_en_uso);
            JOptionPane.showMessageDialog(this, "Asignatura eliminada");
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        this.modelo
            .getAsignaturas()
            .remove(this.asignatura_en_uso.getCodigo());
        this.limpiar_campos();

        if (jList_asignaturas.getModel().getSize() > 0)
        {
            jList_asignaturas.setSelectedIndex(0);
        }
        this.asignatura_en_uso = null;
    }//GEN-LAST:event_jButton_eliminar_asignaturaActionPerformed

    private void jButton_habilitar_nueva_asignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_habilitar_nueva_asignaturaActionPerformed
    {//GEN-HEADEREND:event_jButton_habilitar_nueva_asignaturaActionPerformed
        this.setModoEdicion(true);
        this.limpiar_campos();
        this.asignatura_en_uso = null;
        this.jTextField_codigo_asignatura.requestFocus();
        this.modificar = false;
    }//GEN-LAST:event_jButton_habilitar_nueva_asignaturaActionPerformed

    private void jButton_habilitar_modificacion_asignaturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_habilitar_modificacion_asignaturaActionPerformed
    {//GEN-HEADEREND:event_jButton_habilitar_modificacion_asignaturaActionPerformed
        if (this.asignatura_en_uso != null)
        {
            this.modificar = true;
            this.setModoEdicion(true);

            if (this.asignatura_en_uso.getArbol_dominio() != null)
            {
                this.arbol_auxiliar = this.asignatura_en_uso
                                          .getArbol_dominio()
                                          .clone();
                this.jtree_arbol.setModel(arbol_auxiliar.getTreeModel());
                this.jtree_arbol.repaint();

            }
        }
    }//GEN-LAST:event_jButton_habilitar_modificacion_asignaturaActionPerformed

    private void jButton_eliminar_nodoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_eliminar_nodoActionPerformed
    {//GEN-HEADEREND:event_jButton_eliminar_nodoActionPerformed
        DefaultMutableTreeNode nodoSeleccionado = jtree_arbol.getNodoSeleccionado();
        DefaultTreeModel dtm = (DefaultTreeModel) this.jtree_arbol.getModel();
        if (nodoSeleccionado != null)
        {
            if (!nodoSeleccionado.isRoot())
            {
                dtm.removeNodeFromParent(nodoSeleccionado);
            } else
            {
                this.eliminar_arbol_completo();
            }
        }
    }//GEN-LAST:event_jButton_eliminar_nodoActionPerformed

    private void jButton_agregar_nuevo_nodoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_agregar_nuevo_nodoActionPerformed
    {//GEN-HEADEREND:event_jButton_agregar_nuevo_nodoActionPerformed
        if (this.arbol_auxiliar == null)
        {
            this.bt_raizActionPerformed();
        } else
        {
            this.bt_nuevo_nodoActionPerformed();
        }
    }//GEN-LAST:event_jButton_agregar_nuevo_nodoActionPerformed

    private void jList_asignaturasValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_jList_asignaturasValueChanged
    {//GEN-HEADEREND:event_jList_asignaturasValueChanged
        DefaultTreeModel treeModelSeleccionado = null;
        //this.limpiar_jTree();
        if (jList_asignaturas.getSelectedValue() != null)
        {
            this.asignatura_en_uso = (Asignatura) jList_asignaturas.getSelectedValue();
            if (this.asignatura_en_uso.getArbol_dominio() != null)

            {
                treeModelSeleccionado = this.asignatura_en_uso
                                            .getArbol_dominio()
                                            .getTreeModel();
                this.jTextField_nombre_arbol_dominio.setText(this.asignatura_en_uso
                                                                 .getArbol_dominio()
                                                                 .getNombre());
            }
            jtree_arbol.setModel(treeModelSeleccionado);
            this.jTextField_codigo_asignatura.setText(this.asignatura_en_uso.getCodigo());
            this.jTextField_nombre_asignatura.setText(this.asignatura_en_uso.getNombre());

        }
     
    }//GEN-LAST:event_jList_asignaturasValueChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (this.jtree_arbol.getNodoSeleccionado() != null)
            ;
        this.nodoOrigen = (NodoPerturbacion) this.jtree_arbol.getNodoSeleccionado();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.nodoDestino = (NodoPerturbacion) this.jtree_arbol.getNodoSeleccionado();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (this.nodoOrigen != null)
            this.nodoOrigen.addImpacta(this.nodoDestino);
        this.jtree_arbol.repaint();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton_aceptar;
    private javax.swing.JButton jButton_agregar_nuevo_nodo;
    private javax.swing.JButton jButton_cancelar;
    private javax.swing.JButton jButton_eliminar_asignatura;
    private javax.swing.JButton jButton_eliminar_nodo;
    private javax.swing.JButton jButton_habilitar_modificacion_asignatura;
    private javax.swing.JButton jButton_habilitar_nueva_asignatura;
    private javax.swing.JButton jButton_ver_arbol;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_nuevo;
    private javax.swing.JLabel jLabel_padre;
    private javax.swing.JList jList_asignaturas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane_jTreeVisual;
    private javax.swing.JTextField jTextField_codigo_asignatura;
    private javax.swing.JTextField jTextField_nombre_arbol_dominio;
    private javax.swing.JTextField jTextField_nombre_asignatura;
    private javax.swing.JTextField jTextField_nuevo;
    private javax.swing.JTextField jTextField_padre;
    // End of variables declaration//GEN-END:variables
    private void limpiar_campos()
    {
        listModel_asignaturas.clear();
        this.cargar_jList_asignaturas();
        this.jtree_arbol.setModel(null);
        this.jTextField_codigo_asignatura.setText("");
        this.jTextField_nombre_arbol_dominio.setText("");
        this.jTextField_nombre_asignatura.setText("");
        this.asignatura_en_uso = null;


    }
}
