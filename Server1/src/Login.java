
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Login extends Thread{
    String username;
    String password;
    Socket client;
    public Login(String username,String password,Socket client) throws IOException{
       this.username = username;
       this.password = password;
       this.client = client;
       super.start();
                                                                                  }
            Connection_DB connect = new Connection_DB();
            public void run(){
                boolean cond = connect.rechercher("Select * from user",username,password);
                if(cond == true){
                    PrintStream send;
                    try {
                    send = new PrintStream(client.getOutputStream());
                    send.println("true");
                    Table_utilisateur.add_user(username,client);
                    connect.mise_ajour("update user set status='online' where username='"+username+"'");
                    new ClientWorker(username,client);
                    }catch (IOException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    PrintStream send;
                    try {
                        send = new PrintStream(client.getOutputStream());
                        send.println("false");
                        }catch (IOException ex) {
                            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                                                } 
                     }
                              }
        
             
                                                                                   }   
                       
