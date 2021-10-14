/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author toshiba
 */
public class Send_to_check extends Thread{
    Socket client;
    public Send_to_check(Socket client){
        this.client = client;
        super.start();
    }

    @Override
    public void run() {
         try {
            PrintStream send = new PrintStream(client.getOutputStream());
            send.println(":check");
        } catch (IOException ex) {
        }
    }
    
    
}
