/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.ClientePessoaJuridica;

/**
 *
 * @author giova
 */
public class ClientePessoaJuridicaDao {

    public void createPj(ClientePessoaJuridica pj) {

        Connection con = ConnectionBanco.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO ClientePessoaJuridica(CNPJ, IE, nome, endereco, telefone, email)VALUES(?,?,?,?,?,?)");
            stmt.setString(1, pj.getCNPJ());
            stmt.setString(2, pj.getIE());
            stmt.setString(3, pj.getNome());
            stmt.setString(4, pj.getEndereco());
            stmt.setString(5, pj.getTelefone());
            stmt.setString(6, pj.getEmail());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + ex);
        } finally {
            ConnectionBanco.closeConnection(con, stmt);
        }

    }

    public List<ClientePessoaJuridica> readPj() {

        Connection con = ConnectionBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ClientePessoaJuridica> listpj = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM ClientePessoaJuridica");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ClientePessoaJuridica cpj = new ClientePessoaJuridica();
                cpj.setId(rs.getInt("id"));
                cpj.setCNPJ(rs.getString("CNPJ"));
                cpj.setIE(rs.getString("IE"));
                cpj.setNome(rs.getString("nome"));
                cpj.setEndereco(rs.getString("endereco"));
                cpj.setTelefone(rs.getString("telefone"));
                cpj.setEmail(rs.getString("email"));
                listpj.add(cpj);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientePessoaJuridicaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionBanco.closeConnection(con, stmt, rs);
        }

        return listpj;
    }
    
        public void updatePj(ClientePessoaJuridica pj) {

        Connection con = ConnectionBanco.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE ClientePessoaFisica SET CNPJ = ?, IE = ?,  nome = ?,"
                    + " endereco = ?, telefone = ?, email = ? WHERE id = ?");
            stmt.setString(1, pj.getCNPJ());
            stmt.setString(2, pj.getIE());
            stmt.setString(3, pj.getNome());
            stmt.setString(4, pj.getEndereco());
            stmt.setString(5, pj.getTelefone());
            stmt.setString(6, pj.getEmail());
            stmt.setInt(7, pj.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar: " + ex);
        } finally {
            ConnectionBanco.closeConnection(con, stmt);
        }

    }
        
        public void deletePj(ClientePessoaJuridica pj) {

        Connection con = ConnectionBanco.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM ClientePessoaJuridica WHERE id = ?");
            stmt.setInt(1, pj.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "EXCLUIDO COM SUCESSO");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + ex);
        } finally {
            ConnectionBanco.closeConnection(con, stmt);
        }

    }
        
        public List<ClientePessoaJuridica> searchPj(String cnpj) {

        Connection con = ConnectionBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ClientePessoaJuridica> listpj = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM ClientePessoaJuridica WHERE CNPJ LIKE ?");
            stmt.setString(0, "%"+cnpj+"%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ClientePessoaJuridica cpj1 = new ClientePessoaJuridica();
                cpj1.setId(rs.getInt("id"));
                cpj1.setCNPJ(rs.getString("CNPJ"));
                cpj1.setIE(rs.getString("IE"));
                cpj1.setNome(rs.getString("nome"));
                cpj1.setEndereco(rs.getString("endereco"));
                cpj1.setTelefone(rs.getString("telefone"));
                cpj1.setEmail(rs.getString("email"));
                listpj.add(cpj1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientePessoaJuridicaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionBanco.closeConnection(con, stmt, rs);
        }

        return listpj;
    }

}
