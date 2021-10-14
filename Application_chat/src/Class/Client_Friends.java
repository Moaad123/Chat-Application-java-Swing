package Class;


import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Client_Friends extends Thread{
    JTable table;
    String friends;
    public Client_Friends(JTable table,String friends){
        this.table =table;
        this.friends = friends;
        super.start();
                                                                        }
    @Override
    public void run(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String[] friend = friends.split("-");
        for (String friend1 : friend) {
            model.addRow(new Object[]{friend1, "offline"});
        }                  
    }                     
}
