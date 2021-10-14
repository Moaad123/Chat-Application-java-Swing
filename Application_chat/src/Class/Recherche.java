package Class;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Recherche extends Thread{
    JTable table;
    String username;
    Socket client;
    public Recherche(JTable table,String username,Socket client) throws IOException{
        this.username = username;
        this.table = table;
        this.client = client;
        super.start();
                                                                                    }
    @Override
    public void run(){
        PrintStream search;
        try {
            search = new PrintStream(client.getOutputStream());
            search.println(username+":search");
        }catch (IOException ex) {
            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                                 }
        BufferedReader search_result;
        try {
             DefaultTableModel model = (DefaultTableModel) table.getModel();
             search_result = new BufferedReader(new InputStreamReader(client.getInputStream()));
             String user = search_result.readLine();
             model.addRow(new Object[]{user,"Send"}); 
        }catch (IOException ex) {
            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
        }
                      }

}
