package br.edu.unifaj.emprestimo.dao;

import br.edu.unifaj.emprestimo.models.Emprestimo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EmprestimoDao {
    private Map<Integer, Emprestimo> emprestimos;

    public EmprestimoDao() {
        this.emprestimos = new TreeMap<>();
    }

    public List<Emprestimo> listAll() {
        List<Emprestimo> list = new ArrayList<>(emprestimos.values());
        return list;
    }
    public Emprestimo getById(int id) {
        return emprestimos.get(id);
    }
    public int insert(Emprestimo emprestimo) {
        emprestimos.put(emprestimo.getId(), emprestimo);
        return emprestimo.getId();
    }
    public int update(Emprestimo emprestimo){
        emprestimos.put(emprestimo.getId(), emprestimo);
        return emprestimo.getId();
    }
    public int delete(int id) {
        emprestimos.remove(id);
        return id;
    }
}
