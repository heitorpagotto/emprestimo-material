package br.edu.unifaj.emprestimo.dao;

import br.edu.unifaj.emprestimo.models.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProdutoDao {
    private final Map<Integer, Produto> produtos;

    public ProdutoDao() {
        this.produtos = new TreeMap<>();
    }

    public List<Produto> listAll() {
        List<Produto> list = new ArrayList<>(produtos.values());
        return list;
    }

    public Produto getById(int id) {
        return produtos.get(id);
    }

    public int insert(Produto produto) {
        produtos.put(produto.getId(), produto);
        return produto.getId();
    }

    public int update(Produto produto) {
        produtos.put(produto.getId(), produto);
        return produto.getId();
    }

    public int delete(int id) {
        produtos.remove(id);
        return id;
    }
}
