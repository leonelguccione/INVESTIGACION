package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Fecha
{
    private static DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public Fecha()
    {
        super();
    }

    public static java.sql.Date str2Date(String txt_date)
    {
        java.util.Date utilDate;
        java.sql.Date sqlDate;
        try
        {
            utilDate = sourceFormat.parse(txt_date);
        }
        catch (ParseException e)
        {
            System.out.println("no se pudo entender la fecha");
            utilDate = new java.util.Date();
        }
        return sqlDate = new java.sql.Date(utilDate.getTime());
    }

    public static String date2Str(java.sql.Date sqlDate)
    {
        java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
        String str_date = sourceFormat.format(sqlDate);
        return str_date;
    }
}
