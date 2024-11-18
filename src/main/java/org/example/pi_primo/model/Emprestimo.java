package org.example.pi_primo.model;

public class Emprestimo {

    private java.sql.Date data_emprestimo;
    private java.sql.Date data_devolucao;

    private int id;
    private int id_cliente_fornecedor;
    private int id_cliente_receptor;
    private int id_produto;

    private String nomeCliente;
    private String nomeFornecedor;
    private String nomeProduto;
    private double preco;
    private String situacao;

}
