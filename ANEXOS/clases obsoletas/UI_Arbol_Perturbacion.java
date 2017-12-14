package vista;


import arbol_perturbacion_visual.ANoEvaluableVisual;
import arbol_perturbacion_visual.ArbolPerturbacionVisual;

import arbolvisual.NodoVisual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;

import modelo.ArbolPerturbacion;


public class UI_Arbol_Perturbacion extends JFrame implements ActionListener
{
    private static final String CAPTURA = "captura";
    private static final String TRADICIONAL = "tradicional";
    private static final String PERSONALIZADO = "personalizado";


    private ArbolPerturbacionVisual jtree_arbol_visual = new ANoEvaluableVisual();
    private JPanel jPanel_sur = new JPanel();
    private JPanel jPanel_grilla = new JPanel();
    private JButton jButton_capturar = new JButton("Capturar Imagen");
    private JButton jbutton_oculta = new JButton("Ocultar");
    private JButton jbutton_muestraOcultos = new JButton("Muestra Oc");
    
    private JTree jtree_tradicional = new JTree();
    private ButtonGroup grupo = new ButtonGroup();

    private JRadioButton jr_Personalizado = new JRadioButton("Personalizado");
    private JRadioButton jr_Tradicional = new JRadioButton("Tradicional");
    private JScrollPane jScrollPane_jTree_Tradicional = new javax.swing.JScrollPane();

    public UI_Arbol_Perturbacion( ArbolPerturbacion arbol) throws HeadlessException
    {
        super(arbol.getNombre());
        this.setVisible(true);
        setExtendedState(java.awt
                             .Frame
                             .MAXIMIZED_BOTH);
        this.jtree_arbol_visual.setModel(arbol.getTreeModel());
        this.jtree_tradicional.setModel(arbol.getTreeModel());
        this.jtree_tradicional.setScrollsOnExpand(true);
        this.jtree_arbol_visual.setLineasRectas(true);
        grupo.add(jr_Personalizado);
        grupo.add(jr_Tradicional);
        this.jButton_capturar.setActionCommand(UI_Arbol_Perturbacion.CAPTURA);
        jScrollPane_jTree_Tradicional.setViewportView(jtree_tradicional);
        this.jr_Tradicional.setActionCommand(UI_Arbol_Perturbacion.TRADICIONAL);
        this.jr_Personalizado.setActionCommand(UI_Arbol_Perturbacion.PERSONALIZADO);
        this.jButton_capturar.addActionListener(this);
        this.jr_Tradicional.addActionListener(this);
        this.jr_Personalizado.addActionListener(this);
        jPanel_sur.add(jPanel_grilla);
        this.jPanel_grilla.setLayout(new GridLayout(2, 2));
        jPanel_grilla.add(jr_Personalizado);
        jPanel_grilla.add(jr_Tradicional);
        jPanel_grilla.add(jButton_capturar);
        jPanel_grilla.add(this.jbutton_oculta);
        this.jbutton_oculta.setActionCommand("OCULTA");
        this.jbutton_oculta.addActionListener(this);
        jPanel_grilla.add(this.jbutton_muestraOcultos);
        this.jbutton_muestraOcultos.setActionCommand("MUESTRA");
        this.jbutton_muestraOcultos.addActionListener(this);
        this.jr_Personalizado.setSelected(true);
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
        if (e.getActionCommand().equals(UI_Arbol_Perturbacion.CAPTURA))
            this.captura();
        if (e.getActionCommand().equals(UI_Arbol_Perturbacion.PERSONALIZADO))
        {
            this.inicia_visual(this.jtree_arbol_visual);
        }
        if (e.getActionCommand().equals(UI_Arbol_Perturbacion.TRADICIONAL))
        {
            this.inicia_visual(this.jScrollPane_jTree_Tradicional);

        }
        if (e.getActionCommand().equals("OCULTA"))
        {
            
            
            
            DefaultMutableTreeNode nodo = this.jtree_arbol_visual.getNodoSeleccionado();
            NodoVisual nv = this.jtree_arbol_visual.getNodoVisual(nodo);
            this.jtree_arbol_visual.setOculto(nodo, !nv.isOculto());
            

            
            this.jtree_arbol_visual.repaint();
        }
        
        if (e.getActionCommand().equals("MUESTRA"))
            this.jtree_arbol_visual.setMuestraNodosOcultos(!this.jtree_arbol_visual.isMuestraNodosOcultos());
        
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
                if (jr_Personalizado.isSelected())
                    imagen = getScreenShot(jtree_arbol_visual.getComponent(0));
                else
                    imagen = getScreenShot(jtree_tradicional);

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes PNG", "png");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showSaveDialog(UI_Arbol_Perturbacion.this);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {String nombreArch= chooser.getSelectedFile().getAbsolutePath();
                 
                    try
                    {
                        File archivo = new File( nombreArch+".png");
                        ImageIO.write(imagen, "png", archivo);
                    } catch (IOException e)
                    {
                        System.out.println(e.getMessage());
                    }
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
