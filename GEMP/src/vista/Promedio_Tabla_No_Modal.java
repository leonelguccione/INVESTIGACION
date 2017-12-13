package vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Promedio_Tabla_No_Modal extends JFrame
{

    private JTable table;

    public Promedio_Tabla_No_Modal(DefaultTableModel tableModel, ListSelectionListener listener)
    {

        super();


        this.table = new JTable();
        JScrollPane scrollPaneTable = new JScrollPane();
        scrollPaneTable.setViewportView(this.table);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.table.setBorder(null);

        this.table.setModel(tableModel);
        ListSelectionModel lsm = this.table.getSelectionModel();
        lsm.addListSelectionListener(listener);


        this.getContentPane().setLayout(new BorderLayout(0, 0));
        this.getContentPane().add(scrollPaneTable, BorderLayout.CENTER);
        setExtendedState(java.awt
                             .Frame
                             .MAXIMIZED_BOTH);
        this.setVisible(true);


    }

    public ListSelectionModel getSlectionModel()
    {
        return this.table.getSelectionModel();
    }


    public JTable getTable()
    {
        return table;
    }
}
