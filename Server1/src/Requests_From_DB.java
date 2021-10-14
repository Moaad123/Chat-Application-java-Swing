
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toshiba
 */
public class Requests_From_DB extends Thread{
    Socket client;
    String username;
    public Requests_From_DB(String username,Socket client){
        this.client = client;
        this.username = username;
        super.start();
    }
    @Override
    public void run(){
        Connection_DB connect = new Connection_DB();
        ResultSet res = connect.selection("select sender from request where recever='"+username+"'");
        if(res!=null){
            try {
                String names="";
                while(res.next()){
                    String name=res.getString(1);
                    names+=name+"-";
                                 }
                 PrintStream send=new PrintStream(client.getOutputStream());
                 send.println(names);
            }catch (SQLException ex) {
                Logger.getLogger(Requests_From_DB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Requests_From_DB.class.getName()).log(Level.SEVERE, null, ex);
            }
                        }
        
                     }
}
