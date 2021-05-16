/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.MainWindow;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raniacharkaoui
 */
public class Main {
    
    public static void main(String args[]) throws ClassNotFoundException{
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String userName = "student";
            String password = "1234";
            String url = "jdbc:mysql://localhost/MISProject?serverTimezone=Europe/Brussels";
            Connection conn = null;
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected to the database.");
            PreparedStatement s = conn.prepareStatement("SELECT id, firstName, lastName FROM Patient;");
            ResultSet rs = s.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                System.out.println("[" + id + "] " + firstName + " " +
                lastName.toUpperCase());
            }
            rs.close();
            s.close(); 
        
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        new MainWindow().setVisible(true);
            
    }
    
    
}
