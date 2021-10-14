
import java.net.*;
import java.util.*;

public class Table_utilisateur{
static HashMap<String,Socket> map= new HashMap<String,Socket>(); ;
    public static void add_user(String username,Socket client){
       map.put(username,client);
       
    }
    public static HashMap<String,Socket> getusers(){
        return map;
    }
}