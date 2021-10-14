/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.awt.List;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toshiba
 */
public class Messages_From_csv extends Thread{
    String username;
    Friend friend;
    java.awt.List liste;
    public Messages_From_csv(String username, Friend friend, List liste) {
        this.username = username;
        this.friend = friend;
        this.liste = liste;
        super.start();
    }
    @Override
    public void run(){
        liste.removeAll();
        Scanner sc=null;
        try {
            sc = new Scanner(Paths.get("fichier.csv").toFile());
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Messages_From_csv.class.getName()).log(Level.SEVERE, null, ex);
                }
          String line ="";
        while(sc.hasNext()){
          line = sc.nextLine();
                if(!line.isEmpty()){
                String[] sr=line.split("-");
                if(sr[1].equals(this.friend.ami) && sr[0].equals(username))
                liste.add(username+" : "+sr[2]);
                if(sr[0].equals(this.friend.ami) && sr[1].equals(username))
                liste.add(this.friend.ami+" : "+sr[2]);
                }
               
        }
                
                             
    }
             
                
       
                            

                               
}    
    

