package com.ricardo.dao;

import com.ricardo.model.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MarcaDAO {
    private Connection connection;
    
    public MarcaDAO() {
        this.connection = Conexao.getConnection();
    }
    
    public boolean cadastrarMarca(Marca marca) {
        String sql = "INSERT INTO marca (descricao_marca) VALUES (?);";
        
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, marca.getDescricaoMarca());
            
            if (!validarMarca(marca)) {
                return false;
            }
            
            preparedStatement.execute();
            preparedStatement.close();
            
            JOptionPane.showMessageDialog(null, "Marca "+ marca.getDescricaoMarca() +" cadastrada com sucesso!");
            return true;
        } catch(SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean atualizarMarca(Marca marca) {
        String sql = "UPDATE marca SET descricao_marca = ? WHERE id_marca = ?;";
        
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, marca.getDescricaoMarca());
            preparedStatement.setInt(2, marca.getIdMarca());
            
            if (!validarMarca(marca)) {
                return false;
            }
            
            preparedStatement.execute();
            preparedStatement.close();
            
            JOptionPane.showMessageDialog(null, "Marca "+ marca.getDescricaoMarca() +" atualizada com sucesso!");
            return true;
        } catch(SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean validarMarca(Marca marca) {
        if (!verificarSeJaExisteMarca(marca)) {
            JOptionPane.showMessageDialog(null, "Marca "+ marca.getDescricaoMarca() +" já existe!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (marca.getDescricaoMarca().isBlank()) {
            JOptionPane.showMessageDialog(null, "Informe uma marca!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public boolean verificarSeJaExisteMarca(Marca marca) {
        String sql = "SELECT COUNT(*) as contador FROM MARCA WHERE descricao_marca = ?;";
        
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, marca.getDescricaoMarca());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int quantidadeDeRegistrosComMesmaDescricao = resultSet.getInt("contador");
                if (quantidadeDeRegistrosComMesmaDescricao > 0) {
                    return false;
                }
            }
            
            preparedStatement.close();
            resultSet.close();
            
            return true;
        } catch(SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao contar marca! " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public ArrayList<Marca> buscarTodasMarcas() {
        String sql = "SELECT * FROM marca;";
        ArrayList<Marca> listaDeMarcas = new ArrayList<>();
        
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int idMarca = resultSet.getInt("id_marca");
                String descricaoMarca = resultSet.getString("descricao_marca");
                Marca marca = new Marca(idMarca, descricaoMarca);
                listaDeMarcas.add(marca);
            }
            
            preparedStatement.close();
            resultSet.close();
            return listaDeMarcas;
        } catch(SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todas marcas! " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public Marca buscarMarcaPorCodigo(int idMarca) {
        String sql = "SELECT * FROM marca WHERE id_marca = ?;";
        Marca marca = null;
        
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, idMarca);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int idMarcaEncontrada = resultSet.getInt("id_marca");
                String descricaoMarca = resultSet.getString("descricao_marca");
                marca = new Marca(idMarcaEncontrada, descricaoMarca);
            }
            
            preparedStatement.close();
            resultSet.close();
            return marca;
        } catch(SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar marca " + idMarca + "!" + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch(Exception error) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar marca " + idMarca + "!" + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
