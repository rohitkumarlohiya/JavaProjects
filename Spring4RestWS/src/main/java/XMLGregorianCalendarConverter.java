import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Converts different objects into XMLGregorianCalendar
 */
public class XMLGregorianCalendarConverter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
    	
    	
    	
    	Calendar calendar = Calendar.getInstance(); 
    	
    	System.out.println(calendar.getTime());
    	calendar.add(Calendar.SECOND, 50);
    	System.out.println(calendar.getTime());
    	
    	System.out.println("1==>"+dateToXml(new Date(), new SimpleDateFormat("yyyy-MM-dd")));
    	System.out.println("2==>"+dateToXml(new Date(), new SimpleDateFormat("HH:mm:ss")));
    	System.out.println("3==>"+dateToXml(new Date(), new SimpleDateFormat("yyyy-MM-dd")));
    	
    	
    	

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS z", Locale.ENGLISH);

        String formattedDate = sdf.format(date);

        System.out.printf("%-24s %s%n", "String:", stringToXMLGregorianCalendar(formattedDate, sdf));
    }

    /**
     * Converts Date object into XMLGregorianCalendar
     *
     * @param date Object to be converted
     * @return XMLGregorianCalendar
     */
    private static XMLGregorianCalendar dateToXMLGregorianCalendar(Date date) {

        try {
            GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
            gc.setTime(date);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        } catch (DatatypeConfigurationException e) {
            // TODO: Optimize exception handling
            System.out.print(e.getMessage());
            return null;
        }
    }

    /**
     * Converts a formatted string into XMLGregorianCalendar
     *
     * @param datetime Formatted string
     * @param sdf Date format of the given string
     * @return XMLGregorianCalendar
     */
    private static XMLGregorianCalendar stringToXMLGregorianCalendar(String datetime, SimpleDateFormat sdf) {

        try {
            Date date = sdf.parse(datetime);
            return dateToXMLGregorianCalendar(date);
        } catch (ParseException e) {
            // TODO: Optimize exception handling
            System.out.print(e.getMessage());
            return null;
        }
    }
    
    public static XMLGregorianCalendar dateToXml(Date date,DateFormat dateFormat){
    	  //DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    	  String strDate=dateFormat.format(date);
    	  try {
    	    XMLGregorianCalendar xmlDate=DatatypeFactory.newInstance().newXMLGregorianCalendar(strDate);
    	    return xmlDate;
    	  }
    	 catch (  DatatypeConfigurationException e) {
    	    throw new RuntimeException(e);
    	  }
    	}
}