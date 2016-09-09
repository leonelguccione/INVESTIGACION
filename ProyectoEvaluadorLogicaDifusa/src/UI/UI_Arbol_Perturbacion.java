
package UI;

import arbol_visual.ArbolVisual;

import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import modelo.Arbol_Perturbacion;
import modelo.DatoBean;
import modelo.Modelo;
import modelo.Modelo_ABM_arbol_perturbacion;
import modelo.Nodo_Perturbacion;

/**
 *
 * @author leonel
 */
public class UI_Arbol_Perturbacion extends javax.swing.JInternalFrame
{
    Modelo modelo;
    Modelo_ABM_arbol_perturbacion modelo_abm_ap;
    DefaultListModel listModel = new DefaultListModel();
    Arbol_Perturbacion ap_en_uso = null;

    ArbolVisual jtree_arbol;

    @SuppressWarnings("unchecked")
    public UI_Arbol_Perturbacion(Modelo modelo)
    {
        initComponents();
        this.modelo = modelo;
        this.modelo_abm_ap = modelo.getModelo_abm_ap();
        this.jLista_arboles.setModel(listModel);
        jtree_arbol = (ArbolVisual)jScrollPane_arbol_visual;
        //jtree_arbol.setSize(796, 566);
        //jPanel_arbol_visual.setSize(796, 566);
        this.limpiar_jList();
        this.limpiar_jTree();
        this.cambiar_iconos_jtree();
        this.cargar_jList();
    }

    /**setea componente visuales para cargar la raiz del arbol
     */
    private void habilitar_cargar_raiz()
    {
        this.jL_nodo_padre_seleccionado.setEnabled(false);
        this.jt_nodo_padre.setEnabled(false);
        this.jL_nuevo_identificador_de_nodo.setText("Identificador nodo raiz");
        this.bt_nuevo_nodo.setText("RAIZ");
    }

    private void habilitar_cargar_nodos_hijos()
    {
        this.jL_nodo_padre_seleccionado.setEnabled(true);
        this.jt_nodo_padre.setEnabled(true);
        this.jL_nuevo_identificador_de_nodo.setText("Nuevo Identificador de Nodo");
        this.bt_nuevo_nodo.setText("Nuevo Nodo");
    }

    public void limpiar_jList()
    {
        listModel.clear();
    }

    public void limpiar_creacion_arbol()
    {
        jArea_descripcion_arbol.setText("");
        jt_nombre_arbol.setText("");
    }

    public void cargar_jList()
    {
        Iterator iterator_arboles_perturbacion = modelo_abm_ap.getIterator_listado_arboles_perturbacion();
        //Recorrer el contenido del Iterator
        while (iterator_arboles_perturbacion.hasNext())
        {
            Arbol_Perturbacion ap = (Arbol_Perturbacion) iterator_arboles_perturbacion.next();
            listModel.addElement(ap);
        }
        //Asociar el modelo de lista al JList
        //jLista_arboles.setModel(listModel);
    }

    public void limpiar_jTree()
    {
        this.jtree_arbol.setModel(null);
    }

    public void cargar_jTree()
    {
        //jtree_arbol.setModel(this.ap_en_uso.getTreeModel()); //vincula el jtree con su DefaultTreeModel
        jtree_arbol.setearParametrosArbolVisual(this.ap_en_uso.getTreeModel());
        //jtree_arbol.paintAll(this.jtree_arbol.getGraphics());
        //jtree_arbol.expandPath(jtree_arbol.getSelectionPath());
    }

    public void cambiar_iconos_jtree()
    {
        jtree_arbol.setJuegoImagenesNodoVisual(new ImageIcon("amarillo.png"), new ImageIcon("azul.png"),
                                               new ImageIcon("verde.png"), new ImageIcon("amarillo_sel.png"),
                                               new ImageIcon("azul_sel.png"), new ImageIcon("verde_sel.png"),
                                               new ImageIcon("mas.png"), new ImageIcon("menos.png"));
    }


