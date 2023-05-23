package br.edu.unifaj.emprestimo.menus;

import br.edu.unifaj.emprestimo.services.AlunoService;
import br.edu.unifaj.emprestimo.services.EmprestimoService;
import br.edu.unifaj.emprestimo.services.ProdutoService;

import java.util.Scanner;

public class FirstMenu {
    private final Scanner _sc;
    private final ProdutoService _produtoService;
    private final AlunoService _alunoService;
    private final EmprestimoService _emprestimoService;

    public FirstMenu(Scanner sc) {
        this._sc = sc;
        _produtoService = new ProdutoService(_sc);
        _alunoService = new AlunoService(_sc);
        _emprestimoService = new EmprestimoService(_sc, _alunoService, _produtoService);
    }

    public int generateMainMenu() {
        System.out.println("Sistema de cadastro e empréstimo de produtos\n");

        System.out.println("1-Produtos");
        System.out.println("2-Alunos");
        System.out.println("3-Empréstimos");
        System.out.println("4-Sair\n");
        System.out.println("Selecione uma opção: ");
        int option = _sc.nextInt();
        _sc.nextLine();

        switch (option) {
            case 1:
                _printProductMenu();
                break;
            case 2:
                _printStudentMenu();
                break;
            case 3:
                _printLoanMenu();
                break;
            case 4:
                return 0;
            default:
                System.out.println("Opção inválida.");
                generateMainMenu();
        }

        return 0;
    }

    private void _printProductMenu() {
        System.out.println("Menu de produtos:\n");
        System.out.println("1- Listar Produtos");
        System.out.println("2- Incluir Produto");
        System.out.println("3- Editar Produto");
        System.out.println("4- Excluir Produto");
        System.out.println("5- Voltar\n");

        System.out.println("Selecione uma opção: ");
        int option = _sc.nextInt();
        _sc.nextLine();

        switch (option) {
            case 1:
                _produtoService.listProducts();
                _printProductMenu();
                break;
            case 2:
                _produtoService.insertProduct();
                _printProductMenu();
                break;
            case 3:
                _produtoService.updateProduct();
                _printProductMenu();
                break;
            case 4:
                _produtoService.removeProduct();
                _printProductMenu();
            default:
                generateMainMenu();
        }
    }

    private void _printStudentMenu() {
        System.out.println("Menu de alunos:\n");
        System.out.println("1- Listar Alunos");
        System.out.println("2- Incluir Alunos");
        System.out.println("3- Editar Alunos");
        System.out.println("4- Excluir Alunos");
        System.out.println("5- Voltar\n");

        System.out.println("Selecione uma opção: ");
        int option = _sc.nextInt();
        _sc.nextLine();

        switch (option) {
            case 1:
                _alunoService.listStudents();
                _printStudentMenu();
                break;
            case 2:
                _alunoService.insertStudent();
                _printStudentMenu();
                break;
            case 3:
                _alunoService.updateStudent();
                _printStudentMenu();
                break;
            case 4:
                _alunoService.removeStudent();
                _printStudentMenu();
            default:
                generateMainMenu();
        }
    }

    private void _printLoanMenu() {
        System.out.println("Menu de empréstimos:\n");
        System.out.println("1- Listar Empréstimos");
        System.out.println("2- Realizar Empréstimo");
        System.out.println("3- Editar Empréstimo");
        System.out.println("4- Excluir Empréstimo");
        System.out.println("5- Voltar\n");

        System.out.println("Selecione uma opção: ");
        int option = _sc.nextInt();
        _sc.nextLine();

        switch (option) {
            case 1:
                _emprestimoService.listLoans();
                _printLoanMenu();
                break;
            case 2:
                _emprestimoService.createLoan();
                _printLoanMenu();
                break;
            case 3:
                _emprestimoService.editLoan();
                _printLoanMenu();
                break;
            case 4:
                _emprestimoService.removeLoan();
                _printLoanMenu();
                break;
            default:
                generateMainMenu();
        }
    }
}
