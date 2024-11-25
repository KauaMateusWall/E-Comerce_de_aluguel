package org.example.pi_primo.model;

public class Produto {

    private int id;
    private String nome;
    private String tipo;
    private String descricao;
    private int quantidadeDeEmprestimos;
    private double preco;
    private String situacao;
    private String Proprietario;
    private int idProprietario;

    public Produto(int id, String nome, String tipo, String descricao, int quantidadeDeEmprestimos, double preco, String situacao, String proprietario,int idProprietario) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.quantidadeDeEmprestimos = quantidadeDeEmprestimos;
        this.preco = preco;
        this.situacao = situacao;
        Proprietario = proprietario;
        this.idProprietario=idProprietario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getProprietario() {
        return Proprietario;
    }

    public void setProprietario(String proprietario) {
        Proprietario = proprietario;
    }

    public int getidProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(int idProprietario) {
        this.idProprietario = idProprietario;
    }

}