
package UI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeCellRenderer;

import modelo.Alumno;
import modelo.Arbol_Perturbacion;
import modelo.Cursada;
import modelo.Evaluacion;
import modelo.Modelo;
import modelo.Modelo_ABM_arbol_perturbacion;

import util.Fecha;

/**
 *
 * @author leonel
 */
public class UI_Alta_Eval extends javax.swing.JInternalFrame
{
    Modelo modelo;
    Modelo_ABM_arbol_perturbacion modelo_abm_ap;
    DefaultListModel lista_alumnos_en_cursada = new DefaultListModel();
    DefaultListModel lista_cursadas = new DefaultListModel();
    DefaultListModel lista_arboles = new DefaultListModel();
    DefaultListModel lista_alumnos_en_evaluacion = new DefaultListModel();
    DefaultListModel listModel_evaluaciones = new DefaultListModel();

    Evaluacion evaluacion_en_uso;
    Arbol_Perturbacion arbol_seleccionado = null;
    Cursada cursada_seleccionada = null;

    /** Creates new form UI_Alta_Eval */
    public UI_Alta_Eval(Modelo modelo)
    {
        initComponents();
        // this.initmiscomponentes();
        //this.inicia_mis_componentes();
        this.modelo = modelo;
        this.modelo_abm_ap = this.modelo.getModelo_abm_ap();
        this.jList_alumnos_en_evaluacion.setModel(this.lista_alumnos_en_evaluacion);
        this.jList_Arboles_Perturbacion.setModel(this.lista_arboles);
        this.JList_alumnosencursada.setModel(this.lista_alumnos_en_cursada);
        this.JList_cursadas.setModel(this.lista_cursadas);
        this.jList_evaluaciones_creadas.setModel(this.listModel_evaluaciones);
        this.jtree_arbol.setModel(null);
        this.limpia_componentes();
        this.actualizar_jList_evaluaciones_creadas();

        this.evaluacion_en_uso = new Evaluacion();
        DefaultTreeCellRenderer render = (DefaultTreeCellRenderer) jtree_arbol.getCellRenderer();
        render.setLeafIcon(new ImageIcon(""));
        render.setOpenIcon(new ImageIcon(""));
        render.setClosedIcon(new ImageIcon(""));
    }

    public void limpia_componentes()
    {
        int proxima_ev;
        this.cargar_jList_Arboles_Perturbacion();
        this.cargar_jList_cursadas();
        this.lista_alumnos_en_evaluacion.clear();
        this.lista_alumnos_en_cursada.clear();
        this.jT_arbol.setText("");
        this.jT_asignatura.setText("");
        this.jT_descripcion.setText("");
        proxima_ev = this.modelo.getModelo_abm_evaluacion().recupera_proxima_evaluacion();
        this.jT_id.setText(String.valueOf(proxima_ev));
        this.jT_fecha.setText("");
    }

    public void cargar_jList_Arboles_Perturbacion()
    {
        this.lista_arboles.clear();
        Iterator lista_ap = this.modelo_abm_ap.getIterator_listado_arboles_perturbacion();
        while (lista_ap.hasNext())
        {
            Arbol_Perturbacion unArbolPerturbacion = (Arbol_Perturbacion) lista_ap.next();
            lista_arboles.addElement(unArbolPerturbacion);
        }
    }

    private void cargar_jList_cursadas()
    {
        this.lista_cursadas.clear();
        this.lista_alumnos_en_cursada.clear();

        Iterator iterator_cursadas = modelo.getModelo_abm_cursada().recuperar_cursadas();
        //Recorrer el contenido del Iterator
        while (iterator_cursadas.hasNext())
        {
            Cursada cur = (Cursada) iterator_cursadas.next();

            this.lista_cursadas.addElement(cur);

        }
    }
    
    private void actualizar_jList_evaluaciones_creadas()
    {
        this.listModel_evaluaciones.clear();
        Iterator iterator_evaluaciones = modelo.getModelo_abm_evaluacion().recuperar_evaluaciones();
        //Recorrer el contenido del Iterator
        while (iterator_evaluaciones.hasNext())
        {
            Evaluacion evaluacion = (Evaluacion) iterator_evaluaciones.next();
            this.listModel_evaluaciones.addElement(evaluacion);
        }
    }

