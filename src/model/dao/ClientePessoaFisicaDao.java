
package model.dao;

import connection.ConnectionBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ClientePessoaFisica;



public class ClientePessoaFisicaDao {
    
    public void createPf(ClientePessoaFisica pf){
        
        Connection con = ConnectionBanco.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO ClientePessoaFisica(CPF, nome, endereco, telefone, email)VALUES(?,?,?,?,?)");
            stmt.setString(1, pf.getCPF());
            stmt.setString(2, pf.getNome());
            stmt.setString(3, pf.getEndereco());
            stmt.setString(4, pf.getTelefone());
            stmt.setString(5, pf.getEmail());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso");                 
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + ex);
        } finally {
        ConnectionBanco.closeConnection(con, stmt);
    }
        
    }
    
    public List<ClientePessoaFisica> read(){
        
        Connection con = ConnectionBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ClientePessoaFisica> listpf = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM ClientePessoaFisica");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                
                ClientePessoaFisica cpf = new ClientePessoaFisica();
                cpf.setId(rs.getInt("id"));
                cpf.setCPF(rs.getString("CPF"));
                cpf.setNome(rs.getString("nome"));
                cpf.setEndereco(rs.getString("endereco"));
                cpf.setTelefone(rs.getString("telefone"));
                cpf.setEmail(rs.getString("email"));
                listpf.add(cpf);
                                          
                
            }
                    
             } catch (SQLException ex) {
            Logger.getLogger(ClientePessoaFisicaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionBanco.closeConnection(con, stmt, rs);          
        }
        
        return listpf;
    }
    
   
    public void updatePf(ClientePessoaFisica pf){
        
        Connection con = ConnectionBanco.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE ClientePessoaFisica SET CPF = ?, nome = ?, endereco = ?,"
                    + " telefone = ?, email = ? WHERE id = ?");
            stmt.setString(1, pf.getCPF());
            stmt.setString(2, pf.getNome());
            stmt.setString(3, pf.getEndereco());
            stmt.setString(4, pf.getTelefone());
            stmt.setString(5, pf.getEmail());
            stmt.setInt(6, pf.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");                 
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar: " + ex);
        } finally {
        ConnectionBanco.closeConnection(con, stmt);
    }
        
    }
    
    public void deletePf(ClientePessoaFisica pf){
        
        Connection con = ConnectionBanco.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM ClientePessoaFisica WHERE id = ?");
            stmt.setInt(1, pf.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "EXCLUIDO COM SUCESSO");                 
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + ex);
        } finally {
        ConnectionBanco.closeConnection(con, stmt);
    }
        
    }
    
    public List<ClientePessoaFisica> searchPf(String cpf){
        
        Connection con = ConnectionBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ClientePessoaFisica> listpf = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM ClientePessoaFisica WHERE CPF LIKE ?");
            stmt.setString(1, "%"+cpf+"%");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                
                ClientePessoaFisica cpf1 = new ClientePessoaFisica();
                cpf1.setId(rs.getInt("id"));
                cpf1.setCPF(rs.getString("CPF"));
                cpf1.setNome(rs.getString("nome"));
                cpf1.setEndereco(rs.getString("endereco"));
                cpf1.setTelefone(rs.getString("telefone"));
                cpf1.setEmail(rs.getString("email"));
                listpf.add(cpf1);
                                          
                
            }
                    
             } catch (SQLException ex) {
            Logger.getLogger(ClientePessoaFisicaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionBanco.closeConnection(con, stmt, rs);          
        }
        
        return listpf;
    }
    
}
