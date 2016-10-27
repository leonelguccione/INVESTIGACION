
package UI;

import java.beans.PropertyVetoException;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Modelo;


/**
 *
 * @author leonel
 */
public class UI_Principal extends javax.swing.JFrame
{
    Modelo modelo;

    /** Creates new form UI_Principal */
    public UI_Principal()
    {
        initComponents();
        try
        {
            modelo = new Modelo();
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        jPanel_ppal = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu_Asignaturas = new javax.swing.JMenu();
        jMenuItem_ABM_Asignatuas = new javax.swing.JMenuItem();
        JM_Cohortes = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.SystemColor.textInactiveText);
        setMinimumSize(new java.awt.Dimension(1024, 600));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel_ppal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel_ppal.setAutoscrolls(true);
        jPanel_ppal.setPreferredSize(new java.awt.Dimension(1000, 800));

        javax.swing.GroupLayout jPanel_ppalLayout = new javax.swing.GroupLayout(jPanel_ppal);
        jPanel_ppal.setLayout(jPanel_ppalLayout);
        jPanel_ppalLayout.setHorizontalGroup(
            jPanel_ppalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1106, Short.MAX_VALUE)
        );
        jPanel_ppalLayout.setVerticalGroup(
            jPanel_ppalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel_ppal);

        jMenuBar1.setMaximumSize(new java.awt.Dimension(1100, 770));
        jMenuBar1.setMinimumSize(new java.awt.Dimension(1100, 770));

        jMenu_Asignaturas.setText("Asignaturas");

        jMenuItem_ABM_Asignatuas.setText("ABM");
        jMenuItem_ABM_Asignatuas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ABM_AsignatuasActionPerformed(evt);
            }
        });
        jMenu_Asignaturas.add(jMenuItem_ABM_Asignatuas);

        jMenuBar1.add(jMenu_Asignaturas);

        JM_Cohortes.setText("Alumnos");

        jMenuItem2.setText("ABM");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        JM_Cohortes.add(jMenuItem2);

        jMenuBar1.add(JM_Cohortes);

        jMenu3.setText("Cursadas");

        jMenuItem3.setText("Altas");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        jMenu1.setText("Parciales");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Altas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Instancias de Evaluación");

        jMenuItem5.setText("Altas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem4.setText("Correciones");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        // TODO add your handling code here:

        UI_Alta_Alumno ui_al = new UI_Alta_Alumno(modelo);
        this.jPanel_ppal.add(ui_al);
        ui_al.setVisible(true);
        try
        {
            ui_al.setMaximum(true);
        }
        catch (PropertyVetoException e)
        {
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        UI_Cursada_Altas ui_cur = new UI_Cursada_Altas(modelo);
        this.jPanel_ppal.add(ui_cur);
        ui_cur.setVisible(true);
        try
        {
            ui_cur.setMaximum(true);
        }
        catch (PropertyVetoException e)
        {
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem_ABM_AsignatuasActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem_ABM_AsignatuasActionPerformed
    {//GEN-HEADEREND:event_jMenuItem_ABM_AsignatuasActionPerformed
        // TODO add your handling code here:
        lanzar_UI_Asignaturas();
    }//GEN-LAST:event_jMenuItem_ABM_AsignatuasActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenu1ActionPerformed
    {//GEN-HEADEREND:event_jMenu1ActionPerformed
       
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem1ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem1ActionPerformed
        UI_Parcial ui_par = new UI_Parcial(modelo);
         this.jPanel_ppal.add(ui_par);
        ui_par.setVisible(true);
       try
        {
            ui_par.setMaximum(true);
        }
        catch (PropertyVetoException e)
        {
        }// TODO add your handling code here:

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem5ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem5ActionPerformed
      UI_Instancia_Evaluacion ui_ie = new UI_Instancia_Evaluacion(modelo);
   
     this.jPanel_ppal.add(ui_ie);
    ui_ie.setVisible(true);
    try
    {
        ui_ie.setMaximum(true);
    }
    catch (PropertyVetoException e)
    {
    }// TODO add your handling code here:

       
       
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
UI_Correccion ui_correccion=new UI_Correccion(modelo);
        this.jPanel_ppal.add(ui_correccion);
        ui_correccion.setVisible(true);
        try
        {
           ui_correccion.setMaximum(true);
        }
        catch (PropertyVetoException e)
        {
        }// TODO add your


        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(UI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                 ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(UI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                 ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(UI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                 ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(UI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                 ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new UI_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu JM_Cohortes;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem_ABM_Asignatuas;
    private javax.swing.JMenu jMenu_Asignaturas;
    private javax.swing.JPanel jPanel_ppal;
    // End of variables declaration//GEN-END:variables


    public void lanzar_UI_Asignaturas()
    {
        UI_Asignatura ui_Asignatura = new UI_Asignatura(modelo);
        this.jPanel_ppal.add(ui_Asignatura);
        ui_Asignatura.setVisible(true);
        try
        {
            ui_Asignatura.setMaximum(true);
        } catch (PropertyVetoException e)
        {
        }


    }
}