    //EVENTOS
    public void jList_Arboles_PerturbacionMouseClicked()
    {
        Arbol_Perturbacion arbol = (Arbol_Perturbacion) this.jList_Arboles_Perturbacion.getSelectedValue();
        this.evaluacion_en_uso.setArbol_perturbacion(arbol);
        this.jL_nombre_arbol.setText(arbol.getNombre());
        this.jL_Descripcion_Arbol.setText(arbol.getDescripcion().trim());
        this.jtree_arbol.setModel(null);
        this.jtree_arbol.setModel(this.evaluacion_en_uso.getArbol_perturbacion().getTreeModel());
        this.jtree_arbol.paintAll(this.jtree_arbol.getGraphics());
        this.jtree_arbol.expandPath(jtree_arbol.getSelectionPath());
        this.jT_arbol.setText(arbol.getNombre());
        this.arbol_seleccionado = arbol;
    }
    
    public void jButton_borrar_evaluacionesMouseClicked()
    {
        Evaluacion evaluacion_seleccionada = (Evaluacion)jList_evaluaciones_creadas.getSelectedValue();
        int id_evaluacion = evaluacion_seleccionada.getId_evaluacion();
        modelo.getModelo_abm_evaluacion().borrar_evaluacion(id_evaluacion);
        actualizar_jList_evaluaciones_creadas();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents()//GEN-BEGIN:initComponents
    {

        jPanel_cursadas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JList_cursadas = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jPanel_arboles = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList_Arboles_Perturbacion = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jL_nombre_arbol = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jL_Descripcion_Arbol = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtree_arbol = new javax.swing.JTree();
        jPanel_listado_alumnos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JList_alumnosencursada = new javax.swing.JList();
        jButton_agrega = new javax.swing.JButton();
        jButton_quita = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList_alumnos_en_evaluacion = new javax.swing.JList();
        jPanel_listado_evaluaciones_creadas = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jList_evaluaciones_creadas = new javax.swing.JList();
        jButton_borrar_evaluacion = new javax.swing.JButton();
        jButton_limpiar_seleccion = new javax.swing.JButton();
        jButton_guardar_modificacion = new javax.swing.JButton();
        jPanel_detalles_de_evaluacion = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jT_fecha = new javax.swing.JTextPane();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jT_descripcion = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jT_arbol = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jT_asignatura = new javax.swing.JTextPane();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jT_id = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        jButton_Guardar = new javax.swing.JButton();

        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Alta de Evaluación");
        setAutoscrolls(true);
        setMaximumSize(new java.awt.Dimension(1000, 650));
        setMinimumSize(new java.awt.Dimension(1000, 650));
        setPreferredSize(new java.awt.Dimension(1000, 650));
        try
        {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1)
        {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel_cursadas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cursadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 14))); // NOI18N

