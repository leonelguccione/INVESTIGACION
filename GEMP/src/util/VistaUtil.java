package util;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class VistaUtil
{
    public VistaUtil()
    {
        super();
    }
    
    
    public static int dialogoSiNo(String titulo, String mensaje)
    {
            Object[] options =
            {
                UIManager.getString("OptionPane.yesButtonText"), UIManager.getString("OptionPane.noButtonText")
            };
            int res =
                JOptionPane.showOptionDialog(null, mensaje, titulo, JOptionPane.DEFAULT_OPTION,
                                             JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
            return res;
        }
}
