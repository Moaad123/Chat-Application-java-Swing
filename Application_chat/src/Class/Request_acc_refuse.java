package Class;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Request_acc_refuse extends Thread{
    String username;
    String friend;
    Socket client;
    String request;
    public Request_acc_refuse(String username,String friend,String request,Socket client) throws IOException{
        this.username = username;
        this.friend = friend;
        this.client = client;
        this.request =  request;
        super.start();
                                                                                                             }
    @Override
    public void run(){
        PrintStream send;
        try {
            send = new PrintStream(client.getOutputStream());
            send.println(username+"-"+friend+":"+request);
        }catch (IOException ex) {
            Logger.getLogger(Request_acc_refuse.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                }
                        }
