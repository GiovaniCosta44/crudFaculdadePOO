
package connection;

import com.sun.istack.internal.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;


public class ConnectionBanco {
    
   private static final String driverJDBC = "com.mysql.jdbc.Driver";
   private static final String URL = "jdbc:mysql://localhost:3306/projetopoo";
   private static final String USER = "giovani";
   private static final String PASS = "tc3prbia";
    
    


    public static Connection getConnection(){



            try {
                Class.forName(driverJDBC);
                return DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException("Erro na Conexão", ex);
            }
         }  

    public static void closeConnection(Connection con){
        
        try {
            if(con != null){
                con.close();
                }   
        } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(ConnectionBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt) {
        
        closeConnection(con);
        
        try {
            if(stmt != null){
                stmt.close();
            }            
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(ConnectionBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        
        
        closeConnection(con, stmt);
        
        try {
            if(rs != null){
                rs.close();
            }            
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(ConnectionBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}







/*
public static void realizarUpdate(String sql){
    try{
        System.out.println("Conectando ao banco....");
        java.sql.Connection conexao = DriverManager.getConnection(url, "giovani", "tc3prbia");
        System.out.println("Conexao Efetuada com Sucesso");
        java.sql.Statement st = conexao.createStatement();
        st.executeUpdate(sql);
        st.close();
        conexao.close();
    } catch(Exception e){
        System.out.println("Falha ao conectar com obanco de dados" + e.toString());
    }
  }
*/

