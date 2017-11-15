package vista;

import java.awt.BorderLayout;


import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import modelo.NodoPerturbacion;
import modelo.RelacionImpacto;

public class DialogoNodo extends JDialog
{

    private final JPanel contentPanel = new JPanel();

    private DefaultTableModel dtm = new DefaultTableModel();
    private JTable table;
    private JScrollPane scrollPane;
    private static DialogoNodo instance = null;


    public static DialogoNodo getInstance()
    {
        if (DialogoNodo.instance == null)
            DialogoNodo.instance = new DialogoNodo();
        return instance;
    }

    private DialogoNodo()
    {
        setResizable(false);
        setBounds(100, 100, 359, 335);
        getContentPane().setLayout(new BorderLayout());
        this.contentPanel.setBorder(new TitledBorder(null, "Relaciones de Impacto", TitledBorder.LEADING,
                                                     TitledBorder.TOP, null, null));

        getContentPane().add(this.contentPanel, BorderLayout.CENTER);

        this.scrollPane = new JScrollPane();

        this.table = new JTable();
        this.scrollPane.setViewportView(this.table);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.table.setBorder(null);
        this.table.setModel(new DefaultTableModel(new Object[][] {


                } , new String[] { "Nodo Destino", "Valor" }));
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(this.scrollPane,
                                                                                                                                                                                  GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                  317,
                                                                                                                                                                                  GroupLayout.PREFERRED_SIZE).addContainerGap(161,
                                                                                                                                                                                                                              Short.MAX_VALUE)));
        gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(this.scrollPane,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                259,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE).addContainerGap(278,
                                                                                                                                                                                                                            Short.MAX_VALUE)));
        contentPanel.setLayout(gl_contentPanel);

        this.setTitle("Titulo");


        TableColumn column = null;
        for (int i = 0; i < 2; i++)
        {
            column = table.getColumnModel().getColumn(i);
            if (i == 0)
            {
                column.setPreferredWidth(100); //third column is bigger
            } else
            {
                column.setPreferredWidth(50);
            }
        }

    }

    public void setNodoPerturbacion(NodoPerturbacion nodo)
    {
        if (nodo != null)
        {
            this.setTitle(nodo.getIdDato());
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            Iterator<RelacionImpacto> it = nodo.iteratorImpactos();
            while (it.hasNext())
            {
                RelacionImpacto r = it.next();
                Object[] array =
                {
                    r.getNodo().getIdDato(), r.getValor()
                };

                model.addRow(array);
            }
        } else
        {
            this.setTitle("Sin nodo Seleccionado");
        }
    }


}
