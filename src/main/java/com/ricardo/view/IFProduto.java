/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.ricardo.view;

import com.ricardo.dao.MarcaDAO;
import com.ricardo.dao.UnidadeDeMedidaDAO;
import java.util.ArrayList;
import javax.swing.JTextField;
import com.ricardo.dao.UnidadeDeMedidaDAO;
import com.ricardo.dao.ProdutoDAO;
import com.ricardo.model.Marca;
import com.ricardo.model.Produto;
import com.ricardo.model.UnidadeDeMedida;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ricardorhv
 */
public class IFProduto extends javax.swing.JInternalFrame {
    private ArrayList<Marca> listaDeMarcas;
    private ArrayList<UnidadeDeMedida> listaDeUnidadeDeMedida;
    private ArrayList<Produto> listaDeProdutos;
    private ArrayList<Produto> listaDeProdutosFiltrados;
    ProdutoDAO produtoDAO;
    boolean tabelaDeProdutoJaFoiCarregada = false;
    
    public IFProduto() {
        initComponents();
        produtoDAO = new ProdutoDAO();
        
        inicializarCombosBox();
    }
    
    public void atualizarListaDeProdutos() {
        this.listaDeProdutos = produtoDAO.buscarTodosProdutos();
    }
    
    public void filtrarProduto(String pesquisa) {
        this.listaDeProdutosFiltrados = new ArrayList<>();
        
        for (Produto produto : this.listaDeProdutos) {
            if (produto.getDescricaoProduto().toUpperCase().contains(pesquisa.toUpperCase().trim())) {
                this.listaDeProdutosFiltrados.add(produto);
            }
        }
    }
    
