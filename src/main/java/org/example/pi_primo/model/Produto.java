package org.example.pi_primo.model;

public class Produto {

    private String nome;
    private String tipo;
    private String descricao;
    private int quantidadeDeEmprestimos;
    private double preco;
    private int id;
    private String situacao;

    public Produto(String nome, String tipo, String descricao, int quantidadeDeEmprestimos, double preco, int id, String situacao) {
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.quantidadeDeEmprestimos = quantidadeDeEmprestimos;
        this.preco = preco;
        this.id = id;
        this.situacao = situacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeDeEmprestimos() {
        return quantidadeDeEmprestimos;
    }

    public void setQuantidadeDeEmprestimos(int quantidadeDeEmprestimos) {
        this.quantidadeDeEmprestimos = quantidadeDeEmprestimos;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}

