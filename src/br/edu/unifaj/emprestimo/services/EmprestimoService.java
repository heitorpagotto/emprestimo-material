package br.edu.unifaj.emprestimo.services;

import br.edu.unifaj.emprestimo.dao.EmprestimoDao;
import br.edu.unifaj.emprestimo.models.Aluno;
import br.edu.unifaj.emprestimo.models.Emprestimo;
import br.edu.unifaj.emprestimo.models.Produto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmprestimoService {
    private final Scanner _sc;
    private final EmprestimoDao _dao;
    private final AlunoService _alunoService;
    private final ProdutoService _produtoService;

    public EmprestimoService(Scanner sc, AlunoService alunoService, ProdutoService produtoService) {
        this._sc = sc;
        _dao = new EmprestimoDao();
        _alunoService = alunoService;
        _produtoService = produtoService;
    }

    public void listLoans() {
        List<Emprestimo> listLoans = _dao.listAll();

        if (listLoans.size() == 0)
            System.out.println("Não existem empréstimos cadastrados.\n");

        for (Emprestimo emprestimo : listLoans) {
            Aluno al = _alunoService.getStudent(emprestimo.getIdAluno());
            Produto pr = _produtoService.getProduct(emprestimo.getIdProduto());

            Date date = emprestimo.getData();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            String strDate = dateFormat.format(date);

            System.out.printf("%5s%20s%20s%20s%15s%n", "ID", "Produto", "Aluno", "Data", "Qtd.");
            System.out.printf("%5s%20s%20s%20s%15s%n", emprestimo.getId(), pr.getNome(), al.getNome(), strDate, emprestimo.getQuantidade());
        }
    }

    public void createLoan() {
        System.out.println("Digite o nome do produto: ");
        String nomeProduto = _sc.nextLine();

        Produto prod = _produtoService.getProductByName(nomeProduto);

        if (prod == null) {
            System.out.println("Produto inválido.");
            return;
        }

        System.out.println("Digite o RA do aluno: ");
        String ra = _sc.nextLine();

        Aluno al = _alunoService.getStudentByRA(ra);

        if (al == null) {
            System.out.println("Aluno inválido.");
            return;
        }

        System.out.println("Digite a quantidade de itens: ");

        int quantidade = _sc.nextInt();
        _sc.nextLine();

        if (quantidade > prod.getQuantidade() || quantidade <= 0) {
            System.out.println("Quantidade inválida");
            return;
        }
        int newId = _dao.listAll().size() + 1;

        Emprestimo loan = new Emprestimo(newId, prod.getId(), al.getId(), new Date(), quantidade);
        _dao.insert(loan);

        prod.setQuantidade(prod.getQuantidade() - quantidade);

        _produtoService.update(prod);
    }

    public void editLoan() {
        System.out.println("Digite o Id do empréstimo: ");
        int loanId = _sc.nextInt();
        _sc.nextLine();

        Emprestimo loan = _dao.getById(loanId);

        System.out.println("Digite o nome do novo produto: ");
        String nomeProduto = _sc.nextLine();

        Produto prod = _produtoService.getProductByName(nomeProduto);

        if (prod == null) {
            System.out.println("Produto inválido.");
            return;
        }

        System.out.println("Digite o RA do novo aluno: ");
        String ra = _sc.nextLine();

        Aluno al = _alunoService.getStudentByRA(ra);

        if (al == null) {
            System.out.println("Aluno inválido.");
            return;
        }

        System.out.println("Digite a nova quantidade de itens: ");

        int quantidade = _sc.nextInt();
        _sc.nextLine();

        if ((loan.getQuantidade() + prod.getQuantidade()) < quantidade || quantidade <= 0) {
            System.out.println("Quantidade inválida");
            return;
        }

        int restoreQty = loan.getQuantidade() - quantidade;
        prod.setQuantidade(prod.getQuantidade() + (restoreQty));

        loan.setQuantidade(quantidade);
        loan.setIdAluno(al.getId());
        loan.setIdProduto(prod.getId());
        loan.setData(new Date());

        _dao.update(loan);
        _produtoService.update(prod);
    }

    public void removeLoan() {
        System.out.println("Digite o Id do empréstimo: ");
        int loanId = _sc.nextInt();
        _sc.nextLine();

        Emprestimo loan = _dao.getById(loanId);

        Produto prod = _produtoService.getProduct(loan.getIdProduto());

        prod.setQuantidade(prod.getQuantidade() + loan.getQuantidade());

        _produtoService.update(prod);

        _dao.delete(loanId);
    }
}