    // LOS BOTONES ----------------------------------------------------------------------------------------------------

    /**crea un nuevo arbol (nombre y descripciأ³n) y establece el atributo "ap_en_uso"
     */
    public void bt_crear_arbolActionPerformed()
    {
        String id_Arbol = jt_nombre_arbol.getText().trim();
        String descripcion = jArea_descripcion_arbol.getText().trim();
        this.ap_en_uso = new Arbol_Perturbacion(id_Arbol, descripcion);
        this.jL_arbol_en_uso.setText(id_Arbol);
        this.jL_descripcion.setText(descripcion);
        this.limpiar_creacion_arbol();
        this.habilitar_cargar_raiz();
        this.limpiar_jTree();
        bt_guadarActionPerformed();
    }

    public void bt_borrar_arbolActionPerformed()
    {
        this.ap_en_uso = (Arbol_Perturbacion) jLista_arboles.getSelectedValue();
        this.modelo_abm_ap.borrar_arbol_perturbacion(ap_en_uso);
        this.ap_en_uso = null;
        bt_limpiarActionPerformed(); //limpia el jtree que muestra el arbol seleccionado
        bt_cargarListaActionPerformed();
    }

    private void bt_nuevo_nodoActionPerformed()
    {
        String id_nuevo_nodo = jt_nuevo_nodo.getText().trim();
        if (this.ap_en_uso.getRaiz() == null)
        {
            this.ap_en_uso.setRaiz(id_nuevo_nodo);
            jtree_arbol.setModel(this.ap_en_uso.getTreeModel());
        }
        else
        {
            Nodo_Perturbacion nodo_seleccionado_padre = (Nodo_Perturbacion) jtree_arbol.getLastSelectedPathComponent();
            String idNodo_padre = (nodo_seleccionado_padre.getDato()).getIdDato();
            jt_nodo_padre.setText(idNodo_padre);
            this.ap_en_uso.agregarNodo(nodo_seleccionado_padre, this.jt_nuevo_nodo.getText().trim());
            jtree_arbol.paintAll(this.jtree_arbol.getGraphics());
            jtree_arbol.expandPath(this.jtree_arbol.getSelectionPath());
        }
        if (!this.jt_nodo_padre.isEnabled())
            this.habilitar_cargar_nodos_hijos();
        this.jt_nuevo_nodo.setText("");
        this.jt_nodo_padre.setText("");
    }

    private void bt_guadarActionPerformed()
    {
        modelo_abm_ap.guardar_arbol_perturbacion(this.ap_en_uso);
        this.limpiar_jTree();
        limpiar_jList();
        cargar_jList();
        jLista_arboles.setSelectedValue(this.ap_en_uso, true);
    }

    private void bt_limpiarActionPerformed()
    {
        this.ap_en_uso = null;
        this.jtree_arbol.setModel(null);
        jtree_arbol.paintAll(this.jtree_arbol.getGraphics());
        this.jL_arbol_en_uso.setText("-----");
        this.jL_descripcion.setText("-----");
        this.jt_nuevo_nodo.setText("");
        this.jt_nodo_padre.setText("");
    }

    private void bt_cargarListaActionPerformed()
    {
        limpiar_jList();
        cargar_jList();
    }

    // Eventos sobre jLista ------------------------------------------------------------------------------------------

    private void jLista_arbolesMouseClicked()
    {
        this.ap_en_uso = (Arbol_Perturbacion) jLista_arboles.getSelectedValue();
        //modelo_abm_ap.setArbol_Perturbacion_en_uso((Arbol_Perturbacion) jLista_arboles.getSelectedValue());
        jL_arbol_en_uso.setText(this.ap_en_uso.getNombre());
        jL_descripcion.setText(this.ap_en_uso.getDescripcion().trim());
        jLista_arboles.setSelectedValue(this.ap_en_uso, true);
        limpiar_jTree();
        cargar_jTree();
    }

    // Eventos sobre jtree ------------------------------------------------------------------------------------------