        JList_cursadas.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        JList_cursadas.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                JList_cursadasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JList_cursadas);

        javax.swing.GroupLayout jPanel_cursadasLayout = new javax.swing.GroupLayout(jPanel_cursadas);
        jPanel_cursadas.setLayout(jPanel_cursadasLayout);
        jPanel_cursadasLayout.setHorizontalGroup(
            jPanel_cursadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
        );
        jPanel_cursadasLayout.setVerticalGroup(
            jPanel_cursadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Alta de Evaluacion");

        jPanel_arboles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Arboles de Perturbacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 14))); // NOI18N

        jLabel2.setText("Listado");

        jLabel3.setText("Arbol seleccionado");

        jList_Arboles_Perturbacion.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList_Arboles_Perturbacion.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jList_Arboles_PerturbacionMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jList_Arboles_Perturbacion);

        jLabel4.setText("Nombre");

        jScrollPane4.setViewportView(jL_nombre_arbol);

        jLabel5.setText("Descripcion");

        jScrollPane5.setViewportView(jL_Descripcion_Arbol);

        jScrollPane6.setViewportView(jtree_arbol);

        javax.swing.GroupLayout jPanel_arbolesLayout = new javax.swing.GroupLayout(jPanel_arboles);
        jPanel_arboles.setLayout(jPanel_arbolesLayout);
        jPanel_arbolesLayout.setHorizontalGroup(
            jPanel_arbolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_arbolesLayout.createSequentialGroup()
                .addGroup(jPanel_arbolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(jPanel_arbolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_arbolesLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)
                        .addContainerGap(236, Short.MAX_VALUE))
                    .addGroup(jPanel_arbolesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6))
                    .addGroup(jPanel_arbolesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_arbolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_arbolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane5)))))
        );
        jPanel_arbolesLayout.setVerticalGroup(
            jPanel_arbolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_arbolesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel_arbolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_arbolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_arbolesLayout.createSequentialGroup()
                        .addGroup(jPanel_arbolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_arbolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addGap(5, 5, 5))
        );

        jPanel_listado_alumnos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Listado de Alumnos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 14))); // NOI18N

        JList_alumnosencursada.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(JList_alumnosencursada);

        jButton_agrega.setText(">");
        jButton_agrega.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_agregaMouseClicked(evt);
            }
        });

        jButton_quita.setText("<");
        jButton_quita.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_quitaMouseClicked(evt);
            }
        });
        jButton_quita.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_quitaActionPerformed(evt);
            }
        });

        jList_alumnos_en_evaluacion.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane7.setViewportView(jList_alumnos_en_evaluacion);

        javax.swing.GroupLayout jPanel_listado_alumnosLayout = new javax.swing.GroupLayout(jPanel_listado_alumnos);
        jPanel_listado_alumnos.setLayout(jPanel_listado_alumnosLayout);
        jPanel_listado_alumnosLayout.setHorizontalGroup(
            jPanel_listado_alumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_listado_alumnosLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_listado_alumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_agrega, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_quita, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel_listado_alumnosLayout.setVerticalGroup(
            jPanel_listado_alumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_listado_alumnosLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jButton_agrega)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_quita)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
            .addComponent(jScrollPane7)
        );

        jPanel_listado_evaluaciones_creadas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Listado de Evaluaciones creadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 14))); // NOI18N

        jList_evaluaciones_creadas.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane13.setViewportView(jList_evaluaciones_creadas);

        jButton_borrar_evaluacion.setText("Borrar");
        jButton_borrar_evaluacion.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_borrar_evaluacionMouseClicked(evt);
            }
        });

        jButton_limpiar_seleccion.setText("Limpiar");

        jButton_guardar_modificacion.setText("Actualizar evaluacion");

        javax.swing.GroupLayout jPanel_listado_evaluaciones_creadasLayout = new javax.swing.GroupLayout(jPanel_listado_evaluaciones_creadas);
        jPanel_listado_evaluaciones_creadas.setLayout(jPanel_listado_evaluaciones_creadasLayout);
        jPanel_listado_evaluaciones_creadasLayout.setHorizontalGroup(
            jPanel_listado_evaluaciones_creadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_listado_evaluaciones_creadasLayout.createSequentialGroup()
                .addComponent(jScrollPane13)
                .addGap(5, 5, 5))
            .addGroup(jPanel_listado_evaluaciones_creadasLayout.createSequentialGroup()
                .addComponent(jButton_borrar_evaluacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_limpiar_seleccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jButton_guardar_modificacion)
                .addContainerGap())
        );
        jPanel_listado_evaluaciones_creadasLayout.setVerticalGroup(
            jPanel_listado_evaluaciones_creadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_listado_evaluaciones_creadasLayout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_listado_evaluaciones_creadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_borrar_evaluacion)
                    .addGroup(jPanel_listado_evaluaciones_creadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_limpiar_seleccion)
                        .addComponent(jButton_guardar_modificacion))))
        );

        jPanel_detalles_de_evaluacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalles de la Evaluación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 14))); // NOI18N

        jScrollPane12.setViewportView(jT_fecha);

        jLabel10.setText("Fecha: dd/mm/AAAA");

        jScrollPane11.setViewportView(jT_descripcion);

        jLabel9.setText("Descripcion de la evaluación");

        jScrollPane10.setViewportView(jT_arbol);

        jLabel8.setText("Arbol de perturbacion");

        jScrollPane9.setViewportView(jT_asignatura);

        jLabel7.setText("asignatura");

        jScrollPane8.setViewportView(jT_id);

        jLabel6.setText("identificacion");

        javax.swing.GroupLayout jPanel_detalles_de_evaluacionLayout = new javax.swing.GroupLayout(jPanel_detalles_de_evaluacion);
        jPanel_detalles_de_evaluacion.setLayout(jPanel_detalles_de_evaluacionLayout);
        jPanel_detalles_de_evaluacionLayout.setHorizontalGroup(
            jPanel_detalles_de_evaluacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_detalles_de_evaluacionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_detalles_de_evaluacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );
        jPanel_detalles_de_evaluacionLayout.setVerticalGroup(
            jPanel_detalles_de_evaluacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_detalles_de_evaluacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jButton_Guardar.setText("Crear Evaluación");
        jButton_Guardar.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_GuardarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel_cursadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel_listado_alumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel_detalles_de_evaluacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel_listado_evaluaciones_creadas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel_arboles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButton_Guardar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel_listado_alumnos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_cursadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel_arboles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jButton_Guardar)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel_detalles_de_evaluacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_listado_evaluaciones_creadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }//GEN-END:initComponents

    private void jButton_quitaActionPerformed(java.awt.event.ActionEvent evt) //GEN-FIRST:event_jButton_quitaActionPerformed
        //GEN-HEADER    private void jList_Arboles_PerturbacionMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jList_Arboles_PerturbacionMouseClicked//GEN-HEADEREND:event_jButton_quitaActionPerformed
    { //GEN-HEADEREND:event_jList_Arboles_PerturbacionMouseClicked//GEN-LAST:event_jButton_quitaActionPerformed
        // TODO add your handling code here://GEN-HEADEREND:event_jList_Arboles_PerturbacionMouseClicked
        Arbol_Perturbacion arbol = (Arbol_Perturbacion) this.jList_Arboles_Perturbacion.getSelectedValue();
        this.evaluacion_en_uso.setArbol_perturbacion(arbol);
        this.jL_nombre_arbol.setText(arbol.getNombre());
        this.jL_Descripcion_Arbol.setText(arbol.getDescripcion().trim());
        this.jtree_arbol.setModel(null);
        this.jtree_arbol.setModel(this.evaluacion_en_uso.getArbol_perturbacion().getTreeModel());
        this.jtree_arbol.paintAll(this.jtree_arbol.getGraphics());
        this.jtree_arbol.expandPath(jtree_arbol.getSelectionPath());
        this.jT_arbol.setText(arbol.getNombre());
        this.arbol_seleccionado = arbol;
    } //GEN-LAST:event_jList_Arboles_PerturbacionMouseClicked

    private void JList_cursadasMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_JList_cursadasMouseClicked
    {//GEN-HEADEREND:event_JList_cursadasMouseClicked
        // TODO add your handling code here:
        Cursada actual = (Cursada) this.JList_cursadas.getSelectedValue();
        this.lista_alumnos_en_cursada.clear();
        this.lista_alumnos_en_evaluacion.clear();
        Iterator iterator_alumnos = actual.getAlumnos().iterator();
        //Recorrer el contenido del Iterator
        while (iterator_alumnos.hasNext())
        {
            Alumno al = (Alumno) iterator_alumnos.next();
            this.lista_alumnos_en_cursada.addElement(al);

        }
        this.jT_asignatura.setText(actual.getAsignatura());
        this.cursada_seleccionada = actual;
        
    }//GEN-LAST:event_JList_cursadasMouseClicked

    private void jButton_agregaMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_agregaMouseClicked
    {//GEN-HEADEREND:event_jButton_agregaMouseClicked
        // TODO add your handling code here:
        List<Alumno> l = this.JList_alumnosencursada.getSelectedValuesList();
        for (int i = 0; i < l.size(); i++)
        {
            this.lista_alumnos_en_evaluacion.addElement(l.get(i));
            this.lista_alumnos_en_cursada.removeElement(l.get(i));
        }
    }//GEN-LAST:event_jButton_agregaMouseClicked

    private void jButton_quitaMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_quitaMouseClicked
    {//GEN-HEADEREND:event_jButton_quitaMouseClicked
        // TODO add your handling code here:
        List<Alumno> l = this.jList_alumnos_en_evaluacion.getSelectedValuesList();
        for (int i = 0; i < l.size(); i++)
        {
            this.lista_alumnos_en_evaluacion.removeElement(l.get(i));
            this.lista_alumnos_en_cursada.addElement(l.get(i));
        }
    }//GEN-LAST:event_jButton_quitaMouseClicked

    private void jButton_GuardarMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_GuardarMouseClicked
    {//GEN-HEADEREND:event_jButton_GuardarMouseClicked
        // TODO add your handling code here:
        this.evaluacion_en_uso.setFecha(Fecha.str2Date(jT_fecha.getText().trim()));
        this.evaluacion_en_uso.setDescripcion(this.jT_descripcion.getText());
        this.evaluacion_en_uso.setArbol_perturbacion(this.arbol_seleccionado);
        this.evaluacion_en_uso.setCursada(this.cursada_seleccionada);
        this.evaluacion_en_uso.setId_evaluacion(Integer.parseInt(this.jT_id.getText()));
        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
        if (this.arbol_seleccionado != null && this.cursada_seleccionada != null &&
            this.lista_alumnos_en_evaluacion.size() > 0)
        {
            for (int i = 0; i < this.lista_alumnos_en_evaluacion.size(); i++)
            {
                alumnos.add((Alumno) this.lista_alumnos_en_evaluacion.get(i));
            }
            this.evaluacion_en_uso.setAlumnos_evaluados(alumnos);
            this.modelo.getModelo_abm_evaluacion().agrega_evaluacion(evaluacion_en_uso);
            this.limpia_componentes();
        }
        this.listModel_evaluaciones.addElement(this.evaluacion_en_uso);
        this.actualizar_jList_evaluaciones_creadas();
    }//GEN-LAST:event_jButton_GuardarMouseClicked

    private void jList_Arboles_PerturbacionMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jList_Arboles_PerturbacionMouseClicked
    {//GEN-HEADEREND:event_jList_Arboles_PerturbacionMouseClicked
        // TODO add your handling code here:
        jList_Arboles_PerturbacionMouseClicked();
    }//GEN-LAST:event_jList_Arboles_PerturbacionMouseClicked

    private void jButton_borrar_evaluacionMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_borrar_evaluacionMouseClicked
    {//GEN-HEADEREND:event_jButton_borrar_evaluacionMouseClicked
        // TODO add your handling code here:
        jButton_borrar_evaluacionesMouseClicked();
    }//GEN-LAST:event_jButton_borrar_evaluacionMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList JList_alumnosencursada;
    private javax.swing.JList JList_cursadas;
    private javax.swing.JButton jButton_Guardar;
    private javax.swing.JButton jButton_agrega;
    private javax.swing.JButton jButton_borrar_evaluacion;
    private javax.swing.JButton jButton_guardar_modificacion;
    private javax.swing.JButton jButton_limpiar_seleccion;
    private javax.swing.JButton jButton_quita;
    private javax.swing.JTextPane jL_Descripcion_Arbol;
    private javax.swing.JTextPane jL_nombre_arbol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList_Arboles_Perturbacion;
    private javax.swing.JList jList_alumnos_en_evaluacion;
    private javax.swing.JList jList_evaluaciones_creadas;
    private javax.swing.JPanel jPanel_arboles;
    private javax.swing.JPanel jPanel_cursadas;
    private javax.swing.JPanel jPanel_detalles_de_evaluacion;
    private javax.swing.JPanel jPanel_listado_alumnos;
    private javax.swing.JPanel jPanel_listado_evaluaciones_creadas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextPane jT_arbol;
    private javax.swing.JTextPane jT_asignatura;
    private javax.swing.JTextPane jT_descripcion;
    private javax.swing.JTextPane jT_fecha;
    private javax.swing.JTextPane jT_id;
    private javax.swing.JTree jtree_arbol;
    // End of variables declaration//GEN-END:variables

}
