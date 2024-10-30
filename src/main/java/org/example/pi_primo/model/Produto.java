package org.example.pi_primo.model;

public class Produto {

    private String nome;
    private String tipo;
    private String descricao;
    private int quantidadeDeEmprestimos;
    private double preco;
    private int id;

    public Produto(String nome, String tipo, String descricao, int quantidadeDeEmprestimos, double preco, int id) {
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.quantidadeDeEmprestimos = quantidadeDeEmprestimos;
        this.preco = preco;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidadeDeEmprestimos() {
        return quantidadeDeEmprestimos;
    }

    public double getPreco() {
        return preco;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuantidadeDeEmprestimos(int quantidadeDeEmprestimos) {
        this.quantidadeDeEmprestimos = quantidadeDeEmprestimos;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidadeDeEmprestimos=" + quantidadeDeEmprestimos +
                ", preco=" + preco +
                ", id=" + id +
                '}';
    }
}
