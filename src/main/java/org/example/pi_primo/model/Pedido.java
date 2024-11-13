package org.example.pi_primo.model;

import java.sql.Date;

public class Pedido {
    private java.sql.Date data;
    private Cliente origem;
    private Produto produto;
    private String estado;

    public Pedido(Date data, Cliente origem, Produto produto, String estado) {
        this.data = data;
        this.origem = origem;
        this.produto = produto;
        this.estado = estado;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getOrigem() {
        return origem;
    }

    public void setOrigem(Cliente origem) {
        this.origem = origem;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
