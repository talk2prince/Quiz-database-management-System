/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author prince
 */
public interface questionret2 {
    

    connection con=null;
    static String question[] = new String[25];
    static String answers[][] = new String[25][25];
    static String option[]=new String[25];
    static String choice[]=new String[25];
    static int n[] = new int[50];
    void createconnection();
    public static void main(String[] args) throws SQLException {
    questionret pro = new questionret();
    pro.createconnection();
    
}

    /**
     *
     * @throws SQLException
     */
   /* void createconnection() throws SQLException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Prince@123");
            Statement stmt = con.createStatement();
            ResultSet rs =   stmt.executeQuery("SELECT * FROM QUESTION");
            int i=0,j=0;
            while(rs.next())
            {
                int count=0;
                String quescont =  rs.getString("quescontent");
                
                 option[1] =  rs.getString("option1");
                 option[2] =  rs.getString("option2");
                 option[3] =  rs.getString("option3");
                 option[4] =  rs.getString("option4");
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
                    System.out.println("i is:"+i+"count is" +count);
                    choice[i]="1";
                    n[i]=count;
                }
   
                 if(cb2==1)
                {
                    count++;
                     System.out.println("i is:"+i+"count is" +count);
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
                     System.out.println("i is:"+i+"count is" +count);
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
                     System.out.println("i is:"+i+"count is" +count);
                    String temp = choice[i];
                    if(temp==null)
                    {
                        temp="";
                    }
                        
                    choice[i]=temp+"4";
                    n[i]=count;
                }
                System.out.println(n[i]);
                count=0;
                i++;                
                
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JavaApplication3.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }*/
}

    
    

