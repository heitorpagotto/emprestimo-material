package br.edu.unifaj.emprestimo.services;

import br.edu.unifaj.emprestimo.dao.ProdutoDao;
import br.edu.unifaj.emprestimo.models.Produto;

import java.util.List;
import java.util.Scanner;

public class ProdutoService {
    private final Scanner _sc;
    private final ProdutoDao _dao;

    public ProdutoService(Scanner sc) {
        this._sc = sc;
        this._dao = new ProdutoDao();
    }

    public void listProducts() {
        System.out.printf("%5s%20s%15s%n", "ID", "NOME", "QUANTIDADE");

        List<Produto> listProducts = _dao.listAll();

        if (listProducts.size() == 0)
            System.out.println("Não existem produtos cadastrados.\n");

        for (Produto produto : listProducts) {
            System.out.printf("%5s%20s%15s%n", produto.getId(), produto.getNome(), produto.getQuantidade());
        }
    }

    public void showProduct() {
        System.out.println("Insira o Id do produto: ");
        int productId = _sc.nextInt();
        _sc.nextLine();
        Produto produto = _dao.getById(productId);

        System.out.println("ID\tNOME\tQUANTIDADE");
        System.out.println(produto.getId() + "\t" + produto.getNome() + "\t" + produto.getQuantidade());
    }

    public void insertProduct() {
        System.out.println("Insira o nome do produto: ");
        String nome = _sc.nextLine();
        System.out.println("\n");
        System.out.println("Insira a quantidade do produto: ");
        int quantidade = _sc.nextInt();
        _sc.nextLine();

        int newId = _dao.listAll().size() + 1;

        Produto product = new Produto(newId, nome, quantidade);

        _dao.insert(product);
    }

    public void updateProduct() {
        System.out.println("Insira o Id do produto pra atualizar: ");
        int productId = _sc.nextInt();
        _sc.nextLine();

        Produto getProduct = _dao.getById(productId);

        System.out.println("Insira o novo nome do produto: ");
        getProduct.setNome(_sc.nextLine());

        System.out.println("\n");
        System.out.println("Insira a nova quantidade do produto: ");
        int quantidade = _sc.nextInt();
        _sc.nextLine();
        getProduct.setQuantidade(quantidade);

        _dao.update(getProduct);
    }

    public void removeProduct() {
        System.out.println("Insira o Id do produto pra remover: ");
        int productId = _sc.nextInt();
        _sc.nextLine();

        _dao.delete(productId);
        System.out.println("Produto deletado com sucesso!\n");
    }

    public Produto getProduct(int id) {
        return _dao.getById(id);
    }

    public Produto getProductByName(String name) {
        List<Produto> produtos = _dao.listAll();

        List<Produto> filteredList = produtos.stream().filter(v -> v.getNome().contains(name)).toList();

        if (filteredList.size() == 0) {
            return null;
        }

        return filteredList.get(0);
    }

    public void update(Produto prod) {
        _dao.update(prod);
    }
}
