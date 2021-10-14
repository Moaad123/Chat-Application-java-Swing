/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author toshiba
 */
public class Read_Request extends Thread{
    String username;
    Socket client;
    JTable table;
    public Read_Request(String username, Socket client, JTable table) {
        this.username = username;
        this.client = client;
        this.table = table;
        super.start();
                                                                       }

    @Override
    public void run() {
        PrintStream msg = null;
        String line=null;
        try {
            msg = new PrintStream(client.getOutputStream());
            msg.println(username+":request");
            BufferedReader send = new BufferedReader(new InputStreamReader(client.getInputStream()));
                line=send.readLine(); 
                String[] friends=line.split("-");
                for (String friend : friends) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.addRow(new Object[]{friend, "confirm", "remove"});
                }                 
              
        }catch (IOException ex){
            Logger.getLogger(Read_Request.class.getName()).log(Level.SEVERE, null, ex);
           this.stop();                    }
    }
    }
    

