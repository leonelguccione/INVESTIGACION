package UI;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class UI_Parcial_Working extends JFrame
{
    public UI_Parcial_Working(String string, GraphicsConfiguration graphicsConfiguration)
    {
        super(string, graphicsConfiguration);
    }

    public UI_Parcial_Working(String string) throws HeadlessException
    {
        super(string);
    }

    public UI_Parcial_Working(GraphicsConfiguration graphicsConfiguration)
    {
        super(graphicsConfiguration);
    }

    public UI_Parcial_Working() throws HeadlessException
    {
        super();
    }
}
