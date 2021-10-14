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
public class Messages_From_server extends Thread{
 String username;
    Socket client;

    public Messages_From_server(String username, Socket client) {
        this.username = username;
        this.client = client;
        super.start();
    }

    @Override
    public void run() {
        try {
            PrintStream send= new PrintStream(client.getOutputStream());
            send.println(username+":friends_message");
        } catch (IOException ex) {
            Logger.getLogger(Friends_From_server.class.getName()).log(Level.SEVERE, null, ex);
        }    
}
}
