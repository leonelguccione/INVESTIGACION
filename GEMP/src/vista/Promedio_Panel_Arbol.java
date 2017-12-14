package vista;

import arbol_perturbacion_visual.AEvaluableVisual;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.tree.TreeModel;

import modelo.ArbolPerturbacion;
import modelo.NodoPerturbacion;

public class Promedio_Panel_Arbol extends JPanel implements ActionListener,Interface_Arbol_Promedio
{
    private JPanel panel_centro = new JPanel();
    private JPanel panel_sur = new JPanel();
    private JCheckBox chckbxVerNodosOcultos = new JCheckBox("Ver Nodos Ocultos");
    private AEvaluableVisual jTree_Arbol_Perturbacion = new AEvaluableVisual();
    private static final String OCULTOS = "OCULTOS";


    public Promedio_Panel_Arbol()
    {
        this.iniciaGraficos();
    }


    private void iniciaGraficos()
    {
        setLayout(new BorderLayout(0, 0));
        add(panel_centro);
        add(panel_sur, BorderLayout.SOUTH);
        panel_sur.add(chckbxVerNodosOcultos);
        this.panel_centro.setLayout(new BorderLayout(0, 0));
        this.panel_centro.add(this.jTree_Arbol_Perturbacion, BorderLayout.CENTER);
        this.chckbxVerNodosOcultos.addActionListener(this);
        this.chckbxVerNodosOcultos.setActionCommand(Promedio_Panel_Arbol.OCULTOS);
        this.jTree_Arbol_Perturbacion.setLineasRectas(true);
        this.jTree_Arbol_Perturbacion.setColorFondo(new Color(230,230,230));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals(Promedio_Panel_Arbol.OCULTOS))
        {
            this.jTree_Arbol_Perturbacion.setMuestraNodosOcultos(!this.jTree_Arbol_Perturbacion.isMuestraNodosOcultos());

        }
    }
@Override
    public void setArbol(ArbolPerturbacion a)
    {
        if (a != null)
        {
            this.jTree_Arbol_Perturbacion.setModel(a.getTreeModel());
            this.jTree_Arbol_Perturbacion.setBorder(new TitledBorder(a.getNombre()));

        } else
        {
            this.jTree_Arbol_Perturbacion.setModel(null);
            this.jTree_Arbol_Perturbacion.setBorder(new TitledBorder("No hay un árbol para mostrar"));
        }
        this.repaint();
    }


    @Override
    public void setVisible(boolean aFlag)
    {
        
        this.jTree_Arbol_Perturbacion.setVisible(aFlag);
    }


    void setNodoSeleccionado(NodoPerturbacion nodosel)
    {this.jTree_Arbol_Perturbacion.setNodoSeleccionado(nodosel);
    }
}
