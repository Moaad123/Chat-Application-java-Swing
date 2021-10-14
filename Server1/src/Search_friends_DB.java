import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Search_friends_DB extends Thread{
    String username;
    Socket client;
    public Search_friends_DB(String username,Socket client) throws IOException{
        this.username = username;
        this.client = client;
        super.start();                                                                                      
    }
    Connection_DB connect = new Connection_DB();
    @Override
    public void run(){
        String Requet = "Select * from user";
        if(connect.recherche(Requet,username)){
            PrintStream  search;
            try {
                search = new PrintStream(client.getOutputStream());
                search.println(username);
            }catch (IOException ex) {                   
            }                                       
        }                  
    }

                                                                                             
}
