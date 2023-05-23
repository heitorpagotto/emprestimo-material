package br.edu.unifaj.emprestimo.dao;

import br.edu.unifaj.emprestimo.models.Aluno;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AlunoDao {
    public Map<Integer, Aluno> alunos;

    public AlunoDao() {
        alunos = new TreeMap<>();
    }

    public List<Aluno> listAll() {
        List<Aluno> listAlunos = new ArrayList<>(alunos.values());
        return listAlunos;
    }

    public Aluno getById(int id) {
        return alunos.get(id);
    }

    public int insert(Aluno aluno) {
        alunos.put(aluno.getId(), aluno);
        return aluno.getId();
    }

    public int update(Aluno aluno) {
        alunos.put(aluno.getId(), aluno);
        return aluno.getId();
    }

    public int delete(int id) {
        alunos.remove(id);
        return id;
    }
}
