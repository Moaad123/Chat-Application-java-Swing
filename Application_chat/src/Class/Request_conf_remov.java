/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.io.IOException;
import java.net.Socket;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author toshiba
 */
public class Request_conf_remov extends Thread{
    String username;
    Socket client;
    JTable table;

    public Request_conf_remov(String username, Socket client, JTable table) {
        this.username = username;
        this.client = client;
        this.table = table;
        super.start();
                                                                             }

    @Override
    public void run() {
         String v =(String) table.getValueAt(table.getSelectedRow(),table.getSelectedColumn());
         DefaultTableModel model = (DefaultTableModel) table.getModel();
         String user = (String)table.getValueAt(table.getSelectedRow(),0);
         if(v.equals("confirm")){
            try {
                new Request_acc_refuse(username,user,"confirm",client);
            }catch (IOException ex) {
                                      }
            model.removeRow(table.getSelectedRow());
         }else  if(v.equals("remove")){
            try {
                new Request_acc_refuse(username,user,"remove",client);
            }catch (IOException ex) {                        
            }
          model.removeRow(table.getSelectedRow());                             
         }
         this.stop();
        }
    
    
    
}
