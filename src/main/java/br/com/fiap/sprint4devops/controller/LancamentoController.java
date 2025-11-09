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
        List<Lancamento> lista = lancRepo.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/lancamentos/conta/{idConta}")
    public ResponseEntity<List<Lancamento>> listarPorConta(@PathVariable Long idConta) {
        Conta conta = contaRepo.findById(idConta).orElseThrow();
        List<Lancamento> lista = lancRepo.findByConta(conta);
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/lancamentos")
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento body) {
        // Se vier apenas id da conta dentro do body, garante entidade gerenciada
        if (body.getConta() != null && body.getConta().getId() != null) {
            Conta conta = contaRepo.findById(body.getConta().getId()).orElseThrow();
            body.setConta(conta);
        }
        Lancamento salvo = lancRepo.save(body);
        return ResponseEntity
                .created(URI.create("/api/lancamentos/" + salvo.getId()))
                .body(salvo);
    }

    @GetMapping("/lancamentos/{id}")
    public ResponseEntity<Lancamento> buscar(@PathVariable Long id) {
        Lancamento l = lancRepo.findById(id).orElseThrow();
        return ResponseEntity.ok(l);
    }

    @PutMapping("/lancamentos/{id}")
    public ResponseEntity<Lancamento> atualizar(@PathVariable Long id, @Valid @RequestBody Lancamento body) {
        Lancamento l = lancRepo.findById(id).orElseThrow();
        // copie apenas os campos editáveis
        l.setTipo(body.getTipo());
        l.setValor(body.getValor());
        l.setDescricao(body.getDescricao());
        // se permitir troca de conta:
        if (body.getConta() != null && body.getConta().getId() != null) {
            Conta conta = contaRepo.findById(body.getConta().getId()).orElseThrow();
            l.setConta(conta);
        }
        Lancamento atualizado = lancRepo.save(l);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/lancamentos/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!lancRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        lancRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
