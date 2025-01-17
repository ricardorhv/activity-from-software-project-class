/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.ricardo.view;

import com.ricardo.dao.UnidadeDeMedidaDAO;
import com.ricardo.model.UnidadeDeMedida;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ricardorhv
 */
public class IFUnidadeDeMedida extends javax.swing.JInternalFrame {
    UnidadeDeMedidaDAO unidadeDeMedidaDAO;
    ArrayList<UnidadeDeMedida> listaDeUnidadeDeMedida;
    ArrayList<UnidadeDeMedida> listaDeUnidadeDeMedidaFiltradas;
    boolean tabelaDeUnidadeDeMedidaJaFoiCarregada = false;
    
    public IFUnidadeDeMedida() {
        initComponents();
        unidadeDeMedidaDAO = new UnidadeDeMedidaDAO();
        carregarTabelaComListaDeUnidadeDeMedida();
    }
    
    public void atualizarListaDeUnidadeDeMedida() {
        this.listaDeUnidadeDeMedida = unidadeDeMedidaDAO.buscarTodasUnidadesDeMedida();
    }
    
    public void filtrarUnidadeDeMedida(String pesquisa) {
        this.listaDeUnidadeDeMedidaFiltradas = new ArrayList<>();
        
        for (UnidadeDeMedida unidadeDeMedida : this.listaDeUnidadeDeMedida) {
            if (unidadeDeMedida.getDescricaoUnidadeDeMedida().toUpperCase().contains(pesquisa.toUpperCase().trim())) {
                this.listaDeUnidadeDeMedidaFiltradas.add(unidadeDeMedida);
            }
        }
    }
    
