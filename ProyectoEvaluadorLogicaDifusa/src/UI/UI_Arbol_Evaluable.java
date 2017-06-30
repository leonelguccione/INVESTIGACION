package UI;

import arbol_perturbacion_visual.AEvaluableVisual;

import arbolvisual.NodoVisual;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import modelo.ArbolPerturbacion;

public class UI_Arbol_Evaluable extends JFrame implements ActionListener
{
    private static final String CAPTURA = "captura";
    

    private AEvaluableVisual jtree_arbol_visual = new AEvaluableVisual();
    private JPanel jPanel_sur = new JPanel();
    private JPanel jPanel_grilla = new JPanel();
    private JButton jButton_capturar = new JButton("Capturar Imagen");

    private JCheckBox jbutton_muestraOcultos = new  JCheckBox("Muestra Ocultos",true);
    
    private JTree jtree_tradicional = new JTree();
    

    
    
    

    public UI_Arbol_Evaluable(String string, ArbolPerturbacion arbol) throws HeadlessException
    {
        super(string);
        this.setVisible(true);
        setExtendedState(java.awt
                             .Frame
                             .MAXIMIZED_BOTH);
        this.jtree_arbol_visual.setModel(arbol.getTreeModel());
        this.jtree_tradicional.setModel(arbol.getTreeModel());
        this.jtree_tradicional.setScrollsOnExpand(true);
        this.jtree_arbol_visual.setLineasRectas(true);
        this.jButton_capturar.setActionCommand(UI_Arbol_Evaluable.CAPTURA);
        this.jButton_capturar.addActionListener(this);
        jPanel_sur.add(jPanel_grilla);
        this.jPanel_grilla.setLayout(new GridLayout(2, 2));
        jPanel_grilla.add(jButton_capturar);
        jPanel_grilla.add(this.jbutton_muestraOcultos);
        this.jbutton_muestraOcultos.setActionCommand("MUESTRA");
        this.jbutton_muestraOcultos.addActionListener(this);
            this.jtree_arbol_visual.setMuestraNodosOcultos(true);
        this.inicia_visual(this.jtree_arbol_visual);
    }

    public static BufferedImage getScreenShot(Component component)
    {
        component.setSize(component.getPreferredSize());
        BufferedImage image =
            new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        component.paint(g);
        return image;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals(UI_Arbol_Evaluable.CAPTURA))
            this.captura();
        if (e.getActionCommand().equals("OCULTA"))
        {
            
            
            
            DefaultMutableTreeNode nodo = this.jtree_arbol_visual.getNodoSeleccionado();
            NodoVisual nv = this.jtree_arbol_visual.getNodoVisual(nodo);
            this.jtree_arbol_visual.setOculto(nodo, !nv.isOculto());
            

            
            this.jtree_arbol_visual.repaint();
        }
        
        if (e.getActionCommand().equals("MUESTRA"))
            this.jtree_arbol_visual.setMuestraNodosOcultos(this.jbutton_muestraOcultos.isSelected());
        
        this.jtree_arbol_visual.repaint();
        
    }

    private void captura()
    {
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                BufferedImage imagen;
                
                    imagen = getScreenShot(jtree_arbol_visual.getComponent(0));
                

                try
                {
                    File archivo = new File("screenshot.png");
                    ImageIO.write(imagen, "png", archivo);
                } catch (IOException e)
                {
                    System.out.println(e.getMessage());
                }
            }

        };
        SwingUtilities.invokeLater(r);

    }

    private void inicia_visual(JComponent jarbolactual)
    {
        this.getContentPane().removeAll();
        this.getContentPane().add(jPanel_sur, BorderLayout.SOUTH);
        this.getContentPane().add(jarbolactual, BorderLayout.CENTER);
        this.pack();
        setExtendedState(java.awt
                             .Frame
                             .MAXIMIZED_BOTH);
    }

}

