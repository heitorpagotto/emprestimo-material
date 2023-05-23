package br.edu.unifaj.emprestimo.models;

import java.util.Date;

public class Emprestimo {
    private int id;
    private int id_produto;
    private int id_aluno;
    private Date data;
    private int quantidade;

    public Emprestimo(int id,int id_produto, int id_aluno, Date data, int quantidade) {
        this.id = id;
        this.id_produto = id_produto;
        this.id_aluno = id_aluno;
        this.data = data;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduto() {
        return id_produto;
    }

    public void setIdProduto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getIdAluno() {
        return id_aluno;
    }

    public void setIdAluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
