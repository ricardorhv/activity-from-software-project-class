/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.ricardo.view;

import com.ricardo.dao.MarcaDAO;
import com.ricardo.model.Marca;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ricardorhv
 */
public class IFMarca extends javax.swing.JInternalFrame {
    MarcaDAO marcaDAO;
    ArrayList<Marca> listaDeMarcas;
    ArrayList<Marca> listaDeMarcasFiltradas;
    boolean tabelaDeMarcaJaFoiCarregada = false;
    
    public IFMarca() {
        initComponents();
        marcaDAO = new MarcaDAO();
        carregarTabelaComListaDeMarcas();
    }
    
    public void atualizarListaDeMarcas() {
        this.listaDeMarcas = marcaDAO.buscarTodasMarcas();
    }
    
    public void filtrarMarca(String pesquisa) {
        this.listaDeMarcasFiltradas = new ArrayList<>();
        
        for (Marca marca : this.listaDeMarcas) {
            if (marca.getDescricaoMarca().toUpperCase().contains(pesquisa.toUpperCase().trim())) {
                this.listaDeMarcasFiltradas.add(marca);
            }
        }
    }
    
    public void carregarTabelaComListaDeMarcas() {
        String colunas[] = {"Id", "Nome"};
        atualizarListaDeMarcas();
        
        int quantidadeDeColunas = colunas.length;
        int quantidadeDelinhas = this.listaDeMarcas.size();
        
        Object[][] dadosDasMarcas = new Object[quantidadeDelinhas][quantidadeDeColunas];
        
        for (int linha = 0; linha < quantidadeDelinhas; linha++) {
            dadosDasMarcas[linha][0] = this.listaDeMarcas.get(linha).getIdMarca();
            dadosDasMarcas[linha][1] = this.listaDeMarcas.get(linha).getDescricaoMarca();
        }
        
        DefaultTableModel model = new DefaultTableModel(dadosDasMarcas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblMarcas.setModel(model);
    }
    
    public void carregarTabelaComListaDeMarcasFiltradas() {
        String colunas[] = {"Id", "Nome"};
        atualizarListaDeMarcas();
        
        int quantidadeDeColunas = colunas.length;
        int quantidadeDelinhas = this.listaDeMarcasFiltradas.size();
        
        Object[][] dadosDasMarcas = new Object[quantidadeDelinhas][quantidadeDeColunas];
        
        for (int linha = 0; linha < quantidadeDelinhas; linha++) {
            dadosDasMarcas[linha][0] = this.listaDeMarcasFiltradas.get(linha).getIdMarca();
            dadosDasMarcas[linha][1] = this.listaDeMarcasFiltradas.get(linha).getDescricaoMarca();
        }
        
        DefaultTableModel model = new DefaultTableModel(dadosDasMarcas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblMarcas.setModel(model);
    }
    
    public void carregarMarca(Marca marca) {
        if (marca != null) {
            tfAlterarNome.setText(marca.getDescricaoMarca());
        }
    }
    
    public Marca buscarMarcaPorId(String idMarca) {
        Marca marca;
        if (!idMarca.isBlank()) {
            try {
                marca = marcaDAO.buscarMarcaPorCodigo(Integer.parseInt(idMarca));
                if (marca != null) {
                    return marca;
                }
                
                JOptionPane.showMessageDialog(null, "Marca " + idMarca + " não encontrada", "ERROR", JOptionPane.ERROR_MESSAGE);
                tfAlterarNome.setText("");
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Digite um valor númerico no código", "ERROR", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        
        return null;
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
        tfCadastrarDescricao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnCadastrarMarca = new javax.swing.JButton();
        btnSairCadastro = new javax.swing.JButton();
        pnAlterar = new javax.swing.JPanel();
        tfAlterarNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnAlterar = new javax.swing.JButton();
        tfAlterarCodigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnSairAlterar = new javax.swing.JButton();
        pnConsultar = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMarcas = new javax.swing.JTable();
        tblResetarTabela = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        tfPesquisarMarca = new javax.swing.JTextField();
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

        btnCadastrarMarca.setText("Cadastrar");
        btnCadastrarMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCadastrarMarcaMouseClicked(evt);
            }
        });
        btnCadastrarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarMarcaActionPerformed(evt);
            }
        });

        btnSairCadastro.setText("Sair");
        btnSairCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairCadastroMouseClicked(evt);
            }
        });
        btnSairCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairCadastroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnCadastrarLayout = new javax.swing.GroupLayout(pnCadastrar);
        pnCadastrar.setLayout(pnCadastrarLayout);
        pnCadastrarLayout.setHorizontalGroup(
            pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastrarLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfCadastrarDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                    .addComponent(btnCadastrarMarca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSairCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        pnCadastrarLayout.setVerticalGroup(
            pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastrarLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCadastrarDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                .addComponent(btnCadastrarMarca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSairCadastro)
                .addGap(137, 137, 137))
        );

        tbJanela.addTab("Cadastrar", pnCadastrar);

        jLabel3.setText("Nome");

        btnAlterar.setText("Alterar");
        btnAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAlterarMouseClicked(evt);
            }
        });

        tfAlterarCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfAlterarCodigoFocusLost(evt);
            }
        });

        jLabel5.setText("Código");

        btnSairAlterar.setText("Sair");
        btnSairAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairAlterarMouseClicked(evt);
            }
        });
        btnSairAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnAlterarLayout = new javax.swing.GroupLayout(pnAlterar);
        pnAlterar.setLayout(pnAlterarLayout);
        pnAlterarLayout.setHorizontalGroup(
            pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnAlterarLayout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addGroup(pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSairAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfAlterarNome, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(tfAlterarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(96, 96, 96))
        );
        pnAlterarLayout.setVerticalGroup(
            pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAlterarLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAlterarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAlterarNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(btnAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSairAlterar)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        tbJanela.addTab("Alterar", pnAlterar);

        tblMarcas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMarcas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMarcasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMarcas);

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

        tfPesquisarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPesquisarMarcaActionPerformed(evt);
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
        jLabel13.setName(""); // NOI18N

        javax.swing.GroupLayout pnConsultarLayout = new javax.swing.GroupLayout(pnConsultar);
        pnConsultar.setLayout(pnConsultarLayout);
        pnConsultarLayout.setHorizontalGroup(
            pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConsultarLayout.createSequentialGroup()
                        .addGroup(pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(tfPesquisarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConsultarLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSairConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnConsultarLayout.createSequentialGroup()
                        .addComponent(tblResetarTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPesquisar)))
                .addContainerGap())
        );
        pnConsultarLayout.setVerticalGroup(
            pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPesquisarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tblResetarTabela)
                    .addComponent(btnPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSairConsultar)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbJanela.addTab("Consultar", pnConsultar);

        getContentPane().add(tbJanela, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfPesquisarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPesquisarMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPesquisarMarcaActionPerformed

    private void btnCadastrarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastrarMarcaActionPerformed

    private void btnCadastrarMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarMarcaMouseClicked
        Marca marca = new Marca();
        marca.setDescricaoMarca(tfCadastrarDescricao.getText());
        
        if (marcaDAO.cadastrarMarca(marca)) {
            tfCadastrarDescricao.setText("");
        }
    }//GEN-LAST:event_btnCadastrarMarcaMouseClicked

    private void tfAlterarCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAlterarCodigoFocusLost
        Marca marcaEncontrada = buscarMarcaPorId(tfAlterarCodigo.getText());
        carregarMarca(marcaEncontrada);
    }//GEN-LAST:event_tfAlterarCodigoFocusLost

    private void btnAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMouseClicked
        Marca marcaAntiga = buscarMarcaPorId(tfAlterarCodigo.getText());
        if (marcaAntiga == null) {
            JOptionPane.showMessageDialog(null, "Busque uma marca para atualizar", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Marca marca = new Marca();
        marca.setDescricaoMarca(tfAlterarNome.getText());
        marca.setIdMarca(marcaAntiga.getIdMarca());
        
        if (marcaDAO.atualizarMarca(marca)) {
            tfAlterarCodigo.setText("");
            tfAlterarNome.setText("");
        }
    }//GEN-LAST:event_btnAlterarMouseClicked

    private void tbJanelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbJanelaMouseClicked
        if (tbJanela.getSelectedIndex() == 2) {
            if (!tabelaDeMarcaJaFoiCarregada) {
                carregarTabelaComListaDeMarcas();
                tabelaDeMarcaJaFoiCarregada = true;
            }
        } else {
            tabelaDeMarcaJaFoiCarregada = false;
        }
    }//GEN-LAST:event_tbJanelaMouseClicked

    private void btnPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesquisarMouseClicked
        String pesquisa = tfPesquisarMarca.getText();
        
        if (pesquisa.isBlank()) {
            JOptionPane.showMessageDialog(null, "Informe o nome de uma marca para pesquisar!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        filtrarMarca(pesquisa);
        carregarTabelaComListaDeMarcasFiltradas();
    }//GEN-LAST:event_btnPesquisarMouseClicked

    private void tblResetarTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResetarTabelaMouseClicked
        carregarTabelaComListaDeMarcas();
        tfPesquisarMarca.setText("");
    }//GEN-LAST:event_tblResetarTabelaMouseClicked

    private void btnSairCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSairCadastroActionPerformed

    private void btnSairAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairAlterarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSairAlterarActionPerformed

    private void btnSairCadastroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairCadastroMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnSairCadastroMouseClicked

    private void btnSairAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairAlterarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnSairAlterarMouseClicked

    private void btnSairConsultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairConsultarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnSairConsultarMouseClicked

    private void tblMarcasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMarcasMouseClicked
        if (evt.getClickCount() == 2) {
            int linhaSelecionada = tblMarcas.getSelectedRow();
            int idMarca = (int) tblMarcas.getValueAt(linhaSelecionada, 0);
            
            Marca marcaSelecionada = buscarMarcaPorId(String.valueOf(idMarca));
            tfAlterarCodigo.setText(String.valueOf(idMarca));
            carregarMarca(marcaSelecionada);
            tbJanela.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblMarcasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCadastrarMarca;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSairAlterar;
    private javax.swing.JButton btnSairCadastro;
    private javax.swing.JButton btnSairConsultar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnAlterar;
    private javax.swing.JPanel pnCadastrar;
    private javax.swing.JPanel pnConsultar;
    private javax.swing.JTabbedPane tbJanela;
    private javax.swing.JTable tblMarcas;
    private javax.swing.JButton tblResetarTabela;
    private javax.swing.JTextField tfAlterarCodigo;
    private javax.swing.JTextField tfAlterarNome;
    private javax.swing.JTextField tfCadastrarDescricao;
    private javax.swing.JTextField tfPesquisarMarca;
    // End of variables declaration//GEN-END:variables
}