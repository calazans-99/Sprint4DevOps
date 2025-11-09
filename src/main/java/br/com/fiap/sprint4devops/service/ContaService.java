package br.com.fiap.sprint4devops.service;

import br.com.fiap.sprint4devops.model.Conta;
import br.com.fiap.sprint4devops.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta criar(Conta conta) {
        return contaRepository.save(conta);
    }

    public List<Conta> listar() {
        return contaRepository.findAll();
    }

    public Conta buscarPorId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public void excluir(Long id) {
        contaRepository.deleteById(id);
    }
}
