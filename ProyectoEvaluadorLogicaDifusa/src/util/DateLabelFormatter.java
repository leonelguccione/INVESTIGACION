package util;

import java.text.DateFormat;
import java.text.ParseException;

import java.util.Calendar;
import java.util.Locale;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateLabelFormatter extends AbstractFormatter
{


    private DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.FULL, new Locale("es", "AR"));


    @Override
    public Object stringToValue(String text) throws ParseException
    {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException
    {
        if (value != null)
        {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}
