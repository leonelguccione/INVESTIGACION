
package vista;

import java.sql.SQLException;

import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import modelo.Alumno;
import modelo.Modelo;

/**
 *
 * @author guille
 */


public class UI_Alta_Alumno extends javax.swing.JInternalFrame {
    private static final int CONSULTA = 0;
    private static final int MODIFICACION = 1;
    private static final int AGREGAR = 2;
    private static final int BORRADO = 3;


    private Modelo modelo;
    private DefaultListModel listModel = new DefaultListModel();
    private Alumno alumno_seleccionado = null;
    private int modo = UI_Alta_Alumno.CONSULTA;


    /** Creates new form UI_Alta_Alumno */
    @SuppressWarnings("unchecked")
    public UI_Alta_Alumno(Modelo modelo) {
        initComponents();
        this.modelo = modelo;
        this.jList_Alumnos.setModel(listModel);
        this.actualizar_jList();
        this.setEnabled_jtext(false);
        this.limpia_jText();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents()//GEN-BEGIN:initComponents
    {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList_Alumnos = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jButton_Agregar = new javax.swing.JButton();
        jText_Dni = new javax.swing.JTextField();
        jText_Legajo = new javax.swing.JTextField();
        jText_Apellido = new javax.swing.JTextField();
        jText_Nombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton_Modificar = new javax.swing.JButton();
        jButton_Borrar = new javax.swing.JButton();
        jButton_Cancelar = new javax.swing.JButton();
        jButton_Aceptar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Gestión de Alumnos");
        setNormalBounds(new java.awt.Rectangle(0, 0, 950, 700));
        setPreferredSize(new java.awt.Dimension(950, 700));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Listado de Alumnos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 18))); // NOI18N

        jList_Alumnos.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                jList_AlumnosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList_Alumnos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de un alumno", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 18))); // NOI18N

        jButton_Agregar.setText("Agregar");
        jButton_Agregar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_AgregarActionPerformed(evt);
            }
        });

        jLabel1.setText("DNI:");

        jLabel2.setText("Legajo:");

        jLabel3.setText("Apellido:");

        jLabel4.setText("Nombre:");

        jButton_Modificar.setText("Modificar");
        jButton_Modificar.setEnabled(false);
        jButton_Modificar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_ModificarActionPerformed(evt);
            }
        });

        jButton_Borrar.setText("Borrar");
        jButton_Borrar.setEnabled(false);
        jButton_Borrar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_BorrarActionPerformed(evt);
            }
        });

        jButton_Cancelar.setText("Cancelar");
        jButton_Cancelar.setEnabled(false);
        jButton_Cancelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_CancelarActionPerformed(evt);
            }
        });

        jButton_Aceptar.setText("Aceptar");
        jButton_Aceptar.setEnabled(false);
        jButton_Aceptar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_AceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_Agregar)
                        .addGap(41, 41, 41)
                        .addComponent(jButton_Modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jText_Legajo)
                            .addComponent(jText_Apellido)
                            .addComponent(jText_Nombre)
                            .addComponent(jText_Dni)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jButton_Aceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Cancelar)
                        .addGap(80, 80, 80)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jText_Dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jText_Legajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jText_Apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jText_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Agregar)
                    .addComponent(jButton_Modificar)
                    .addComponent(jButton_Borrar))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Cancelar)
                    .addComponent(jButton_Aceptar)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }//GEN-END:initComponents

    private void jButton_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AgregarActionPerformed
        // TODO add your handling code here:
        this.setEnabledAceptarCancelar(true);
        this.modo = UI_Alta_Alumno.AGREGAR;
        this.limpia_jText();
        this.setEnabled_jtext(true);
    }//GEN-LAST:event_jButton_AgregarActionPerformed

    private void jButton_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelarActionPerformed
        // TODO add your handling code here:
        this.consultar();
        
        
        
    }//GEN-LAST:event_jButton_CancelarActionPerformed

    private void jButton_BorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BorrarActionPerformed
        this.setEnabledAceptarCancelar(true);
        this.modo = UI_Alta_Alumno.BORRADO;
    }//GEN-LAST:event_jButton_BorrarActionPerformed

    private void jButton_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ModificarActionPerformed
        this.setEnabledAceptarCancelar(true);
        this.modo = UI_Alta_Alumno.MODIFICACION;
        this.setEnabled_jtext(true);
        this.jText_Dni.setEnabled(false);
        
    }//GEN-LAST:event_jButton_ModificarActionPerformed

    private void jButton_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AceptarActionPerformed
        switch (this.modo) {
        case UI_Alta_Alumno.BORRADO:
            this.borrar();

            break;
        case UI_Alta_Alumno.AGREGAR:
            this.agregar();

            break;
        case UI_Alta_Alumno.MODIFICACION:
            this.modificar();

            break;
        }
        this.consultar();
        
        
    }//GEN-LAST:event_jButton_AceptarActionPerformed

    private void jList_AlumnosValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_jList_AlumnosValueChanged
    {//GEN-HEADEREND:event_jList_AlumnosValueChanged
    if (!this.listModel.isEmpty()) {

        if (this.jList_Alumnos.getSelectedValue() != null) {
            this.alumno_seleccionado = (Alumno) this.jList_Alumnos.getSelectedValue();
            this.jText_Apellido.setText(this.alumno_seleccionado.getApellido());
            this.jText_Nombre.setText(this.alumno_seleccionado.getNombre());
            this.jText_Dni.setText(String.valueOf(this.alumno_seleccionado.getDni()));
            this.jText_Legajo.setText(String.valueOf(this.alumno_seleccionado.getLegajo()));
        }
    }
    this.verificaEnabled();
    }//GEN-LAST:event_jList_AlumnosValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Aceptar;
    private javax.swing.JButton jButton_Agregar;
    private javax.swing.JButton jButton_Borrar;
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Modificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList_Alumnos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jText_Apellido;
    private javax.swing.JTextField jText_Dni;
    private javax.swing.JTextField jText_Legajo;
    private javax.swing.JTextField jText_Nombre;
    // End of variables declaration//GEN-END:variables

    private void setEnabled_jtext(boolean parametro) {
        this.jText_Apellido.setEnabled(parametro);
        this.jText_Nombre.setEnabled(parametro);
        this.jText_Dni.setEnabled(parametro);
        this.jText_Legajo.setEnabled(parametro);
    }


    private void limpia_jText() {
        this.jText_Apellido.setText("");
        this.jText_Nombre.setText("");
        this.jText_Dni.setText("");
        this.jText_Legajo.setText("");
    }


    private void actualizar_jList() {
        listModel.clear();

        // Iterator iterator_alumnos = modelo.getModelo_abm_alumno().get_lista_alumnos();
        Iterator iterator_alumnos = modelo.getAlumnos()
                                          .values()
                                          .iterator();
        while (iterator_alumnos.hasNext()) {
            Alumno al = (Alumno) iterator_alumnos.next();
            listModel.addElement(al);
        }
    }


    private void verificaEnabled() {
        this.jButton_Borrar.setEnabled(this.alumno_seleccionado != null);
        this.jButton_Modificar.setEnabled(this.alumno_seleccionado != null);

    }


    private void borrar() {
        String st_dni = this.jText_Dni.getText();

        try {
            this.modelo
                .getModelo_abm_alumno()
                .borrarAlumno(this.alumno_seleccionado);
            JOptionPane.showMessageDialog(this, "Alumno eliminado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            
        }
        this.modelo.getAlumnos().remove(this.alumno_seleccionado.getDni());
        
    }

    private void agregar() {
        String st_dni = this.jText_Dni.getText();
        String st_legajo = this.jText_Legajo.getText();
        try {
            long dni = Long.parseLong(st_dni);
            long legajo = Long.parseLong(st_legajo);

            String apellido = this.jText_Apellido
                                  .getText()
                                  .trim();
            String nombre = this.jText_Nombre
                                .getText()
                                .trim();
            Alumno al = new Alumno(legajo, apellido, nombre, dni);

            this.modelo.agrega_alumno(al);
            JOptionPane.showMessageDialog(this, "Alumno agregado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void modificar() {
        try {
            long legajo = Long.parseLong(this.jText_Legajo.getText());
            String apellido = this.jText_Apellido.getText();
            String nombre = this.jText_Nombre.getText();
            this.alumno_seleccionado.setApellido(apellido);
            this.alumno_seleccionado.setNombre(nombre);
            this.alumno_seleccionado.setLegajo(legajo);

            this.modelo
                .getModelo_abm_alumno()
                .modificarAlumno(this.alumno_seleccionado);
            JOptionPane.showMessageDialog(this, "Alumno modificado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }


    private void setEnabledAceptarCancelar(boolean parametro) {
        this.jButton_Cancelar.setEnabled(parametro);
        this.jButton_Aceptar.setEnabled(parametro);
        this.jButton_Agregar.setEnabled(!parametro);
        this.jButton_Borrar.setEnabled(!parametro);
        this.jButton_Modificar.setEnabled(!parametro);
        this.jList_Alumnos.setEnabled(!parametro);
        this.setEnabled_jtext(false);
    }

    private void consultar() {
        this.actualizar_jList();
        this.limpia_jText();
        this.alumno_seleccionado = null;
        this.setEnabledAceptarCancelar(false);
        this.modo = UI_Alta_Alumno.CONSULTA;
        this.setEnabled_jtext(false);
    }
}