package Class;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Logiin_and_register {
    public Logiin_and_register(String username,String password, Socket client) throws IOException{
    new Thread(new Runnable(){
        public void run(){
            PrintStream login;
            try {
                login = new PrintStream(client.getOutputStream());
                login.println(username+"-"+password+":login");
            }catch (IOException ex) {
                Logger.getLogger(Logiin_and_register.class.getName()).log(Level.SEVERE, null, ex);
                                     }
                          }
    }).start();
                                                                                                 }
    public Logiin_and_register(String username,String password, String name, String last_name,Socket client) throws IOException{
        new Thread(new Runnable(){
        public void run(){
        PrintStream register;
        try {
            register = new PrintStream(client.getOutputStream());
            register.println(username+"-"+password+"-"+name+"-"+last_name+":register");
            }catch (IOException ex) {
                Logger.getLogger(Logiin_and_register.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                          }
        }).start();
                                                                                                                                }
                                  }