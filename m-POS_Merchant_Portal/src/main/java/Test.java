import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: estel
 * Date: 11/12/13
 * Time: 11:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }


    public static void main(String arg[]) {

        Connection con = null;
        PreparedStatement ps = null;
        InputStream is = null;
        try {

            String driver_name = "com.informix.jdbc.IfxDriver";
            String data_informix_url = "jdbc:informix-sqli://192.168.0.149:13001/mpos:INFORMIXSERVER=ids_emcom";
            String data_informix_user = "informix";
            String data_informix_password = "Informix";

            Class.forName(driver_name);
            con = DriverManager.getConnection(data_informix_url, data_informix_user, data_informix_password);
            ps = con.prepareStatement("insert into chargeslip values (?,?,?,?,?,?,?,?)");


          /*  chargeslip_status	INTEGER		false
            chargeslip_txn_id	VARCHAR	25	false
            chargeslip_last_four_digit	VARCHAR	25	false
            chargeslip_amount	VARCHAR	25	false
            chargeslip_byte	BLOB		true
            chargeslip_response_ts	DATETIME		false
            chargeslip_ts	DATETIME		false
*/

            ps.setInt(1, 1);
            ps.setInt(2, 1);
            ps.setString(3, "1234");
            ps.setString(4, "9999");
            ps.setString(5, "9999");

            File f = new File("E:\\sale_receipt_transid.png");
            FileInputStream fin = new FileInputStream(f);
            ps.setBinaryStream(6, fin, (int) f.length());


            ps.setDate(7, getCurrentDate());
            ps.setDate(8, getCurrentDate());

            //ps.setInt(1, 6);
            /*     ps.setInt(1, 11);
       ps.setDate(2,getCurrentDate());
       ps.setString(3, "12312");
       ps.setDate(4,getCurrentDate());

       ps.setString(5,"123");
       ps.setString(6,"4123");

       File f = new File("E:\\index.png");
       FileInputStream fin = new FileInputStream(f);
       ps.setBinaryStream(7, fin, (int) f.length());  */

            //  ps.setInt(4,1);

            int count = ps.executeUpdate();

            System.out.println("Count: " + count);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception ex) {
            }
        }


    }
}
