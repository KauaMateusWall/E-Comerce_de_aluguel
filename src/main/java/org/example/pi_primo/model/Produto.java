package org.example.pi_primo.model;

public class Produto {
    private int ID;
    private String nome;
    private String TIPO;
    private double preco;

    public Produto(int ID, String nome, String TIPO, double preco) {
        this.ID = ID;
        this.nome = nome;
        this.TIPO = TIPO;
        this.preco = preco;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
