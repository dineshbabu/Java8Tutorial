package jdbcdemo.mysql;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteImage {

    public static void main(String[] args) throws FileNotFoundException,
            SQLException, IOException {

        Connection con = null;
        PreparedStatement pst = null;
        FileInputStream fin = null;

        String cs = "jdbc:mysql://localhost:3306/testdb";
        String user = "testuser";
        String password = "test623";

        try {

            File img = new File("woman.jpg");
            fin = new FileInputStream(img);

            con = DriverManager.getConnection(cs, user, password);

            pst = con.prepareStatement("INSERT INTO Images(Data) VALUES(?)");
            pst.setBinaryStream(1, fin, (int) img.length());
            pst.executeUpdate();

        } finally {

            if (pst != null) {
                pst.close();
            }
            
            if (con != null) {
                con.close();
            }
            
            if (fin != null) {
                fin.close();
            }
        }
    }
}