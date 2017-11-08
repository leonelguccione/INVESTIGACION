package vista;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Class1
{
    public Class1()
    {
        super();
    }

    public static void main(String[] args)
    {
        // Open the file
        FileInputStream fstream;
        try
        {
            fstream = new FileInputStream("config.cfg");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;
            while ((strLine = br.readLine()) != null)
            {
                // Print the content on the console
                System.out.println(strLine);
            }
            br.close();

        } catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
