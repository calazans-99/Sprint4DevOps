package br.com.fiap.sprint4devops.service;

import br.com.fiap.sprint4devops.entity.Conta;
import br.com.fiap.sprint4devops.repository.ContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository repo;

    public ContaService(ContaRepository repo) {
        this.repo = repo;
    }

    public List<Conta> listar() { return repo.findAll(); }

    @Transactional
    public Conta criar(Conta c) { return repo.save(c); }

    public Conta buscar(Long id) { return repo.findById(id).orElseThrow(); }

    @Transactional
    public Conta atualizar(Long id, Conta dados) {
        Conta c = buscar(id);
        c.setNomeTitular(dados.getNomeTitular());
        c.setEmail(dados.getEmail());
        return repo.save(c);
    }

    @Transactional
    public void deletar(Long id) {
        if (repo.existsById(id)) repo.deleteById(id);
    }
}
