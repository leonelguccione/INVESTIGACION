package arbol_perturbacion_visual;


import arbolvisual.ArbolVisual;
import arbolvisual.NodoVisual;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.Enumeration;
import java.util.Iterator;

import javax.imageio.ImageIO;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeModelEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import modelo.NodoPerturbacion;
import modelo.RelacionImpacto;

import vista.DialogoNodo;


public abstract class ArbolPerturbacionVisual extends ArbolVisual
{
    private Paleta paleta = new Paleta();
    {
        this.paleta.addColor(new Color(255, 128, 128));
        this.paleta.addColor(new Color(255, 255, 128));
        this.paleta.addColor(new Color(128, 255, 128));
    }

    private Color colorLineasRelacionalesOrigen = Color.cyan;
    private Color colorLineasRelacionalesDestino = Color.red;


    private DialogoNodo dialogoNodo = DialogoNodo.getInstance();
    private JPopupMenu mipop = new JPopupMenu();
    private JMenuItem menuItemDetalle = new JMenuItem("Detalle del Nodo");
    private JMenuItem menuItemCaptura = new JMenuItem("Captura Pantalla");
    private ActionListener actionlistener;
    private static final String CAPTURA="CAPTURA";
    private static final String DETALLE="DETALLE";

    private void borraNodoDestinoEnRelaciones(NodoPerturbacion nodo_actual, NodoPerturbacion nodo_a_borrar)
    {

        RelacionImpacto rel = nodo_actual.getRelacionImpacto(nodo_a_borrar);
        if (rel != null)
            nodo_actual.removeImpacto(rel);
        Enumeration hijos = nodo_actual.children();
        while (hijos.hasMoreElements())
        {
            NodoPerturbacion hijo = (NodoPerturbacion) hijos.nextElement();
            this.borraNodoDestinoEnRelaciones(hijo, nodo_a_borrar);
        }
    }

    public class ArbolPerturbacionListener extends ArbolVisual.ArbolListener
    {

        @Override
        public void treeNodesRemoved(TreeModelEvent e)
        {
            NodoPerturbacion nodo_a_borrar = (NodoPerturbacion) e.getChildren()[0];
            NodoPerturbacion raiz = (NodoPerturbacion) ArbolPerturbacionVisual.this.getModel().getRoot();
            ArbolPerturbacionVisual.this.borraNodoDestinoEnRelaciones(raiz, nodo_a_borrar);
            super.treeNodesRemoved(e);
        }
    };

    protected class LienzoRelacional extends Lienzo
    {

        @Override
        public void paint(Graphics g)
        {
            // TODO Implement this method

            Iterator<NodoVisual> nodosVisuales = ArbolPerturbacionVisual.this.iteratorNodosVisuales();
            while (nodosVisuales.hasNext())
            {
                NodoVisual origen = nodosVisuales.next();

                if (origen.isVisible())
                {
                    if (!origen.isOculto() || ArbolPerturbacionVisual.this.isMuestraNodosOcultos())
                    {
                        NodoPerturbacion nodo = (NodoPerturbacion) origen.getNodoReal();
                        NodoPerturbacion impactado;

                        Iterator<RelacionImpacto> impactados = nodo.iteratorImpactos();

                        while (impactados.hasNext())
                        {
                            impactado = impactados.next().getNodo();
                            NodoVisual destino = ArbolPerturbacionVisual.this.getNodoVisual(impactado);
                            if (destino.isVisible() &&
                                (!destino.isOculto() || ArbolPerturbacionVisual.this.isMuestraNodosOcultos()))

                            {
                                Color colorOrigen;
                                Color colorDestino;
                                if (origen.isOculto())
                                    colorOrigen =
                                        UtilGraphics.getColorAlpha(ArbolPerturbacionVisual.this
                                                                   .colorLineasRelacionalesOrigen, 32);
                                else
                                    colorOrigen = ArbolPerturbacionVisual.this.colorLineasRelacionalesOrigen;
                                if (destino.isOculto())
                                    colorDestino =
                                        UtilGraphics.getColorAlpha(ArbolPerturbacionVisual.this
                                                                   .colorLineasRelacionalesDestino, 32);
                                else
                                    colorDestino = ArbolPerturbacionVisual.this.colorLineasRelacionalesDestino;

                                UtilGraphics.lineaDegrade(g,
                                                          origen.getX() +
                                                          ArbolPerturbacionVisual.this.getAnchoNodo() / 2,
                                                          origen.getY() +
                                                          ArbolPerturbacionVisual.this.getAltoNodo() / 2,
                                                          destino.getX() +
                                                          ArbolPerturbacionVisual.this.getAnchoNodo() / 2,
                                                          destino.getY() +
                                                          ArbolPerturbacionVisual.this.getAltoNodo() / 2, colorOrigen,
                                                          colorDestino, 100);
                            }
                        }
                    }
                }

            }
            super.paint(g);
        }
    }


