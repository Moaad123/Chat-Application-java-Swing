package Class;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toshiba
 */
public class Button_Send extends Thread{
    String username;
    Friend friend;
    String message; 
    Socket client;
    java.awt.List liste;

    public Button_Send(String username,String message ,Friend friend, Socket client, List liste) {
        this.username = username;
        this.friend = friend;
        this.client = client;
        this.message = message;
        this.liste = liste;
        super.start();
    }

    @Override
    public void run() {
        try{
            FileWriter fw;
            fw = new FileWriter("fichier.csv",true);
            BufferedWriter bw= new BufferedWriter(fw);
            try (PrintWriter pw = new PrintWriter(bw)) {
                PrintStream send= new PrintStream(client.getOutputStream());
                send.println(friend.ami+"-"+username+"-"+message+":Message");
                liste.add(username+" : "+message);
                pw.println(username+"-"+friend.ami+"-"+message);
                pw.flush();
            }
        }catch (IOException ex) {
                            }
    }
    
    
    
    
    
}
