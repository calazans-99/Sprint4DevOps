package br.com.fiap.sprint4devops.controller;

import br.com.fiap.sprint4devops.entity.Conta;
import br.com.fiap.sprint4devops.entity.Lancamento;
import br.com.fiap.sprint4devops.repository.ContaRepository;
import br.com.fiap.sprint4devops.repository.LancamentoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LancamentoController {

    private final LancamentoRepository lancRepo;
    private final ContaRepository contaRepo;

    public LancamentoController(LancamentoRepository lancRepo, ContaRepository contaRepo) {
        this.lancRepo = lancRepo;
        this.contaRepo = contaRepo;
    }

    @GetMapping("/lancamentos")
    public ResponseEntity<List<Lancamento>> listar() {
        return ResponseEntity.ok(lancRepo.findAll());
    }

    @GetMapping("/lancamentos/conta/{idConta}")
    public ResponseEntity<List<Lancamento>> listarPorConta(@PathVariable Long idConta) {
        Conta conta = contaRepo.findById(idConta).orElseThrow();
        return ResponseEntity.ok(lancRepo.findByConta(conta));
    }

    @PostMapping("/contas/{idConta}/lancamentos")
    public ResponseEntity<Lancamento> criar(@PathVariable Long idConta, @Valid @RequestBody Lancamento lanc) {
        Conta conta = contaRepo.findById(idConta).orElseThrow();
        lanc.setConta(conta);
        Lancamento salvo = lancRepo.save(lanc);
        return ResponseEntity.created(URI.create("/api/lancamentos/" + salvo.getId())).body(salvo);
    }

    @GetMapping("/lancamentos/{id}")
    public ResponseEntity<Lancamento> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(lancRepo.findById(id).orElseThrow());
    }

    @PutMapping("/lancamentos/{id}")
    public ResponseEntity<Lancamento> atualizar(@PathVariable Long id, @Valid @RequestBody Lancamento body) {
        Lancamento l = lancRepo.findById(id).orElseThrow();
        l.setDescricao(body.getDescricao());
        l.setValor(body.getValor());
        if (body.getConta() != null && body.getConta().getId() != null) {
            Conta conta = contaRepo.findById(body.getConta().getId()).orElseThrow();
            l.setConta(conta);
        }
        return ResponseEntity.ok(lancRepo.save(l));
    }

    @DeleteMapping("/lancamentos/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (lancRepo.existsById(id)) {
            lancRepo.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }
}
