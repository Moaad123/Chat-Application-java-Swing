package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Msg_server_to_CSV extends Thread{
    String message;
    public Msg_server_to_CSV(String message) throws IOException{
        this.message = message;
        super.start();
                                                              }
    @Override
    public void run(){
        FileWriter fw = null;
        try {
            fw = new FileWriter("fichier.csv",true);
        }catch (IOException ex) {
            Logger.getLogger(Msg_server_to_CSV.class.getName()).log(Level.SEVERE, null, ex);
                                }
        BufferedWriter bw= new BufferedWriter(fw);
        try (PrintWriter pw = new PrintWriter(bw)) {
            String[] messages=message.split("/");
            for (String message1 : messages) {
                pw.println(message1);
                pw.flush();
            }
        }
                       }

    
}
