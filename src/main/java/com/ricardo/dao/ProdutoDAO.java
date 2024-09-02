package com.ricardo.dao;
    
import com.ricardo.model.Marca;
import com.ricardo.model.Produto;
import com.ricardo.model.UnidadeDeMedida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    private Connection connection;
    
    public ProdutoDAO() {
        this.connection = Conexao.getConnection();
    }
    
    public boolean cadastrarProduto(Produto produto) {
        String sql = "INSERT INTO produto (descricao_produto, id_marca, id_unidade_de_medida, situacao, valor_unitario) VALUES (?, ?, ?, ?, ?);";
        
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, produto.getDescricaoProduto());
            preparedStatement.setInt(2, produto.getMarca().getIdMarca());
            preparedStatement.setInt(3, produto.getUnidadeDeMedida().getIdUnidadeDeMedida());
            preparedStatement.setString(4, produto.getSituacao());
            preparedStatement.setFloat(5, produto.getValorUnitario());
            
            if (!validarProduto(produto)) {
                return false;
            }
            
            preparedStatement.execute();
            preparedStatement.close();
            
            JOptionPane.showMessageDialog(null, "Produto "+ produto.getDescricaoProduto() +" cadastrado com sucesso!");
            return true;
        } catch(SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastar " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean atualizarProduto(Produto produto) {
        String sql = "UPDATE produto SET descricao_produto = ?, situacao = ?, id_marca = ?, id_unidade_de_medida = ?, valor_unitario = ? WHERE id_produto = ?;";
        
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, produto.getDescricaoProduto());
            preparedStatement.setString(2, produto.getSituacao());
            preparedStatement.setInt(3, produto.getMarca().getIdMarca());
            preparedStatement.setInt(4, produto.getUnidadeDeMedida().getIdUnidadeDeMedida());
            preparedStatement.setFloat(5, produto.getValorUnitario());
            preparedStatement.setInt(6, produto.getIdProduto());
            
            if (!validarProduto(produto)) {
                return false;
            }
            
            preparedStatement.execute();
            preparedStatement.close();
            
            JOptionPane.showMessageDialog(null, "Produto "+ produto.getDescricaoProduto()+" atualizada com sucesso!");
            return true;
        } catch(SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean validarProduto(Produto produto) {
        if (!verificarSeJaExisteProduto(produto)) {
            JOptionPane.showMessageDialog(null, "Produto "+ produto.getDescricaoProduto() +" já existe!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!verificarSeMarcaExiste(produto.getMarca())) {
            JOptionPane.showMessageDialog(null, "Marca "+ produto.getMarca().getIdMarca() +" não foi encontrada!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!verificarSeUnidadeMedidaExiste(produto.getUnidadeDeMedida())) {
            JOptionPane.showMessageDialog(null, "Unidade de medida "+ produto.getUnidadeDeMedida().getIdUnidadeDeMedida()+" não foi encontrada!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (produto.getDescricaoProduto().isBlank() || produto.getSituacao().isBlank()) {
            JOptionPane.showMessageDialog(null, "Não é possível cadastrar campos sem valor!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public boolean verificarSeUnidadeMedidaExiste(UnidadeDeMedida unidadeDeMedida) {
        String sql = "SELECT COUNT(*) as contador FROM unidade_de_medida WHERE id_unidade_de_medida = ?;";
        
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, unidadeDeMedida.getIdUnidadeDeMedida());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int quantidadeDeRegistrosComMesmaDescricao = resultSet.getInt("contador");
                if (quantidadeDeRegistrosComMesmaDescricao > 0) {
                    return true;
                }
            }
            
            preparedStatement.close();
            resultSet.close();
            
            return false;
        } catch(SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao contar unidade de medida! " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean verificarSeMarcaExiste(Marca marca) {
        String sql = "SELECT COUNT(*) as contador FROM marca WHERE id_marca = ?;";
        
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, marca.getIdMarca());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int quantidadeDeRegistrosComMesmaDescricao = resultSet.getInt("contador");
                if (quantidadeDeRegistrosComMesmaDescricao > 0) {
                    return true;
                }
            }
            
            preparedStatement.close();
            resultSet.close();
            
            return false;
        } catch(SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao contar marca! " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean verificarSeJaExisteProduto(Produto produto) {
        String sql = "SELECT COUNT(*) as contador FROM produto WHERE descricao_produto = ?;";
        
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, produto.getDescricaoProduto());
            
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
            JOptionPane.showMessageDialog(null, "Erro ao contar produto! " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public ArrayList<Produto> buscarTodosProdutos() {
        String sql = "SELECT * FROM produto JOIN marca ON marca.id_marca = produto.id_marca JOIN unidade_de_medida ON unidade_de_medida.id_unidade_de_medida = produto.id_unidade_de_medida;";
        ArrayList<Produto> listaDeProdutos = new ArrayList<>();
        
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int idMarca = resultSet.getInt("id_marca");
                String descricaoMarca = resultSet.getString("descricao_marca");
                Marca marca = new Marca(idMarca, descricaoMarca);
                
                int idUnidadeDeMedida = resultSet.getInt("id_unidade_de_medida");
                String descricaoUnidadeDeMedida = resultSet.getString("descricao_unidade_medida");
                UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida(idUnidadeDeMedida, descricaoUnidadeDeMedida);
                
                int idProduto = resultSet.getInt("id_produto");
                String descricaoProduto = resultSet.getString("descricao_produto");
                String situacao = resultSet.getString("situacao");
                float valor = resultSet.getFloat("valor_unitario");
                Produto produto = new Produto(idProduto, descricaoProduto, situacao, valor, marca, unidadeDeMedida);
                
                listaDeProdutos.add(produto);
            }
            
            preparedStatement.close();
            resultSet.close();
            return listaDeProdutos;
        } catch(SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todos produtos! " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public Produto buscarProdutoPorCodigo(int idProduto) {
        String sql = "SELECT * FROM produto JOIN marca ON marca.id_marca = produto.id_marca JOIN unidade_de_medida ON unidade_de_medida.id_unidade_de_medida = produto.id_unidade_de_medida WHERE produto.id_produto = ?;";
        Produto produto = null;
        
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, idProduto);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int idMarca = resultSet.getInt("id_marca");
                String descricaoMarca = resultSet.getString("descricao_marca");
                Marca marca = new Marca(idMarca, descricaoMarca);
                
                int idUnidadeDeMedida = resultSet.getInt("id_unidade_de_medida");
                String descricaoUnidadeDeMedida = resultSet.getString("descricao_unidade_medida");
                UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida(idUnidadeDeMedida, descricaoUnidadeDeMedida);
                
                int idProdutoEncontrado = resultSet.getInt("id_produto");
                String descricaoProduto = resultSet.getString("descricao_produto");
                String situacao = resultSet.getString("situacao");
                float valor = resultSet.getFloat("valor_unitario");
                produto = new Produto(idProdutoEncontrado, descricaoProduto, situacao, valor, marca, unidadeDeMedida);
            }
            
            preparedStatement.close();
            resultSet.close();
            return produto;
        } catch(SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produto " + idProduto + "!" + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        } 
    }
}