    private void jtree_arbolMouseClicked()
    {
        Nodo_Perturbacion nodo_seleccionado;
        nodo_seleccionado = (Nodo_Perturbacion) jtree_arbol.getLastSelectedPathComponent();
        if (nodo_seleccionado != null)
        {
            DatoBean db = ((DatoBean) nodo_seleccionado.getUserObject());        
            String idPadre = db.getIdDato();
            jt_nodo_padre.setText(idPadre.toString().trim());
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

        jt_nombre_arbol = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLista_arboles = new javax.swing.JList();
        jLabel10 = new javax.swing.JLabel();
        bt_crear_arbol = new javax.swing.JButton();
        bt_cargar_lista = new javax.swing.JButton();
        jLabel_crear_arbol = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jArea_descripcion_arbol = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        bt_guadar = new javax.swing.JButton();
        bt_limpiar = new javax.swing.JButton();
        bt_nuevo_nodo = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jL_descripcion = new javax.swing.JLabel();
        jL_arbol_en_uso = new javax.swing.JLabel();
        jL_nuevo_identificador_de_nodo = new javax.swing.JLabel();
        jL_nodo_padre_seleccionado = new javax.swing.JLabel();
        jt_nodo_padre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jt_nuevo_nodo = new javax.swing.JTextField();
        jScrollPane_arbol_visual = new ArbolVisual();
        bt_borrar_arbol = new javax.swing.JButton();

        setClosable(true);
        setForeground(new java.awt.Color(153, 255, 255));
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("ABM Árbol de Perturbación");
        setAutoscrolls(true);
        setMaximumSize(new java.awt.Dimension(1173, 830));
        setMinimumSize(new java.awt.Dimension(1173, 830));
        setPreferredSize(new java.awt.Dimension(1173, 830));
        try
        {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1)
        {
            e1.printStackTrace();
        }
        setVisible(true);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel1.setText("ABM Arbol de Perturbación");

        jLabel2.setText("Nombre:");

        jLista_arboles.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jLista_arboles.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jLista_arbolesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jLista_arboles);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Listado de árboles");

        bt_crear_arbol.setText("Crear Árbol");
        bt_crear_arbol.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_crear_arbolActionPerformed(evt);
            }
        });

        bt_cargar_lista.setText("Cargar Lista");
        bt_cargar_lista.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_cargar_listaActionPerformed(evt);
            }
        });

        jLabel_crear_arbol.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel_crear_arbol.setText("Datos del nuevo arbol de Perturbacion");

        jLabel8.setText("Descripción: ");

        jArea_descripcion_arbol.setColumns(20);
        jArea_descripcion_arbol.setRows(5);
        jScrollPane3.setViewportView(jArea_descripcion_arbol);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bt_guadar.setText("Guardar");
        bt_guadar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_guadarActionPerformed(evt);
            }
        });

        bt_limpiar.setText("Limpiar");
        bt_limpiar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_limpiarActionPerformed(evt);
            }
        });

        bt_nuevo_nodo.setText("Nuevo Nodo");
        bt_nuevo_nodo.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_nuevo_nodoActionPerformed(evt);
            }
        });

        jLabel7.setText("Descripción: ");

        jL_descripcion.setText("---");

        jL_arbol_en_uso.setText("---");

        jL_nuevo_identificador_de_nodo.setText("Nuevo Identificador de Nodo:");

        jL_nodo_padre_seleccionado.setText("Nodo Padre Seleccionado:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setText("árbol seleccionado: ");

        jScrollPane_arbol_visual.setBorder(javax.swing.BorderFactory.createTitledBorder("Árbol de Perturbación"));
        jScrollPane_arbol_visual.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jScrollPane_arbol_visualMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(bt_nuevo_nodo))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jL_nodo_padre_seleccionado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jL_nuevo_identificador_de_nodo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jL_descripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                                    .addComponent(jt_nodo_padre)
                                    .addComponent(jt_nuevo_nodo)
                                    .addComponent(jL_arbol_en_uso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane_arbol_visual))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bt_guadar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_limpiar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jL_arbol_en_uso))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jL_descripcion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_nodo_padre_seleccionado)
                    .addComponent(jt_nodo_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jt_nuevo_nodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jL_nuevo_identificador_de_nodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_nuevo_nodo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane_arbol_visual, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_guadar)
                    .addComponent(bt_limpiar)))
        );

        bt_borrar_arbol.setText("Borrar arbol");
        bt_borrar_arbol.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_borrar_arbolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_crear_arbol)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jt_nombre_arbol, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(bt_crear_arbol, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_cargar_lista)
                        .addGap(9, 9, 9)
                        .addComponent(bt_borrar_arbol)))
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_crear_arbol)
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jt_nombre_arbol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_crear_arbol)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_cargar_lista)
                            .addComponent(bt_borrar_arbol))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleParent(this);

        pack();
    }//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3ActionPerformed
    {//GEN-HEADEREND:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void bt_crear_arbolActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_bt_crear_arbolActionPerformed
    {//GEN-HEADEREND:event_bt_crear_arbolActionPerformed
        bt_crear_arbolActionPerformed();
        
    }//GEN-LAST:event_bt_crear_arbolActionPerformed

    private void bt_nuevo_nodoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_bt_nuevo_nodoActionPerformed
    {//GEN-HEADEREND:event_bt_nuevo_nodoActionPerformed
        bt_nuevo_nodoActionPerformed();
        
       
    }//GEN-LAST:event_bt_nuevo_nodoActionPerformed

    private void jLista_arbolesMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jLista_arbolesMouseClicked
    {//GEN-HEADEREND:event_jLista_arbolesMouseClicked
        jLista_arbolesMouseClicked();
    }//GEN-LAST:event_jLista_arbolesMouseClicked

    private void bt_guadarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_bt_guadarActionPerformed
    {//GEN-HEADEREND:event_bt_guadarActionPerformed
        bt_guadarActionPerformed();
    }//GEN-LAST:event_bt_guadarActionPerformed

    private void bt_limpiarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_bt_limpiarActionPerformed
    {//GEN-HEADEREND:event_bt_limpiarActionPerformed
        // TODO add your handling code here:
        this.bt_limpiarActionPerformed();
    }//GEN-LAST:event_bt_limpiarActionPerformed

    private void bt_cargar_listaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_bt_cargar_listaActionPerformed
    {//GEN-HEADEREND:event_bt_cargar_listaActionPerformed
        bt_cargarListaActionPerformed();
    }//GEN-LAST:event_bt_cargar_listaActionPerformed

    private void bt_borrar_arbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_borrar_arbolActionPerformed
        // TODO add your handling code here:
        bt_borrar_arbolActionPerformed();
    }//GEN-LAST:event_bt_borrar_arbolActionPerformed

    private void jScrollPane_arbol_visualMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jScrollPane_arbol_visualMouseClicked
    {//GEN-HEADEREND:event_jScrollPane_arbol_visualMouseClicked
        this.jtree_arbolMouseClicked();
    }//GEN-LAST:event_jScrollPane_arbol_visualMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_borrar_arbol;
    private javax.swing.JButton bt_cargar_lista;
    private javax.swing.JButton bt_crear_arbol;
    private javax.swing.JButton bt_guadar;
    private javax.swing.JButton bt_limpiar;
    private javax.swing.JButton bt_nuevo_nodo;
    private javax.swing.JTextArea jArea_descripcion_arbol;
    private javax.swing.JLabel jL_arbol_en_uso;
    private javax.swing.JLabel jL_descripcion;
    private javax.swing.JLabel jL_nodo_padre_seleccionado;
    private javax.swing.JLabel jL_nuevo_identificador_de_nodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_crear_arbol;
    private javax.swing.JList jLista_arboles;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane_arbol_visual;
    private javax.swing.JTextField jt_nodo_padre;
    private javax.swing.JTextField jt_nombre_arbol;
    private javax.swing.JTextField jt_nuevo_nodo;
    // End of variables declaration//GEN-END:variables

}
