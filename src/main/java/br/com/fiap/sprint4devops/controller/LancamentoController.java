package br.com.fiap.sprint4devops.controller;

import br.com.fiap.sprint4devops.model.Lancamento;
import br.com.fiap.sprint4devops.repository.LancamentoRepository;
import br.com.fiap.sprint4devops.repository.ContaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

    private final LancamentoRepository lancamentoRepository;
    private final ContaRepository contaRepository;

    public LancamentoController(LancamentoRepository lancamentoRepository, ContaRepository contaRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.contaRepository = contaRepository;
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@RequestBody Lancamento lancamento) {
        if (lancamento.getConta() == null || lancamento.getConta().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        var conta = contaRepository.findById(lancamento.getConta().getId())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        lancamento.setConta(conta);
        return ResponseEntity.ok(lancamentoRepository.save(lancamento));
    }

    @GetMapping
    public ResponseEntity<List<Lancamento>> listar() {
        return ResponseEntity.ok(lancamentoRepository.findAll());
    }
}
