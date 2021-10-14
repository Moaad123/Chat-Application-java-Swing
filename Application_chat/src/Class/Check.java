/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.net.Socket;
import javax.swing.JTable;


/**
 *
 * @author toshiba
 */
public class Check extends Thread{
    JTable table;
    String user_status;
    Socket client;
    public Check(String user_status, JTable table,Socket client) {
        this.table = table;
        this.user_status = user_status;
        this.client = client;
        super.start();
    }
    @Override
    public void run() {
           String[] user_sta=user_status.split("/");
        for (String user_sta1 : user_sta){
            String[] username_status = user_sta1.split("-");
            String username=username_status[0];
            String status=username_status[1];
            for(int i=0;i<table.getRowCount();i++){
                if(username.equals(table.getValueAt(i, 0)))
                    table.setValueAt(status, i, 1);
            }
        }    
    }  
}
    
    
    