    public void carregarUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
        if (unidadeDeMedida != null) {
            tfAlterarNome.setText(unidadeDeMedida.getDescricaoUnidadeDeMedida());
        }
    }
    
    public UnidadeDeMedida buscarUnidadeDeMedidaPorId(String idUnidadeDeMedida) {
        UnidadeDeMedida unidadeDeMedida;
        
        if (!idUnidadeDeMedida.isBlank()) {
            try {
                unidadeDeMedida = unidadeDeMedidaDAO.buscarUnidadeDeMedidaPorCodigo(Integer.parseInt(idUnidadeDeMedida));
                if (unidadeDeMedida != null) {
                    return unidadeDeMedida;
                }
                
                JOptionPane.showMessageDialog(null, "Marca " + idUnidadeDeMedida + " não encontrada", "ERROR", JOptionPane.ERROR_MESSAGE);
                tfAlterarNome.setText("");
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Digite um valor númerico no código", "ERROR", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        
        return null;
    }
    
    public void carregarTabelaComListaDeUnidadeDeMedida() {
        String colunas[] = {"Id", "Nome"};
        atualizarListaDeUnidadeDeMedida();
        
        int quantidadeDeColunas = colunas.length;
        int quantidadeDelinhas = this.listaDeUnidadeDeMedida.size();
        
        Object[][] dadosDasMarcas = new Object[quantidadeDelinhas][quantidadeDeColunas];
        
        for (int linha = 0; linha < quantidadeDelinhas; linha++) {
            dadosDasMarcas[linha][0] = this.listaDeUnidadeDeMedida.get(linha).getIdUnidadeDeMedida();
            dadosDasMarcas[linha][1] = this.listaDeUnidadeDeMedida.get(linha).getDescricaoUnidadeDeMedida();
        }
        
        DefaultTableModel model = new DefaultTableModel(dadosDasMarcas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblUnidadeDeMedida.setModel(model);
    }
    
    public void carregarTabelaComListaDeUnidadeDeMedidaFiltrada() {
        String colunas[] = {"Id", "Nome"};
        atualizarListaDeUnidadeDeMedida();
        
        int quantidadeDeColunas = colunas.length;
        int quantidadeDelinhas = this.listaDeUnidadeDeMedidaFiltradas.size();
        
        Object[][] dadosDasMarcas = new Object[quantidadeDelinhas][quantidadeDeColunas];
        
        for (int linha = 0; linha < quantidadeDelinhas; linha++) {
            dadosDasMarcas[linha][0] = this.listaDeUnidadeDeMedidaFiltradas.get(linha).getIdUnidadeDeMedida();
            dadosDasMarcas[linha][1] = this.listaDeUnidadeDeMedidaFiltradas.get(linha).getDescricaoUnidadeDeMedida();
        }
        
        DefaultTableModel model = new DefaultTableModel(dadosDasMarcas, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblUnidadeDeMedida.setModel(model);
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
        btnCadastrarUnidadeDeMedida = new javax.swing.JButton();
        btnSairCadastro = new javax.swing.JButton();
        pnAlterar = new javax.swing.JPanel();
        tfAlterarNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnAlterar = new javax.swing.JButton();
        tfAlterarCodigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnSairAlterar = new javax.swing.JButton();
        pnConsultar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUnidadeDeMedida = new javax.swing.JTable();
        btnResetar = new javax.swing.JButton();
        tfPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnSairConsultar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JButton();

        setClosable(true);

        tbJanela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbJanelaMouseClicked(evt);
            }
        });

        jLabel2.setText("Nome");

        btnCadastrarUnidadeDeMedida.setText("Cadastrar");
        btnCadastrarUnidadeDeMedida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCadastrarUnidadeDeMedidaMouseClicked(evt);
            }
        });

        btnSairCadastro.setText("Sair");
        btnSairCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairCadastroMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnCadastrarLayout = new javax.swing.GroupLayout(pnCadastrar);
        pnCadastrar.setLayout(pnCadastrarLayout);
        pnCadastrarLayout.setHorizontalGroup(
            pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastrarLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(tfCadastrarDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCadastrarUnidadeDeMedida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSairCadastro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        pnCadastrarLayout.setVerticalGroup(
            pnCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastrarLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCadastrarDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141)
                .addComponent(btnCadastrarUnidadeDeMedida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSairCadastro)
                .addContainerGap(143, Short.MAX_VALUE))
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

        javax.swing.GroupLayout pnAlterarLayout = new javax.swing.GroupLayout(pnAlterar);
        pnAlterar.setLayout(pnAlterarLayout);
        pnAlterarLayout.setHorizontalGroup(
            pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnAlterarLayout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addGroup(pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tfAlterarNome, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(tfAlterarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSairAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(137, Short.MAX_VALUE))
        );

        tbJanela.addTab("Alterar", pnAlterar);

        tblUnidadeDeMedida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUnidadeDeMedida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUnidadeDeMedidaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUnidadeDeMedida);

        btnResetar.setText("Resetar");
        btnResetar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetarMouseClicked(evt);
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
        jLabel13.setName(""); // NOI18N

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPesquisarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnConsultarLayout = new javax.swing.GroupLayout(pnConsultar);
        pnConsultar.setLayout(pnConsultarLayout);
        pnConsultarLayout.setHorizontalGroup(
            pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConsultarLayout.createSequentialGroup()
                        .addGroup(pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnResetar)
                            .addComponent(jLabel1)
                            .addComponent(tfPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPesquisar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConsultarLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSairConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
        );
        pnConsultarLayout.setVerticalGroup(
            pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConsultarLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnResetar))
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSairConsultar)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbJanela.addTab("Consultar", pnConsultar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tbJanela, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbJanela)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarUnidadeDeMedidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarUnidadeDeMedidaMouseClicked
        UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();
        
        unidadeDeMedida.setDescricaoUnidadeDeMedida(tfCadastrarDescricao.getText());
        if (unidadeDeMedidaDAO.cadastrarUnidadeDeMedida(unidadeDeMedida)) {
            tfCadastrarDescricao.setText("");
        }
    }//GEN-LAST:event_btnCadastrarUnidadeDeMedidaMouseClicked

    private void tfAlterarCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAlterarCodigoFocusLost
        UnidadeDeMedida unidadeDeMedida = buscarUnidadeDeMedidaPorId(tfAlterarCodigo.getText());
        carregarUnidadeDeMedida(unidadeDeMedida);
    }//GEN-LAST:event_tfAlterarCodigoFocusLost

    private void btnSairCadastroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairCadastroMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnSairCadastroMouseClicked

    private void btnSairAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairAlterarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnSairAlterarMouseClicked

    private void btnAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMouseClicked
        UnidadeDeMedida unidadeDeMedidaAntiga = buscarUnidadeDeMedidaPorId(tfAlterarCodigo.getText());
        if (unidadeDeMedidaAntiga == null) {
            JOptionPane.showMessageDialog(null, "Busque uma unidade de medida para atualizar", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();
        unidadeDeMedida.setDescricaoUnidadeDeMedida(tfAlterarNome.getText());
        unidadeDeMedida.setIdUnidadeDeMedida(unidadeDeMedidaAntiga.getIdUnidadeDeMedida());
        
        if (unidadeDeMedidaDAO.atualizarUnidadeDeMedida(unidadeDeMedida)) {
            tfAlterarCodigo.setText("");
            tfAlterarNome.setText("");
        }
    }//GEN-LAST:event_btnAlterarMouseClicked

    private void tbJanelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbJanelaMouseClicked
        if (tbJanela.getSelectedIndex() == 2) {
            if (!tabelaDeUnidadeDeMedidaJaFoiCarregada) {
                carregarTabelaComListaDeUnidadeDeMedida();
                tabelaDeUnidadeDeMedidaJaFoiCarregada = true;
            }
        } else {
            tabelaDeUnidadeDeMedidaJaFoiCarregada = false;
        }
    }//GEN-LAST:event_tbJanelaMouseClicked

    private void btnPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesquisarMouseClicked
        String pesquisa = tfPesquisar.getText();

        if (pesquisa.isBlank()) {
            JOptionPane.showMessageDialog(null, "Informe o nome de uma unidade de medida para pesquisar!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        filtrarUnidadeDeMedida(pesquisa);
        carregarTabelaComListaDeUnidadeDeMedidaFiltrada();
    }//GEN-LAST:event_btnPesquisarMouseClicked

    private void btnSairConsultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairConsultarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnSairConsultarMouseClicked

    private void tfPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPesquisarActionPerformed

    private void btnResetarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetarMouseClicked
        carregarTabelaComListaDeUnidadeDeMedida();
        tfPesquisar.setText("");
    }//GEN-LAST:event_btnResetarMouseClicked

    private void tblUnidadeDeMedidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUnidadeDeMedidaMouseClicked
        if (evt.getClickCount() == 2) {
            int linhaSelecionada = tblUnidadeDeMedida.getSelectedRow();
            int idUnidadeDeMedida = (int) tblUnidadeDeMedida.getValueAt(linhaSelecionada, 0);
            
            UnidadeDeMedida unidadeDeMedidaSelecionada = buscarUnidadeDeMedidaPorId(String.valueOf(idUnidadeDeMedida));
            tfAlterarCodigo.setText(String.valueOf(idUnidadeDeMedida));
            carregarUnidadeDeMedida(unidadeDeMedidaSelecionada);
            tbJanela.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblUnidadeDeMedidaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCadastrarUnidadeDeMedida;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnResetar;
    private javax.swing.JButton btnSairAlterar;
    private javax.swing.JButton btnSairCadastro;
    private javax.swing.JButton btnSairConsultar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnAlterar;
    private javax.swing.JPanel pnCadastrar;
    private javax.swing.JPanel pnConsultar;
    private javax.swing.JTabbedPane tbJanela;
    private javax.swing.JTable tblUnidadeDeMedida;
    private javax.swing.JTextField tfAlterarCodigo;
    private javax.swing.JTextField tfAlterarNome;
    private javax.swing.JTextField tfCadastrarDescricao;
    private javax.swing.JTextField tfPesquisar;
    // End of variables declaration//GEN-END:variables
}
