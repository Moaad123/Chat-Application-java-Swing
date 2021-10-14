/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JTable;

/**
 *
 * @author toshiba
 */
public class Read extends Thread{
     String username;
     Socket client;
     java.awt.List liste;
     Friend friend;
     JTable table;
    public Read(String username,Friend friend ,Socket client,JTable table, List liste) {
        this.username = username;
        this.client = client;
        this.liste = liste;
        this.table =  table;
        this.friend = friend;
        super.start();
    }

    @Override
    public void run() {
      try{ 
        BufferedReader flux= new BufferedReader(new InputStreamReader(client.getInputStream()));
        while(true){
            String line=null;
           if(flux.ready()){
               line = flux.readLine();
           }
           if(this.isInterrupted()){
                break;
           }
           if(line!=null){
                String[] flux_reader = line.split(":");
                if(flux_reader[1].equals("message_db"))
                   new Msg_server_to_CSV(flux_reader[0]);
                if(flux_reader[1].equals("friends"))
                   new Client_Friends(table,flux_reader[0]);
                else if(flux_reader[1].equals("status")){
                    new Check(flux_reader[0],table,client);
                }else if(flux_reader[1].equals("message")){
                      String[] arr =flux_reader[0].split("-");
                      FileWriter fw = new FileWriter("fichier.csv",true);
                      BufferedWriter bw= new BufferedWriter(fw);
                      PrintWriter pw = new PrintWriter(bw);
                   if(arr[0].equals(friend.ami)){
                      liste.add(arr[0]+" : "+arr[2]);
                      pw.println(flux_reader[0]);
                      pw.flush();
                      pw.close();
                   }else{
                      pw.println(flux_reader[0]);
                      pw.flush();
                      pw.close();                   
                    }
                 }
            }
        }
                            
              }catch (IOException ex) {
                       
                                       }   
    }
    public void close(){
        this.interrupt();
    }
    
     
}
