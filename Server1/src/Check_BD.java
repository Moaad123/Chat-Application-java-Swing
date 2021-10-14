
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toshiba
 */
public class Check_BD extends Thread{
    Socket client;
    public Check_BD(Socket client){
        this.client = client;
        super.start();
    }

    @Override
    public void run() {
        Connection_DB connect= new Connection_DB();
       ResultSet rs=connect.selection("select username, status from user");
        try {
            PrintStream send= new PrintStream(client.getOutputStream());
            String us;
            String st;
            String username_status="";
            while(rs.next()){
                us = rs.getString(1);
                st = rs.getString(2);
                username_status +=us+"-"+st+"/";
            }
            send.println(username_status+":status");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Check_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
