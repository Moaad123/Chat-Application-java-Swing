import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


class ClientWorker extends Thread{
    final private Socket client;
    public String username;
    ClientWorker(String username,Socket client){
        this.client = client;
        this.username = username;
        super.start();                                            
    }

    @Override
    public void run(){
    String line;
    BufferedReader in = null;
    PrintWriter out = null;
    try{
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    }catch (IOException e) {
        System.exit(-1);                        
    }
    Connection_DB connect =  new Connection_DB();
    while(true){
        try{
            line = in.readLine(); 
            String[] str = line.split(":");
            HashMap<String,Socket> User = Table_utilisateur.getusers();
            if(str[1].equals("log_out")){
                connect.mise_ajour("update user set status='offline' where username='"+str[0]+"'");
            }else if(str[1].equals("Message")){
                boolean status = false;
                String[] Message = str[0].split("-");
                for(String us:User.keySet()){
                    if(us.equals(Message[0])){
                        status = true;
                        Socket sock = (Socket) User.get(us);
                        PrintStream msg = new PrintStream(sock.getOutputStream(),true);
                        msg.println(Message[1]+"-"+Message[0]+"-"+Message[2]+":message");
                        break;                         
                    }                             
                }
                if(status == false){
                    connect.mise_ajour("INSERT INTO `messsage_text`(`sender`, `recever`, `text_msg`) VALUES ('"+Message[1]+"','"+Message[0]+"','"+Message[2]+"')");         
                }
            }else if(str[1].equals("confirm")){
                        String[] usr=str[0].split("-");
                        connect.mise_ajour("insert into friend_to values('"+usr[0]+"','"+usr[1]+"')");
                        connect.mise_ajour("DELETE FROM `request` WHERE sender='"+usr[1]+"' and recever='"+usr[0]+"'");
            }else if(str[1].equals("remove")){
                        String[] usr=str[0].split("-");
                        connect.mise_ajour("DELETE FROM `request` WHERE sender='"+usr[1]+"' and recever='"+usr[0]+"'");
            }else if(str[1].equals("search")){
                        new Search_friends_DB(str[0],client);
            }else if(str[1].equals("send_request")){
                        String[] send=str[0].split("-");
                        new Send_Request_DB(send[0],send[1]);
           }else if(str[1].equals("request")){
               new Requests_From_DB(str[0],client);
           }else if(str[1].equals("username_friends")){
               new Message_from_BD(str[0],client);
           }else if(str[1].equals("friends_message")){
               new Client_Friends_DB(str[0],client);
           }else if(str[1].equals("check")){
               new Check_BD(client);
           }
        }catch (IOException e){
            System.exit(-1);
            }catch (SQLException ex){
            Logger.getLogger(ClientWorker.class.getName()).log(Level.SEVERE, null, ex);                         
            }
        }
    }
  }