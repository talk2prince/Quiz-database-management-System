
package javaapplication3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaApplication3 {
connection con=null;
    public static void main(String[] args) throws SQLException {
    JavaApplication3 pro = new JavaApplication3();
    pro.createconnection();
        
    }
    void createconnection() throws SQLException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Prince@123");
            Statement stmt = con.createStatement();
            ResultSet rs =   stmt.executeQuery("SELECT * FROM FACULTY");
            while(rs.next())
            {
                String name =  rs.getString("name");
                System.out.println(name);
                
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JavaApplication3.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
}
  
