/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toshiba
 */
public class Send_Request extends Thread{
    String username;
    Socket client;
    String friend;
    public Send_Request(String username,String friend,Socket client) throws IOException{
        this.username = username;
        this.client = client;
        this.friend = friend;
        super.start();
    }
    @Override
    public void run(){
        try{
            PrintStream send = new PrintStream(this.client.getOutputStream());
            send.println(username+"-"+friend+":send_request");
        }catch (IOException ex) {
            Logger.getLogger(Send_Request.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
