package br.com.fiap.sprint4devops.controller;

import br.com.fiap.sprint4devops.entity.Conta;
import br.com.fiap.sprint4devops.entity.Lancamento;
import br.com.fiap.sprint4devops.repository.ContaRepository;
import br.com.fiap.sprint4devops.repository.LancamentoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LancamentoController {

    private final ContaRepository contaRepo;
    private final LancamentoRepository lancRepo;

    public LancamentoController(ContaRepository contaRepo, LancamentoRepository lancRepo) {
        this.contaRepo = contaRepo;
        this.lancRepo = lancRepo;
    }

    @PostMapping("/contas/{idConta}/lancamentos")
    public ResponseEntity<Lancamento> criar(@PathVariable Long idConta, @Valid @RequestBody Lancamento lanc) {
        Conta conta = contaRepo.findById(idConta).orElseThrow();
        lanc.setConta(conta);
        Lancamento salvo = lancRepo.save(lanc);
        return ResponseEntity.created(URI.create("/api/lancamentos/" + salvo.getIdLcto())).body(salvo);
    }

    @GetMapping("/contas/{idConta}/lancamentos")
    public List<Lancamento> listarPorConta(@PathVariable Long idConta) {
        Conta conta = contaRepo.findById(idConta).orElseThrow();
        return lancRepo.findByConta(conta);
    }

    @PutMapping("/lancamentos/{id}")
    public Lancamento atualizar(@PathVariable Long id, @Valid @RequestBody Lancamento body) {
        Lancamento l = lancRepo.findById(id).orElseThrow();
        l.setTipo(body.getTipo());
        l.setValor(body.getValor());
        l.setDescricao(body.getDescricao());
        return lancRepo.save(l);
    }

    @DeleteMapping("/lancamentos/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        lancRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
