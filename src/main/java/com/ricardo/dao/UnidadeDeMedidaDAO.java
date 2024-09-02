package com.ricardo.dao;

import com.ricardo.model.UnidadeDeMedida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UnidadeDeMedidaDAO {

    private Connection connection;

    public UnidadeDeMedidaDAO() {
        this.connection = Conexao.getConnection();
    }

    public boolean cadastrarUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
        String sql = "INSERT INTO unidade_de_medida (descricao_unidade_medida) VALUES (?);";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, unidadeDeMedida.getDescricaoUnidadeDeMedida());

            if (!validarUnidadeDeMedida(unidadeDeMedida)) {
                return false;
            }

            preparedStatement.execute();
            preparedStatement.close();

            JOptionPane.showMessageDialog(null, "Unidade de medida " + unidadeDeMedida.getDescricaoUnidadeDeMedida() + " cadastrada com sucesso!");
            return true;
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastar " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
        public boolean atualizarUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
        String sql = "UPDATE unidade_de_medida SET descricao_unidade_medida = ? WHERE id_unidade_de_medida = ?;";
        
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, unidadeDeMedida.getDescricaoUnidadeDeMedida());
            preparedStatement.setInt(2, unidadeDeMedida.getIdUnidadeDeMedida());
            
            if (!validarUnidadeDeMedida(unidadeDeMedida)) {
                return false;
            }
            
            preparedStatement.execute();
            preparedStatement.close();
            
            JOptionPane.showMessageDialog(null, "Marca "+ unidadeDeMedida.getDescricaoUnidadeDeMedida() +" atualizada com sucesso!");
            return true;
        } catch(SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean validarUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
        if (!verificarSeJaExisteUnidadeDeMedida(unidadeDeMedida)) {
            JOptionPane.showMessageDialog(null, "Unidade de medida " + unidadeDeMedida.getDescricaoUnidadeDeMedida() + " já existe!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean verificarSeJaExisteUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
        String sql = "SELECT COUNT(*) as contador FROM unidade_de_medida WHERE descricao_unidade_medida = ?;";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, unidadeDeMedida.getDescricaoUnidadeDeMedida());

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
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao contar unidade de medida! " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public ArrayList<UnidadeDeMedida> buscarTodasUnidadesDeMedida() {
        String sql = "SELECT * FROM unidade_de_medida;";
        ArrayList<UnidadeDeMedida> listaDeUnidadeDeMedida = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idUnidadeDeMedida = resultSet.getInt("id_unidade_de_medida");
                String descricaoUnidadeDeMedida = resultSet.getString("descricao_unidade_medida");
                UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida(idUnidadeDeMedida, descricaoUnidadeDeMedida);

                listaDeUnidadeDeMedida.add(unidadeDeMedida);
            }

            preparedStatement.close();
            resultSet.close();

            return listaDeUnidadeDeMedida;
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todas unidades de medidas! " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public UnidadeDeMedida buscarUnidadeDeMedidaPorCodigo(int idUnidadeDeMedida) {
        String sql = "SELECT * FROM unidade_de_medida WHERE id_unidade_de_medida = ?;";
        UnidadeDeMedida unidadeDeMedida = null;

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUnidadeDeMedida);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idUnidadeDeMedidaEncontrada = resultSet.getInt("id_unidade_de_medida");
                String descricaoUnidadeDeMedida = resultSet.getString("descricao_unidade_medida");
                unidadeDeMedida = new UnidadeDeMedida(idUnidadeDeMedidaEncontrada, descricaoUnidadeDeMedida);
            }

            preparedStatement.close();
            resultSet.close();
            return unidadeDeMedida;
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar unidade de medida " + idUnidadeDeMedida + "!" + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar unidade de medida " + idUnidadeDeMedida + "!" + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
