/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author toshiba
 */
package Class;
    import java.sql.*;
import javax.swing.JOptionPane;

public class Connection_DB {
  private  Connection cn;
    public Connection_DB(){
        try {
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chat","root","");
        
    }catch (Exception e) {
      System.out.println(e);
    }
    }

    public int mise_ajour(String sql){
	   int t=0;
		try {
	
			Statement st;
               st = cn.createStatement();
			t=st.executeUpdate(sql);
		  
		} catch (Exception e) {
			   System.out.println(e);
		}
	 
		   return t;
	   }
   
    public  ResultSet selection(String sql){
	  ResultSet rs=null;
		try {
	
			Statement st=cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		  rs=st.executeQuery(sql);
		   
		} catch (Exception e) {
			  System.out.println(e);
		}
	  return rs;
	 }
    
	public boolean rechercher(String req,String cond,String cond2){
     boolean i=false;
    try {
     ResultSet rs=selection(req);
        while (rs.next()) {   
            String rs1 = rs.getString(1);
               String rs2= rs.getString(2);
            if (rs1.equals(cond) && rs2.equals(cond2)) {
             
                i=true;
                 return i;
            }
        }
    } catch (Exception e) {
         System.out.println(e);
    }
   return i;

}
        
        public boolean recherche(String req,String cond){
     boolean i=false;
    try {
     ResultSet rs=selection(req);
        while (rs.next()) {   
            String rs1 = rs.getString(1);
               String rs2= rs.getString(2);
            if (rs1.equals(cond)) {
         
                i=true;
                 return i;
            }
        }
    } catch (Exception e) {
         System.out.println(e);
    }
   return i;

}



}

