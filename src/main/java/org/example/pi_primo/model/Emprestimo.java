package org.example.pi_primo.model;

import java.sql.Date;

public class Emprestimo {

    private java.sql.Date data_emprestimo;
    private java.sql.Date data_devolucao;

    private int id;
    private int id_cliente_fornecedor;
    private int id_produto;

    private String nomeFornecedor;
    private String nomeProduto;
    private String situacao;

    public Emprestimo(Date data_emprestimo,
                      Date data_devolucao,
                      int id,
                      int id_cliente_fornecedor,
                      int id_produto,
                      String nomeFornecedor,
                      String nomeProduto,
                      String situacao) {
        this.data_emprestimo = data_emprestimo;
        this.data_devolucao = data_devolucao;
        this.id = id;
        this.id_cliente_fornecedor = id_cliente_fornecedor;
        this.id_produto = id_produto;
        this.nomeFornecedor = nomeFornecedor;
        this.nomeProduto = nomeProduto;
        this.situacao = situacao;
    }

    public Date getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(Date data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente_fornecedor() {
        return id_cliente_fornecedor;
    }

    public void setId_cliente_fornecedor(int id_cliente_fornecedor) {
        this.id_cliente_fornecedor = id_cliente_fornecedor;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }


    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
