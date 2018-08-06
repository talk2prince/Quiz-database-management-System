
package javaapplication3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class questionret {
    connection con=null;
    static String question[] = new String[25];
    static String answers[][] = new String[25][25];
    static String option[]=new String[4];
    static String choice[]=new String[25];
    static int n[] = new int[50];
    
    public static void main(String[] args) throws SQLException {
    questionret pro = new questionret();
    pro.createconnection();
    
}
     void createconnection() throws SQLException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Prince@123");
            Statement stmt = con.createStatement();
            ResultSet rs =   stmt.executeQuery("SELECT * FROM QUESTION,NOQ");

            int i=0,j=0;
            while(rs.next())
            {
                int count=0;
                int k;
                
                String quescont =  rs.getString("quescontent");
                
                 option[0]=  (rs.getString("option1"))+"\n";
                 option[1]=  (rs.getString("option2"))+"\n";
                 option[2]=  (rs.getString("option3"))+"\n";
                 option[3]=  (rs.getString("option4"))+"\n";
                 for(k=0;k<4;k++)
                 {
                     answers[i][k]=option[k];
       
                 }
                int cb1 = rs.getInt("checkbox1");
                int cb2 = rs.getInt("checkbox2");
                int cb3 = rs.getInt("checkbox3");
                int cb4 = rs.getInt("checkbox4"); 

                question[i]=quescont;
                        for(j=0;j<4;j++)
                        {
                            answers[i][j]=option[i];

                        }
                if(cb1==1)
                {
                    count++;

                    choice[i]="1";
                    n[i]=count;
                }
   
                 if(cb2==1)
                {
                    count++;

                    String temp = choice[i];
                     if(temp==null)
                    {
                        temp="";
                    }
                    choice[i]=temp+"2";
                    n[i]=count;
                    
                } 
                if(cb3==1)
                {
                    count++;
           
                    String temp = choice[i];
                     if(temp==null)
                    {
                        temp="";
                    }
                    choice[i]=temp+"3";
                    n[i]=count;
                }
                 
                if(cb4==1)
                {
                    count++;
       
                    String temp = choice[i];
                    if(temp==null)
                    {
                        temp="";
                    }
                        
                    choice[i]=temp+"4";
                    n[i]=count;
                }
                System.out.println(question[i]);
       
                count=0;
                i++;       
                int no;
                no = rs.getInt("noq");
                int tim = rs.getInt("time");
                System.out.println(no);
                System.out.println(tim);
            }
          
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JavaApplication3.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
}
