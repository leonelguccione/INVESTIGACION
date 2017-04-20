
package prueba;


import client.ArbolPerturbacionVisual;

import java.awt.Color;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Guille
 */
public class Ventana2 extends javax.swing.JFrame
{

    private ArbolPerturbacionVisual tree2;

    private DefaultTreeModel modeloarbol1;
    private DefaultTreeModel modeloarbol2;

    /** Creates new form Ventana2 */
    public Ventana2()
    {
        initComponents();

        this.modeloarbol2 = new DefaultTreeModel(new DefaultMutableTreeNode("(JFrame)\nventana"));
        this.modeloarbol1 = new DefaultTreeModel(new NodoConNumero("Raiz", 0.8));


        tree2 = (ArbolPerturbacionVisual) this.jScrollPane2;


        this.jTree1.setModel(modeloarbol1);

        tree2.setAnchoBorde(2);
        this.tree2.getPaleta().addColor(new Color(255, 128, 128));
        this.tree2.getPaleta().addColor(new Color(255, 255, 128));
        this.tree2.getPaleta().addColor(new Color(128, 255, 128));
    
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents()//GEN-BEGIN:initComponents
    {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane2 = new ArbolPerturbacionVisual();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setText("Agregar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Borrar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton2MouseClicked(evt);
            }
        });

        jTextField1.setText("jTextField1");

        jCheckBox1.setText("Rectas");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jCheckBox1MouseClicked(evt);
            }
        });

        jButton3.setText("Modelo1");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setText("Null");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton4MouseClicked(evt);
            }
        });

        jButton5.setText("Modelo2");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton5MouseClicked(evt);
            }
        });

        jTextField2.setText("jTextField1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addGap(6, 6, 6)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addGap(33, 33, 33))
        );

        getContentPane().add(jPanel5, java.awt.BorderLayout.LINE_START);

        jTree1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jTree1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_END);

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jScrollPane2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                jScrollPane2MousePressed(evt);
            }
        });
        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents

  private void jButton1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton1MouseClicked
  {//GEN-HEADEREND:event_jButton1MouseClicked
        // TODO add your handling code here:
        agregar();
    
    
    
  }//GEN-LAST:event_jButton1MouseClicked

  private void jButton2MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton2MouseClicked
  {//GEN-HEADEREND:event_jButton2MouseClicked
        // TODO add your handling code here:


        borrar();
  }//GEN-LAST:event_jButton2MouseClicked

  private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jCheckBox1MouseClicked
  {//GEN-HEADEREND:event_jCheckBox1MouseClicked
        rectas();

  }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        this.tree2.setModel(this.modeloarbol1);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
   
        this.tree2.setModel(null);
        System.out.println("NULL");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        this.tree2.setModel(this.modeloarbol2);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTree1MouseClicked
    {//GEN-HEADEREND:event_jTree1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTree1MouseClicked

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jScrollPane2MouseClicked
    {//GEN-HEADEREND:event_jScrollPane2MouseClicked
    
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void jScrollPane2MousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jScrollPane2MousePressed
    {//GEN-HEADEREND:event_jScrollPane2MousePressed

        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MousePressed

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
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Ventana2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Ventana2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Ventana2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Ventana2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new Ventana2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

    private void agregar()
    {
        DefaultMutableTreeNode nodoSeleccionado = tree2.getNodoSeleccionado();
        DefaultTreeModel modeloactual = (DefaultTreeModel) this.tree2.getModel();
        if (nodoSeleccionado != null)
        {
            NodoConNumero unnodo =
                new NodoConNumero(this.jTextField1.getText(), Double.parseDouble(this.jTextField2.getText()));
            modeloactual.insertNodeInto(unnodo, nodoSeleccionado, nodoSeleccionado.getChildCount());

        }
    }

    private void borrar()
    {
        DefaultMutableTreeNode nodoSeleccionado = tree2.getNodoSeleccionado();
        DefaultTreeModel modeloactual = (DefaultTreeModel) this.tree2.getModel();
        if (nodoSeleccionado != null)
            modeloactual.removeNodeFromParent(nodoSeleccionado);

    }

    private void rectas()
    {
        tree2.setLineasRectas(this.jCheckBox1.isSelected());
        repaint();


    }
}

