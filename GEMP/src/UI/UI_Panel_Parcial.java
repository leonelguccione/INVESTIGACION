
package UI;

import arbol_perturbacion_visual.AEvaluableVisual;

/**
 *
 * @author Guille
 */
public class UI_Panel_Parcial extends javax.swing.JPanel
{

    /** Creates new form UI_Panel_Parcial */
    public UI_Panel_Parcial()
    {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents()//GEN-BEGIN:initComponents
    {

        jPanelSouth = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane_TreeVisual = new AEvaluableVisual();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setText("Ocultar Nodo");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jCheckBox1.setText("Ver Nodos Ocultos");
        jPanel1.add(jCheckBox1);

        jPanelSouth.add(jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton2.setText("Guardar Parcial");
        jPanel2.add(jButton2);

        jButton3.setText("Cancelar");
        jPanel2.add(jButton3);

        jPanelSouth.add(jPanel2);

        add(jPanelSouth, java.awt.BorderLayout.SOUTH);

        jScrollPane_TreeVisual.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("�?rbol Evaluado"));
        add(jScrollPane_TreeVisual, java.awt.BorderLayout.CENTER);
    }//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelSouth;
    private javax.swing.JScrollPane jScrollPane_TreeVisual;
    // End of variables declaration//GEN-END:variables

}
