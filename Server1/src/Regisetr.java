
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Regisetr extends Thread{
    String username;
    String password;
    String name;
    String last_name;
    Socket client;
    public Regisetr(String username,String password,String name,String last_name,Socket client) throws IOException{
        this.username = username;
        this.password = password;
        this.name = name;
        this.last_name = last_name;
        this.client = client;
        super.start();
                                                                                                                    }
    @Override
    public void run(){
        Connection_DB connect = new Connection_DB();
        boolean retur = connect.recherche("select * from user", username);
        int i=0;
        try{
            PrintStream send = new PrintStream(client.getOutputStream());
            if(retur==true){   
                send.println("exist");
            }else{
                String requet2 = "insert into user values('"+username+"','"+password+"','"+name+"','"+last_name+"','offline')";
                i = connect.mise_ajour(requet2);
                if(i==1){ 
                    send.println("succes");
                }
            }
         }catch (IOException ex) {
                Logger.getLogger(Regisetr.class.getName()).log(Level.SEVERE, null, ex);                           
         }
    }


}