    public void carregarTabelaComListaDeProdutos() {
        String colunas[] = {"Id", "Nome", "Marca", "Unidade Medida", "Situação", "Valor"};
        atualizarListaDeProdutos();
        
        int quantidadeDeColunas = colunas.length;
        int quantidadeDelinhas = this.listaDeProdutos.size();
        
        Object[][] dadosDasMarcas = new Object[quantidadeDelinhas][quantidadeDeColunas];
        
        for (int linha = 0; linha < quantidadeDelinhas; linha++) {
            dadosDasMarcas[linha][0] = this.listaDeProdutos.get(linha).getIdProduto();
            dadosDasMarcas[linha][1] = this.listaDeProdutos.get(linha).getDescricaoProduto();
            dadosDasMarcas[linha][2] = this.listaDeProdutos.get(linha).getMarca().getDescricaoMarca();
            dadosDasMarcas[linha][3] = this.listaDeProdutos.get(linha).getUnidadeDeMedida().getDescricaoUnidadeDeMedida();
            dadosDasMarcas[linha][4] = this.listaDeProdutos.get(linha).getSituacao();
            dadosDasMarcas[linha][5] = this.listaDeProdutos.get(linha).getValorUnitario();
        }
        
        DefaultTableModel model = new DefaultTableModel(dadosDasMarcas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblProdutos.setModel(model);
    }
    
    public void carregarTabelaComListaDeProdutosFiltrados() {
        String colunas[] = {"Id", "Nome", "Marca", "Unidade Medida", "Situação", "Valor"};
        atualizarListaDeProdutos();
        
        int quantidadeDeColunas = colunas.length;
        int quantidadeDelinhas = this.listaDeProdutosFiltrados.size();
        
        Object[][] dadosDasMarcas = new Object[quantidadeDelinhas][quantidadeDeColunas];
        
        for (int linha = 0; linha < quantidadeDelinhas; linha++) {
            dadosDasMarcas[linha][0] = this.listaDeProdutosFiltrados.get(linha).getIdProduto();
            dadosDasMarcas[linha][1] = this.listaDeProdutosFiltrados.get(linha).getDescricaoProduto();
            dadosDasMarcas[linha][2] = this.listaDeProdutosFiltrados.get(linha).getMarca().getDescricaoMarca();
            dadosDasMarcas[linha][3] = this.listaDeProdutosFiltrados.get(linha).getUnidadeDeMedida().getDescricaoUnidadeDeMedida();
            dadosDasMarcas[linha][4] = this.listaDeProdutosFiltrados.get(linha).getSituacao();
            dadosDasMarcas[linha][5] = this.listaDeProdutosFiltrados.get(linha).getValorUnitario();
        }
        
        DefaultTableModel model = new DefaultTableModel(dadosDasMarcas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblProdutos.setModel(model);
    }
    
    public void carregarProduto(Produto produto) {
        if (produto != null) {
            tfAlterarNome.setText(produto.getDescricaoProduto());
            tfAlterarValorUnitario.setText(String.valueOf(produto.getValorUnitario()));
            
            int indexDaMarcaDoProduto = 0;
            
            for (Marca marca : this.listaDeMarcas) {
                if (marca.getIdMarca() == produto.getMarca().getIdMarca()) {
                    indexDaMarcaDoProduto = this.listaDeMarcas.indexOf(marca);
                }
            }
            
            this.cbAlterarMarca.setSelectedIndex(indexDaMarcaDoProduto);
            System.out.println(indexDaMarcaDoProduto);
            
            int indexDaUnidadeDeMedidaDoProduto = 0;
            
            for (UnidadeDeMedida unidadeDeMedida : this.listaDeUnidadeDeMedida) {
                if (unidadeDeMedida.getIdUnidadeDeMedida() == produto.getUnidadeDeMedida().getIdUnidadeDeMedida()) {
                    indexDaUnidadeDeMedidaDoProduto = this.listaDeUnidadeDeMedida.indexOf(unidadeDeMedida);
                }
            }
            
            this.cbAlterarUnidade.setSelectedIndex(indexDaUnidadeDeMedidaDoProduto);
            System.out.println(indexDaUnidadeDeMedidaDoProduto);
            
            this.cbAlterarSituacao.setSelectedIndex(produto.getSituacao().equals("ATIVO") ? 0 : 1);
        }
    }
    
    public Produto buscarProdutoPorId(String idProduto) {
        Produto produto;
        
        if (!idProduto.isBlank()) {
            try {
                produto = produtoDAO.buscarProdutoPorCodigo(Integer.parseInt(idProduto));
                if (produto != null) {
                    return produto;
                }
                
                JOptionPane.showMessageDialog(null, "Produto " + idProduto + " não encontrada", "ERROR", JOptionPane.ERROR_MESSAGE);
                limparCampos(tfAlterarNome, tfAlterarValorUnitario);
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Digite um valor númerico no código", "ERROR", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        
        return null;
    }
    
    private void limparCampos(JTextField tf1, JTextField tf2) {
        tf1.setText("");
        tf2.setText("");
    }
    
    private void limparCampos(JTextField tf1, JTextField tf2, JTextField tf3) {
        tf1.setText("");
        tf2.setText("");
        tf3.setText("");
    }
    
    public void inicializarCombosBox() {
        MarcaDAO marcaDAO = new MarcaDAO();
        UnidadeDeMedidaDAO unidadeDeMedidaDAO = new UnidadeDeMedidaDAO();
        
        this.listaDeMarcas = marcaDAO.buscarTodasMarcas();
        this.listaDeUnidadeDeMedida = unidadeDeMedidaDAO.buscarTodasUnidadesDeMedida();
        
        cbCadastrarMarca.removeAllItems();
        cbCadastrarUnidade.removeAllItems();
        
        cbAlterarMarca.removeAllItems();
        cbAlterarUnidade.removeAllItems();
        
        for (Marca marca : this.listaDeMarcas) {
            cbCadastrarMarca.addItem(marca.getDescricaoMarca());
            cbAlterarMarca.addItem(marca.getDescricaoMarca());
        }
        
        for (UnidadeDeMedida unidadeDeMedida : this.listaDeUnidadeDeMedida) {
            cbCadastrarUnidade.addItem(unidadeDeMedida.getDescricaoUnidadeDeMedida());
            cbAlterarUnidade.addItem(unidadeDeMedida.getDescricaoUnidadeDeMedida());
        }   
    }
    
    public Marca buscarMarcaSelecionadaNoCb(JComboBox cb) {
        Marca marcaSelecionada = null;
        
        for (Marca marca : this.listaDeMarcas) {
            if (marca.getDescricaoMarca().equals(cb.getSelectedItem())) {
                marcaSelecionada = marca;
            }
        }
        
        return marcaSelecionada;
    }
    
    public UnidadeDeMedida buscarUnidadeDeMedidaSelecionadaNoCb(JComboBox cb) {
        UnidadeDeMedida unidadeDeMedidaSelecionada = null;
        
        for (UnidadeDeMedida unidadeDeMedida : this.listaDeUnidadeDeMedida) {
            if (unidadeDeMedida.getDescricaoUnidadeDeMedida().equals(cb.getSelectedItem())) {
                unidadeDeMedidaSelecionada = unidadeDeMedida;
            }
        }
        
        return unidadeDeMedidaSelecionada;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbJanela = new javax.swing.JTabbedPane();
        pnCadastrar = new javax.swing.JPanel();
        cbCadastrarMarca = new javax.swing.JComboBox<>();
        tfCadastrarNome = new javax.swing.JTextField();
        cbCadastrarUnidade = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnCadastrarProduto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        tfCadastrarValorUnitario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbCadastrarSituacao = new javax.swing.JComboBox<>();
        pnAlterar = new javax.swing.JPanel();
        tfAlterarNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbAlterarMarca = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbAlterarUnidade = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfAlterarValorUnitario = new javax.swing.JTextField();
        btnAlterar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        tfAlterarCodigo = new javax.swing.JTextField();
        cbAlterarSituacao = new javax.swing.JComboBox<>();
        pnConsultar = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        tblResetarTabela = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        tfPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnSairConsultar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        setClosable(true);

        tbJanela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbJanelaMouseClicked(evt);
            }
        });

        jLabel2.setText("Nome");

        btnCadastrarProduto.setText("Cadastrar");
        btnCadastrarProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCadastrarProdutoMouseClicked(evt);
            }
        });

        jLabel4.setText("Valor unitário");

        jLabel6.setText("Marca");

        jLabel7.setText("Unidade de medida");

        jLabel8.setText("Situação");

        cbCadastrarSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ATIVO", "INATIVO" }));

        javax.swing.GroupLayout pnCadastrarLayout = new javax.swing.GroupLayout(pnCadastrar);
        pnCadastrar.setLayout(pnCadastrarLayout);
        pnCadastrarLayout.setHorizontalGroup(
            pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastrarLayout.createSequentialGroup()
                .addGroup(pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCadastrarLayout.createSequentialGroup()
                        .addGap(303, 303, 303)
                        .addGroup(pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfCadastrarValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addComponent(btnCadastrarProduto)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCadastrarLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnCadastrarLayout.createSequentialGroup()
                                .addGroup(pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnCadastrarLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cbCadastrarSituacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbCadastrarUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)))
                            .addGroup(pnCadastrarLayout.createSequentialGroup()
                                .addGroup(pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(tfCadastrarNome, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(cbCadastrarMarca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(70, 70, 70))
        );
        pnCadastrarLayout.setVerticalGroup(
            pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastrarLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCadastrarNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCadastrarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnCadastrarLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCadastrarSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnCadastrarLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCadastrarUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCadastrarValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(btnCadastrarProduto)
                .addGap(154, 154, 154))
        );

        tbJanela.addTab("Cadastrar", pnCadastrar);

        jLabel3.setText("Nome");

        jLabel9.setText("Marca");

        jLabel10.setText("Situação");

        jLabel11.setText("Unidade de medida");

        jLabel5.setText("Valor unitário");

        btnAlterar.setText("Alterar");
        btnAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAlterarMouseClicked(evt);
            }
        });

        jLabel12.setText("Código");

        tfAlterarCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfAlterarCodigoFocusLost(evt);
            }
        });

        cbAlterarSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ATIVO", "INATIVO" }));

        javax.swing.GroupLayout pnAlterarLayout = new javax.swing.GroupLayout(pnAlterar);
        pnAlterar.setLayout(pnAlterarLayout);
        pnAlterarLayout.setHorizontalGroup(
            pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAlterarLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnAlterarLayout.createSequentialGroup()
                        .addGroup(pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnAlterarLayout.createSequentialGroup()
                                .addGap(240, 515, Short.MAX_VALUE)
                                .addComponent(btnAlterar))
                            .addGroup(pnAlterarLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfAlterarValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnAlterarLayout.createSequentialGroup()
                                .addGroup(pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10)
                                    .addComponent(cbAlterarSituacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addComponent(tfAlterarNome, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                                .addGap(16, 16, 16)
                                .addGroup(pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnAlterarLayout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnAlterarLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cbAlterarMarca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbAlterarUnidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(78, 78, 78))
                    .addGroup(pnAlterarLayout.createSequentialGroup()
                        .addGroup(pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(tfAlterarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnAlterarLayout.setVerticalGroup(
            pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAlterarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAlterarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAlterarNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAlterarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbAlterarUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbAlterarSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnAlterarLayout.createSequentialGroup()
                        .addGroup(pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(30, 30, 30)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAlterarValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(btnAlterar)
                .addGap(128, 128, 128))
        );

        tbJanela.addTab("Alterar", pnAlterar);

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Marca", "Unidade de medida", "Situação", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProdutos);

        tblResetarTabela.setText("Resetar");
        tblResetarTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblResetarTabelaMouseClicked(evt);
            }
        });

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPesquisarMouseClicked(evt);
            }
        });

        tfPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPesquisarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome");

        btnSairConsultar.setText("Sair");
        btnSairConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairConsultarMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setText("Clique 2x na linha para alterar o item");

        javax.swing.GroupLayout pnConsultarLayout = new javax.swing.GroupLayout(pnConsultar);
        pnConsultar.setLayout(pnConsultarLayout);
        pnConsultarLayout.setHorizontalGroup(
            pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addGroup(pnConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConsultarLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSairConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnConsultarLayout.createSequentialGroup()
                        .addGroup(pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(tfPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tblResetarTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPesquisar)))
                .addContainerGap())
        );
        pnConsultarLayout.setVerticalGroup(
            pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConsultarLayout.createSequentialGroup()
                .addGroup(pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnConsultarLayout.createSequentialGroup()
                        .addGap(0, 68, Short.MAX_VALUE)
                        .addComponent(btnPesquisar)
                        .addGap(48, 48, 48))
                    .addGroup(pnConsultarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tblResetarTabela)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSairConsultar)
                    .addComponent(jLabel13))
                .addContainerGap())
        );

        tbJanela.addTab("Consultar", pnConsultar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbJanela)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbJanela)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarProdutoMouseClicked
        Produto produto = new Produto();
        produtoDAO = new ProdutoDAO();

        Marca marcaSelecionada = buscarMarcaSelecionadaNoCb(cbCadastrarMarca);
        UnidadeDeMedida unidadeDeMedidaSelecionada = buscarUnidadeDeMedidaSelecionadaNoCb(cbCadastrarUnidade);
        float valorProduto = Float.parseFloat(tfCadastrarValorUnitario.getText().isBlank() ? "0" : tfCadastrarValorUnitario.getText());

        produto.setDescricaoProduto(tfCadastrarNome.getText());
        produto.setSituacao(String.valueOf(cbCadastrarSituacao.getSelectedItem()));
        produto.setValorUnitario(valorProduto);
        produto.setMarca(marcaSelecionada);
        produto.setUnidadeDeMedida(unidadeDeMedidaSelecionada);

        if (produtoDAO.cadastrarProduto(produto)) {
            limparCampos(tfCadastrarNome, tfCadastrarValorUnitario);
        }
    }//GEN-LAST:event_btnCadastrarProdutoMouseClicked

    private void btnAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMouseClicked
        Produto produtoAntigo = buscarProdutoPorId(tfAlterarCodigo.getText());
        if (produtoAntigo == null) {
            JOptionPane.showMessageDialog(null, "Busque um produto para atualizar", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Marca marca = buscarMarcaSelecionadaNoCb(cbAlterarMarca);
        UnidadeDeMedida unidadeDeMedida = buscarUnidadeDeMedidaSelecionadaNoCb(cbAlterarUnidade);
        
        String nomeProduto = tfAlterarNome.getText();
        float valorDoProduto = Float.parseFloat(tfAlterarValorUnitario.getText().isBlank() ? "0" : tfAlterarValorUnitario.getText());
        String situacao = String.valueOf(cbAlterarSituacao.getSelectedItem());
        
        Produto produto = new Produto(produtoAntigo.getIdProduto(), nomeProduto, situacao, valorDoProduto, marca, unidadeDeMedida);
        
        if (produtoDAO.atualizarProduto(produto)) {
            limparCampos(tfAlterarCodigo, tfAlterarNome, tfAlterarValorUnitario);
        }
    }//GEN-LAST:event_btnAlterarMouseClicked

    private void tblResetarTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResetarTabelaMouseClicked
        carregarTabelaComListaDeProdutos();
        tfPesquisar.setText("");
    }//GEN-LAST:event_tblResetarTabelaMouseClicked

    private void btnPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesquisarMouseClicked
        String pesquisa = tfPesquisar.getText();

        if (pesquisa.isBlank()) {
            JOptionPane.showMessageDialog(null, "Informe o nome de um produto para pesquisar!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        filtrarProduto(pesquisa);
        carregarTabelaComListaDeProdutosFiltrados();
    }//GEN-LAST:event_btnPesquisarMouseClicked

    private void tfPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPesquisarActionPerformed

    private void btnSairConsultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairConsultarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnSairConsultarMouseClicked

    private void tfAlterarCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAlterarCodigoFocusLost
        Produto produtoEncontrado = buscarProdutoPorId(tfAlterarCodigo.getText());
        carregarProduto(produtoEncontrado);
    }//GEN-LAST:event_tfAlterarCodigoFocusLost

    private void tbJanelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbJanelaMouseClicked
        if (tbJanela.getSelectedIndex() == 2) {
            if (!tabelaDeProdutoJaFoiCarregada) {
                carregarTabelaComListaDeProdutos();
                tabelaDeProdutoJaFoiCarregada = true;
            }
        } else {
            tabelaDeProdutoJaFoiCarregada = false;
        }
    }//GEN-LAST:event_tbJanelaMouseClicked

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void tblProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutosMouseClicked
        if (evt.getClickCount() == 2) {
            int linhaSelecionada = tblProdutos.getSelectedRow();
            int idProduto = (int) tblProdutos.getValueAt(linhaSelecionada, 0);
            
            Produto produtoSelecionado = buscarProdutoPorId(String.valueOf(idProduto));
            tfAlterarCodigo.setText(String.valueOf(idProduto));
            carregarProduto(produtoSelecionado);
            tbJanela.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblProdutosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCadastrarProduto;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSairConsultar;
    private javax.swing.JComboBox<String> cbAlterarMarca;
    private javax.swing.JComboBox<String> cbAlterarSituacao;
    private javax.swing.JComboBox<String> cbAlterarUnidade;
    private javax.swing.JComboBox<String> cbCadastrarMarca;
    private javax.swing.JComboBox<String> cbCadastrarSituacao;
    private javax.swing.JComboBox<String> cbCadastrarUnidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnAlterar;
    private javax.swing.JPanel pnCadastrar;
    private javax.swing.JPanel pnConsultar;
    private javax.swing.JTabbedPane tbJanela;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JButton tblResetarTabela;
    private javax.swing.JTextField tfAlterarCodigo;
    private javax.swing.JTextField tfAlterarNome;
    private javax.swing.JTextField tfAlterarValorUnitario;
    private javax.swing.JTextField tfCadastrarNome;
    private javax.swing.JTextField tfCadastrarValorUnitario;
    private javax.swing.JTextField tfPesquisar;
    // End of variables declaration//GEN-END:variables
}
