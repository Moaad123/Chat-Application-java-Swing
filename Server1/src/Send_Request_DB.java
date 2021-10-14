

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toshiba
 */
public class Send_Request_DB extends Thread{
    String username;
    String friend;
    public Send_Request_DB(String username,String friend){
        this.username = username;
        this.friend = friend;
        super.start();
    }
    @Override
    public void run(){
        Connection_DB connect = new Connection_DB();
         String send= "insert into request values('"+username+"','"+friend+"')";
         connect.mise_ajour(send);
    }
    
}
