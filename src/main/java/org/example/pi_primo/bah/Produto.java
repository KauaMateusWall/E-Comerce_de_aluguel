package org.example.pi_primo.bah;

public class Produto {

        private String nome;
        private String tipo;
        private String descricao;
        private double preco;
        private int id;

        public Produto(String nome, String tipo, double preco, int id, String descricao) {
            this.nome = nome;
            this.tipo = tipo;
            this.preco = preco;
            this.id = id;
            this.descricao=descricao;
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

    public String getDescricao() {
            return this.descricao;
    }
}


