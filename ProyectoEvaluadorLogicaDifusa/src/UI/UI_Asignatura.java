
package UI;

import arbol_visual.ArbolVisual;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import modelo.Arbol_Perturbacion;
import modelo.Asignatura;
import modelo.Modelo;
import modelo.Modelo_ABM_Asignatura;
import modelo.Nodo_Perturbacion;

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
    //Modelo_ABM_arbol_perturbacion modelo_abm_ap;
    Modelo_ABM_Asignatura modelo_abm_asignatura;

    /**modelo vinculado a JList_asignatura
     * listado de asignaturas creadas
     */
    DefaultListModel listModel_asignaturas = new DefaultListModel();
    Asignatura asignatura_en_uso = null;

    ArbolVisual jtree_arbol;

    /**modificar es true: si se habilita para modificar datos de una asignatura previamente ingresada
     * modificar es false: si se habilita para crear una nueva asignaturar
     * */
    private boolean modificar = true;

    //Arbol_Perturbacion ap_en_uso = null;

    private MouseAdapter mouseA;

    /** Creates new form UI_Asignatura 
     * Se referencia al objeto modelo
     * */
    public UI_Asignatura(Modelo modelo)
    {
        this.setMinimumSize(new Dimension(1100,850));
        this.setMaximumSize(new Dimension(1100,850));
        this.setPreferredSize(new Dimension(1100,850));
        initComponents();
        this.modelo = modelo;
        this.modelo_abm_asignatura = modelo.getModelo_abm_asignatura();
        this.jList_asignaturas.setModel(listModel_asignaturas);
        this.mouseA = new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                DefaultMutableTreeNode dmtn = jtree_arbol.getNodoSeleccionado();
                if (dmtn != null)
                {
                    jTextField_padre.setText(dmtn.toString());
                }
            }
        };
        jtree_arbol = (ArbolVisual) jScrollPane_jTreeVisual;
        jtree_arbol.setSize(796, 566);
        //jPanel_arbol_visual.setSize(796, 566);
        this.asignatura_en_uso = null;
        this.limpiar_jList_asignaturas();
        this.cargar_jList_asignaturas();
        this.limpiar_jTree();
        this.jtree_arbol.setLineasRectas(true);
        //this.cambiar_iconos_jtree();
    }

    public void limpiar_zona_jText_asignaturas()
    {
        this.jTextField_codigo_asignatura.setText("");
        this.jTextField_nombre_asignatura.setText("");
        this.jTextField_nombre_arbol_dominio.setText("");
    }

    public void setEnabled_zona_jText_asignaturas(boolean v)
    {
        this.jTextField_codigo_asignatura.setEnabled(v);
        this.jTextField_nombre_asignatura.setEnabled(v);
        this.jTextField_nombre_arbol_dominio.setEnabled(v);
    }

    public void setEditable_zona_jText_asignaturas(boolean v)
    {
        this.jTextField_codigo_asignatura.setEditable(v);
        this.jTextField_nombre_asignatura.setEditable(v);
        this.jTextField_nombre_arbol_dominio.setEditable(v);
    }

    /**setea componente visuales para cargar la raiz del arbol
     */
    private void habilitar_cargar_raiz()
    {
        this.jLabel_nuevo.setText("RAIZ");
        this.jTextField_padre.setEnabled(false);
        this.jLabel_padre.setVisible(false);
        this.jTextField_nuevo.setVisible(true);
        this.jButton_agregar_nuevo_nodo.setText("RAIZ");
    }

    private void habilitar_cargar_nodos_hijos()
    {
        this.jLabel_padre.setText("Padre");
        this.jLabel_nuevo.setText("Nuevo");
        this.jLabel_padre.setVisible(true);
        this.jLabel_nuevo.setVisible(true);
        this.jTextField_padre.setEnabled(false);
        this.jTextField_nuevo.setEnabled(true);
        this.jTextField_padre.setVisible(true);
        this.jTextField_nuevo.setVisible(true);
        this.jButton_agregar_nuevo_nodo.setText("Agregar Nuevo");
        this.jtree_arbol.setModel(this.asignatura_en_uso.getArbol_dominio().getTreeModel());
    }

    /* public void limpiar_creacion_arbol()
    {
        jArea_descripcion_arbol.setText("");
        jt_nombre_arbol.setText("");
    } */

    public void limpiar_jTree()
    {
        this.jtree_arbol.setModel(null);
    }

    public void cargar_jTree()
    {
        //jtree_arbol.setModel(this.ap_en_uso.getTreeModel()); //vincula el jtree con su DefaultTreeModel
        DefaultTreeModel dtm = this.asignatura_en_uso.getArbol_dominio().getTreeModel();
        //jtree_arbol.setearParametrosArbolVisual(dtm);
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


    /**almacena nuevamente la asignatura incorporando el árbol de perturbación
     */
    public void bt_guardar_arbolActionPerformed()
    {
        this.modelo_abm_asignatura.actualizar_arbol_perturbacion(this.asignatura_en_uso);
        this.jTextField_padre.setText("");
        this.jTextField_nuevo.setText("");
        //TODO: hacer disable el árbol luego de guardarlo. Luego hacerlo enable al agregar o eliminar un nodo.
        this.limpiar_jTree();
    }

    public void jButton_eliminar_nodo()
    {
        DefaultMutableTreeNode nodoSeleccionado = jtree_arbol.getNodoSeleccionado();
        DefaultTreeModel dtm = (DefaultTreeModel) this.jtree_arbol.getModel();
        if (nodoSeleccionado != null)
        {
            if (!nodoSeleccionado.isRoot())
            {
                dtm.removeNodeFromParent(nodoSeleccionado);
            }
            else
            {
                this.eliminar_arbol_completo();
            }
        }
    }

    private void eliminar_arbol_completo()
    {
        this.asignatura_en_uso.setArbol_dominio(null);
        this.jtree_arbol.setModel(null);
        jtree_arbol.paintAll(this.jtree_arbol.getGraphics());
        this.habilitar_cargar_raiz();
    }

    private void bt_raizActionPerformed() // se crea el árbol de dominio para una asignatura nueva.
    {
        //if (this.asignatura_en_uso.getArbol_dominio() == null) se cumple esta condición
        String id_nuevo_nodo = jTextField_nuevo.getText().trim();
        Arbol_Perturbacion arbol_dominio =
            new Arbol_Perturbacion(jTextField_nombre_arbol_dominio.getText().trim(), "< sin descripción >");
        arbol_dominio.setRaiz(jTextField_nuevo.getText().trim());
        this.asignatura_en_uso.setArbol_dominio(arbol_dominio);
        
        jtree_arbol.setModel(this.asignatura_en_uso.getArbol_dominio().getTreeModel());

        this.habilitar_cargar_nodos_hijos();
        
        //TODO: probar esto en el constructor
        jtree_arbol.addMouseListener(mouseA);

        this.jTextField_nuevo.setText("");
        this.jTextField_padre.setText("");
    }

    private void bt_nuevo_nodoActionPerformed()
    {
        String id_nuevo_nodo = jTextField_nuevo.getText().trim();
        //if (this.asignatura_en_uso.getArbol_dominio() != null) se cumple esta condición
        Nodo_Perturbacion nodo_seleccionado_padre = (Nodo_Perturbacion) jtree_arbol.getLastSelectedPathComponent();
        String idNodo_padre = (nodo_seleccionado_padre.getDato()).getIdDato();
        jTextField_padre.setText(idNodo_padre);
        this.asignatura_en_uso.getArbol_dominio().agregarNodo(nodo_seleccionado_padre,
                                                              this.jTextField_nuevo.getText().trim());
        jtree_arbol.paintAll(this.jtree_arbol.getGraphics());
        jtree_arbol.expandPath(this.jtree_arbol.getSelectionPath());

        this.jTextField_nuevo.setText("");
        this.jTextField_padre.setText("");
    }

    public void limpiar_jList_asignaturas()
    {
        listModel_asignaturas.clear();
    }

    public void cargar_jList_asignaturas()
    {
        Iterator iterator_asignaturas = modelo_abm_asignatura.getIterator_listado_asignaturas();
        //Recorrer el contenido del Iterator
        while (iterator_asignaturas.hasNext())
        {
            Asignatura asignatura_ste = (Asignatura) iterator_asignaturas.next();
            listModel_asignaturas.addElement(asignatura_ste);
        }
    }

    //EVENTOS
    private void jButton_registrar_asignaturaMouseClicked()
    {
        String codigo_asignatura = jTextField_codigo_asignatura.getText().trim();
        String nombre_asignatura = jTextField_nombre_asignatura.getText().trim();
        String nombre_arbol_dominio = jTextField_nombre_arbol_dominio.getText().trim();
        this.asignatura_en_uso = new Asignatura();
        this.asignatura_en_uso.setCodigo(codigo_asignatura);
        this.asignatura_en_uso.setNombre(nombre_asignatura);
        this.asignatura_en_uso.setArbol_dominio(null);
        this.modelo_abm_asignatura.almacenar_asignatura(this.asignatura_en_uso);
        this.limpiar_jList_asignaturas();
        this.cargar_jList_asignaturas();
        this.jList_asignaturas.setSelectedValue(this.asignatura_en_uso, true);
        //preparar zona arbol para la creación de uno nuevo
        this.habilitar_cargar_raiz();
    }
    
    private void jButton_cancelarKeyPressed()
    {
        this.jTextField_codigo_asignatura.setText("");
        this.jTextField_nombre_asignatura.setText("");
        this.jTextField_nombre_arbol_dominio.setText("");
    }

    private void jList_asignaturasMouseClicked()
    {
        //this.limpiar_jTree();
        this.asignatura_en_uso = (Asignatura) jList_asignaturas.getSelectedValue();
        DefaultTreeModel treeModelSeleccionado = this.asignatura_en_uso.getArbol_dominio().getTreeModel();
        if(treeModelSeleccionado!=null)
            jtree_arbol.setModel(treeModelSeleccionado);
    }

    private void jButton_habilitar_modificacion_asignatura()
    {
        if(this.asignatura_en_uso!=null)
        {
            this.setEnabled_zona_jText_asignaturas(true);
            this.jTextField_codigo_asignatura.setText(this.asignatura_en_uso.getCodigo());
            this.jTextField_nombre_asignatura.setText(this.asignatura_en_uso.getNombre());
            if (this.asignatura_en_uso.getArbol_dominio() != null)
            {
                jTextField_nombre_arbol_dominio.setText(this.asignatura_en_uso.getArbol_dominio().getNombre());
                this.habilitar_cargar_nodos_hijos();
            }
            else
            {
                this.habilitar_cargar_raiz();
            }
        }
    }
    
    private void jButton_eliminar_asignaturaMouseClicked()
    {
        this.asignatura_en_uso.setArbol_dominio(null);
        this.modelo_abm_asignatura.borrar_asignatura(this.asignatura_en_uso);
        this.limpiar_jList_asignaturas();
        this.cargar_jList_asignaturas();
        if(jList_asignaturas.getModel().getSize()>0)
        {
            jList_asignaturas.setSelectedIndex(0);
        }
        //preparar zona arbol para la creación de uno nuevo
        this.habilitar_cargar_raiz();
        this.asignatura_en_uso = null;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents()//GEN-BEGIN:initComponents
    {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList_asignaturas = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_nombre_asignatura = new javax.swing.JTextField();
        jTextField_codigo_asignatura = new javax.swing.JTextField();
        jButton_registrar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField_nombre_arbol_dominio = new javax.swing.JTextField();
        jButton_cancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane_jTreeVisual = new ArbolVisual();
        jLabel_padre = new javax.swing.JLabel();
        jLabel_nuevo = new javax.swing.JLabel();
        jTextField_padre = new javax.swing.JTextField();
        jTextField_nuevo = new javax.swing.JTextField();
        jButton_agregar_nuevo_nodo = new javax.swing.JButton();
        jButton_guardar_ap = new javax.swing.JButton();
        jButton_eliminar_nodo = new javax.swing.JButton();
        jButton_eliminar_asignatura = new javax.swing.JButton();
        jButton_habilitar_modificacion_asignatura = new javax.swing.JButton();
        jButton_habilitar_nueva_asignatura = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Asignaturas");
        setMaximumSize(new java.awt.Dimension(950, 650));
        setMinimumSize(new java.awt.Dimension(950, 650));
        setName("Asignaturas"); // NOI18N
        setPreferredSize(new java.awt.Dimension(950, 650));
        try
        {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1)
        {
            e1.printStackTrace();
        }
        setVisible(true);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Gestión de Asignaturas");

        jList_asignaturas.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Asignaturas"));
        jList_asignaturas.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList_asignaturas.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jList_asignaturasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList_asignaturas);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Crear o Modificar una Asignatura"));

        jLabel2.setText("Nombre:");

        jLabel3.setText("Código:");

        jButton_registrar.setText("Registrar");
        jButton_registrar.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_registrarMouseClicked(evt);
            }
        });

        jLabel4.setText("Dominio: ");
        jLabel4.setToolTipText("Nombre del árbol de dominio");

        jButton_cancelar.setText("Cancelar");
        jButton_cancelar.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_cancelarMouseClicked(evt);
            }
        });
        jButton_cancelar.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                jButton_cancelarKeyPressed(evt);
            }
        });

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
                    .addComponent(jTextField_nombre_arbol_dominio, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField_codigo_asignatura, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField_nombre_asignatura, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_cancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
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
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_cancelar)
                    .addComponent(jButton_registrar))
                .addGap(10, 10, 10))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Alta de Árbol de Dominio"));

        jScrollPane_jTreeVisual.setBorder(javax.swing.BorderFactory.createTitledBorder("Árbol de Dominio"));

        jLabel_padre.setText("Padre:");

        jLabel_nuevo.setText("Nuevo:");

        jButton_agregar_nuevo_nodo.setText("Agregar nuevo");
        jButton_agregar_nuevo_nodo.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_agregar_nuevo_nodoMouseClicked(evt);
            }
        });

        jButton_guardar_ap.setText("Guardar Árbol");
        jButton_guardar_ap.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_guardar_apMouseClicked(evt);
            }
        });

        jButton_eliminar_nodo.setText("Eliminar Nodo");
        jButton_eliminar_nodo.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_eliminar_nodoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_guardar_ap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_eliminar_nodo)
                .addGap(26, 26, 26))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane_jTreeVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel_padre)
                        .addGap(3, 3, 3)
                        .addComponent(jTextField_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_nuevo)
                        .addGap(1, 1, 1)
                        .addComponent(jTextField_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_agregar_nuevo_nodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_padre)
                    .addComponent(jTextField_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_nuevo)
                    .addComponent(jTextField_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_agregar_nuevo_nodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane_jTreeVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_guardar_ap)
                    .addComponent(jButton_eliminar_nodo)))
        );

        jButton_eliminar_asignatura.setText("Eliminar");
        jButton_eliminar_asignatura.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_eliminar_asignaturaMouseClicked(evt);
            }
        });

        jButton_habilitar_modificacion_asignatura.setBackground(new java.awt.Color(255, 255, 153));
        jButton_habilitar_modificacion_asignatura.setText("Habilitar Modificación");
        jButton_habilitar_modificacion_asignatura.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_habilitar_modificacion_asignaturaMouseClicked(evt);
            }
        });

        jButton_habilitar_nueva_asignatura.setBackground(new java.awt.Color(0, 255, 102));
        jButton_habilitar_nueva_asignatura.setText("Habilitar Nueva Asignatura");
        jButton_habilitar_nueva_asignatura.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_habilitar_nueva_asignaturaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton_habilitar_nueva_asignatura)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jButton_eliminar_asignatura)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_habilitar_modificacion_asignatura))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_eliminar_asignatura)
                            .addComponent(jButton_habilitar_modificacion_asignatura))
                        .addGap(18, 18, 18)
                        .addComponent(jButton_habilitar_nueva_asignatura)
                        .addGap(38, 38, 38)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
        );

        pack();
    }//GEN-END:initComponents

    private void jButton_registrarMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_registrarMouseClicked
    {//GEN-HEADEREND:event_jButton_registrarMouseClicked
        jButton_registrar_asignaturaMouseClicked();
    }//GEN-LAST:event_jButton_registrarMouseClicked

    private void jButton_guardar_apMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_guardar_apMouseClicked
    {//GEN-HEADEREND:event_jButton_guardar_apMouseClicked
        bt_guardar_arbolActionPerformed();
    }//GEN-LAST:event_jButton_guardar_apMouseClicked

    private void jButton_habilitar_nueva_asignaturaMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_habilitar_nueva_asignaturaMouseClicked
    {//GEN-HEADEREND:event_jButton_habilitar_nueva_asignaturaMouseClicked
        this.limpiar_zona_jText_asignaturas();
        this.jTextField_codigo_asignatura.requestFocus();
        this.modificar = false;
    }//GEN-LAST:event_jButton_habilitar_nueva_asignaturaMouseClicked

    private void jButton_habilitar_modificacion_asignaturaMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_habilitar_modificacion_asignaturaMouseClicked
    {//GEN-HEADEREND:event_jButton_habilitar_modificacion_asignaturaMouseClicked
        jButton_habilitar_modificacion_asignatura();
    }//GEN-LAST:event_jButton_habilitar_modificacion_asignaturaMouseClicked

    private void jButton_agregar_nuevo_nodoMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_agregar_nuevo_nodoMouseClicked
    {//GEN-HEADEREND:event_jButton_agregar_nuevo_nodoMouseClicked
        
        if (this.asignatura_en_uso.getArbol_dominio() == null)
        {
            this.bt_raizActionPerformed();
        }
        else
        {
            this.bt_nuevo_nodoActionPerformed();
        }
    }//GEN-LAST:event_jButton_agregar_nuevo_nodoMouseClicked

    private void jButton_eliminar_nodoMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_eliminar_nodoMouseClicked
    {//GEN-HEADEREND:event_jButton_eliminar_nodoMouseClicked
        jButton_eliminar_nodo();
    }//GEN-LAST:event_jButton_eliminar_nodoMouseClicked

    private void jList_asignaturasMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jList_asignaturasMouseClicked
    {//GEN-HEADEREND:event_jList_asignaturasMouseClicked
        jList_asignaturasMouseClicked();
    }//GEN-LAST:event_jList_asignaturasMouseClicked

    private void jButton_eliminar_asignaturaMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_eliminar_asignaturaMouseClicked
    {//GEN-HEADEREND:event_jButton_eliminar_asignaturaMouseClicked
        this.jButton_eliminar_asignaturaMouseClicked();
    }//GEN-LAST:event_jButton_eliminar_asignaturaMouseClicked

    private void jButton_cancelarKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jButton_cancelarKeyPressed
    {//GEN-HEADEREND:event_jButton_cancelarKeyPressed
        this.jButton_cancelarKeyPressed();
    }//GEN-LAST:event_jButton_cancelarKeyPressed

    private void jButton_cancelarMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_cancelarMouseClicked
    {//GEN-HEADEREND:event_jButton_cancelarMouseClicked
        this.jButton_cancelarKeyPressed();
    }//GEN-LAST:event_jButton_cancelarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_agregar_nuevo_nodo;
    private javax.swing.JButton jButton_cancelar;
    private javax.swing.JButton jButton_eliminar_asignatura;
    private javax.swing.JButton jButton_eliminar_nodo;
    private javax.swing.JButton jButton_guardar_ap;
    private javax.swing.JButton jButton_habilitar_modificacion_asignatura;
    private javax.swing.JButton jButton_habilitar_nueva_asignatura;
    private javax.swing.JButton jButton_registrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_nuevo;
    private javax.swing.JLabel jLabel_padre;
    private javax.swing.JList jList_asignaturas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane_jTreeVisual;
    private javax.swing.JTextField jTextField_codigo_asignatura;
    private javax.swing.JTextField jTextField_nombre_arbol_dominio;
    private javax.swing.JTextField jTextField_nombre_asignatura;
    private javax.swing.JTextField jTextField_nuevo;
    private javax.swing.JTextField jTextField_padre;
    // End of variables declaration//GEN-END:variables

}
