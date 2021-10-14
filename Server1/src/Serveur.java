import java.net.*;
import java.io.*;

public class Serveur extends Thread {
    public ServerSocket Server;
    public Serveur(){
        try{
            this.Server = new ServerSocket(4000);
            super.start();
        }catch(IOException e){
                             }
                    }
    @Override
    public void run(){
       while(true){
            try{
                Socket Client = this.Server.accept();
                BufferedReader fluxLecture = new BufferedReader(new InputStreamReader(Client.getInputStream()));
                String U = fluxLecture.readLine();
                String[] str=U.split(":");
                if(str[1].equals("login")){
                    String[] login = str[0].split("-");
                    new Login(login[0],login[1],Client);
                }else if(str[1].equals("register")){
                    String[] register =str[0].split("-");
                    new Regisetr(register[0],register[1],register[2],register[3],Client);                               
                }
            }catch (IOException ex) {                        
            }                             
       }                                
    } 
    public static void main(String[] args){
        new Serveur();                                      
    }
}