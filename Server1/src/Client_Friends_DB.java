import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client_Friends_DB extends Thread{
    String username;
    Socket client;
    public Client_Friends_DB(String username,Socket client){    
        this.username = username;
        this.client = client;
        super.start();
    }
            public void run(){
                Connection_DB connect = new Connection_DB();
                String sql = "SELECT DISTINCT U2.username from user as U1,user as U2 where U1.username='"+username+"' and U2.username IN( (SELECT friend2 FROM friend_to where U1.username = friend1) UNION (SELECT friend1 FROM friend_to where U1.username = friend2))";
                ResultSet   user = connect.selection(sql);
                if(!(user==null)){
                    String names = "";
                    try {
                        while(user.next()){
                            String name=  user.getString(1);
                            names+=name+"-";
                                            }
                    }catch (SQLException ex) {
                        Logger.getLogger(Client_Friends_DB.class.getName()).log(Level.SEVERE, null, ex);
                                              }
                    PrintStream send;
                    try {
                        send = new PrintStream(client.getOutputStream());
                        send.println(names+":friends");
                    }catch (IOException ex) {
                        Logger.getLogger(Client_Friends_DB.class.getName()).log(Level.SEVERE, null, ex);
                                             }
                                             }
                              }
                                                                                                }
                                
