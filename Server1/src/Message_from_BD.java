import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Message_from_BD extends Thread{
    String username;
    Socket client;
    public Message_from_BD(String username,Socket client) throws SQLException, IOException{
        this.username = username;
        this.client = client;
        super.start();
                                                                                          }
    public void run(){
        Connection_DB connect = new Connection_DB();
        String request_messages = "SELECT `sender`, `recever`, `text_msg` FROM `messsage_text` WHERE recever='"+username+"'";
        ResultSet messages = connect.selection(request_messages);
        if(!(messages==null)){
            String message="";
            try{
                PrintStream msg;
                while(messages.next()){
                    String sender= messages.getString(1);
                    String recever= messages.getString(2);
                    String messge= messages.getString(3);
                    message+=sender+"-"+recever+"-"+messge+"/";
                              }
                msg = new PrintStream(client.getOutputStream());
                msg.println(message+":message_db");
            }catch (SQLException ex) {
                Logger.getLogger(Message_from_BD.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException ex) {
                Logger.getLogger(Message_from_BD.class.getName()).log(Level.SEVERE, null, ex);
    }
String delete_messages = "DELETE FROM `messsage_text` WHERE recever='"+username+"'";
connect.mise_ajour(delete_messages);
}
}       
   
}