    public ArbolPerturbacionVisual(DefaultTreeModel arbol, int anchoNodo, int altoNodo)
    {
        // TODO Implement this method
        super(arbol, anchoNodo, altoNodo);
        this.setLienzo(new LienzoRelacional());
        this.arbolListener = new ArbolPerturbacionListener();
        this.configuraPopUp();
        this.setColorFondo(Color.lightGray);
    }

    public ArbolPerturbacionVisual(DefaultTreeModel defaultTreeModel)
    {
        super(defaultTreeModel);
        this.setLienzo(new LienzoRelacional());
        this.arbolListener = new ArbolPerturbacionListener();
        this.configuraPopUp();
        this.setColorFondo(Color.lightGray);
    }

    public ArbolPerturbacionVisual()
    {
        super();
        this.setLienzo(new LienzoRelacional());
        this.arbolListener = new ArbolPerturbacionListener();
        this.configuraPopUp();
        this.setColorFondo(new Color(230,230,230));
    }

    @Override
    protected void dibujaNodoVisual(NodoVisual nodoVisual, Graphics g)
    {
        // TODO Implement this method
        NodoPerturbacion nodo = (NodoPerturbacion) nodoVisual.getNodoReal();

        if (this.paleta != null)
        {
            if (!nodo.isCero())
            {
                Color color = this.paleta.getColor(nodo.getNota());
                nodoVisual.setColorRelleno(color);
                nodoVisual.setColorBorde(color.darker());
            }
        }


        super.dibujaNodoVisual(nodoVisual, g);


    }


    public void setPaleta(Paleta paleta)
    {
        this.paleta = paleta;
    }

    public Paleta getPaleta()
    {
        return paleta;
    }

    public void setColorLineasRelacionalesOrigen(Color colorLineasRelacionalesOrigen)
    {
        this.colorLineasRelacionalesOrigen = colorLineasRelacionalesOrigen;
    }

    public Color getColorLineasRelacionalesOrigen()
    {
        return colorLineasRelacionalesOrigen;
    }

    public void setColorLineasRelacionalesDestino(Color colorLineasRelacionalesDestino)
    {
        this.colorLineasRelacionalesDestino = colorLineasRelacionalesDestino;
    }

    public Color getColorLineasRelacionalesDestino()
    {
        return colorLineasRelacionalesDestino;
    }

    public abstract void verificaOcultos();


    @Override
    public void setModel(TreeModel arbol)
    {
        // TODO Implement this method
        super.setModel(arbol);
        this.verificaOcultos();
        this.recalcular();
    }

    private void configuraPopUp()
    {
        this.mipop.add(this.menuItemDetalle);
        this.mipop.add(this.menuItemCaptura);
        this.menuItemDetalle.setActionCommand(ArbolPerturbacionVisual.DETALLE);
        this.menuItemCaptura.setActionCommand(ArbolPerturbacionVisual.CAPTURA);
        
        this.setComponentPopupMenu(this.mipop);

        this.actionlistener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getActionCommand().equals(ArbolPerturbacionVisual.DETALLE))
                {
                    ArbolPerturbacionVisual.this
                        .dialogoNodo.setVisible(!ArbolPerturbacionVisual.this.dialogoNodo.isVisible());
                }

                if (e.getActionCommand().equals(ArbolPerturbacionVisual.CAPTURA))
                    ArbolPerturbacionVisual.this.captura();

                if (e.getSource() == ArbolPerturbacionVisual.this)
                {
                    ArbolPerturbacionVisual.this
                        .dialogoNodo.setNodoPerturbacion((NodoPerturbacion) ArbolPerturbacionVisual.this
                                                         .getNodoSeleccionado());
                    ArbolPerturbacionVisual.this.dialogoNodo.toFront();

                }
            }
        };


        this.menuItemDetalle.addActionListener(this.actionlistener);
        this.menuItemCaptura.addActionListener(this.actionlistener);
        
        this.addActionListener(actionlistener);
        this.setComponentPopupMenu(mipop);
    }
    
    
    private void captura()
    {
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                BufferedImage imagen;
                
                    imagen = getScreenShot(ArbolPerturbacionVisual.this.getLienzo());
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes PNG", "png");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showSaveDialog(ArbolPerturbacionVisual.this);
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
    
    private static BufferedImage getScreenShot(Component component)
    {
        component.setSize(component.getPreferredSize());
        BufferedImage image =
            new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        component.paint(g);
        return image;
    }

}
