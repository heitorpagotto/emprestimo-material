package br.edu.unifaj.emprestimo.services;

import br.edu.unifaj.emprestimo.dao.AlunoDao;
import br.edu.unifaj.emprestimo.models.Aluno;

import java.util.List;
import java.util.Scanner;

public class AlunoService {
    private AlunoDao _dao;
    private Scanner _sc;
    public AlunoService(Scanner sc) {
        this._dao = new AlunoDao();
        _sc = sc;
    }

    public void listStudents() {
        System.out.printf("%5s%20s%15s%n", "ID", "NOME", "RA");

        List<Aluno> listStudents = _dao.listAll();

        if (listStudents.size() == 0)
            System.out.println("Não existem alunos cadastrados.\n");

        for (Aluno aluno : listStudents) {
            System.out.printf("%5s%20s%15s%n", aluno.getId(), aluno.getNome(), aluno.getRa());
        }
    }
    public void showStudent() {
        System.out.println("Insira o Id do aluno: ");
        int studentId = _sc.nextInt();
        _sc.nextLine();
        Aluno aluno = _dao.getById(studentId);

        System.out.println("ID\tNOME\tRA");
        System.out.println(aluno.getId() + "\t" + aluno.getNome() + "\t" + aluno.getRa());
    }

    public void insertStudent() {
        System.out.println("Insira o nome do aluno: ");
        String nome = _sc.nextLine();
        System.out.println("Insira o RA do aluno: ");
        String ra = _sc.nextLine();

        int newId = _dao.listAll().size() + 1;

        Aluno student = new Aluno(newId, nome, ra);

        _dao.insert(student);
    }

    public void updateStudent() {
        System.out.println("Insira o Id do aluno pra atualizar: ");
        int studentId = _sc.nextInt();
        _sc.nextLine();

        Aluno student = _dao.getById(studentId);

        System.out.println("Insira o novo nome do aluno: ");
        student.setNome(_sc.nextLine());

        System.out.println("\n");
        System.out.println("Insira o novo RA do aluno: ");
        student.setRa(_sc.nextLine());

        _dao.update(student);
    }

    public void removeStudent() {
        System.out.println("Insira o Id do aluno para remover: ");
        int studentId = _sc.nextInt();
        _sc.nextLine();

        _dao.delete(studentId);
        System.out.println("Aluno deletado com sucesso!\n");
    }

    public Aluno getStudent(int id) {
        return _dao.getById(id);
    }

    public Aluno getStudentByRA(String ra) {
        List<Aluno> alunos = _dao.listAll();

        List<Aluno> filteredList = alunos.stream().filter(a -> a.getRa().contains(ra)).toList();

        if (filteredList.size() == 0) {
            return null;
        }

        return filteredList.get(0);
    }
}
