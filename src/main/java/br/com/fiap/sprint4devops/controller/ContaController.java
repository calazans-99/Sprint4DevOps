package br.com.fiap.sprint4devops.controller;

import br.com.fiap.sprint4devops.entity.Conta;
import br.com.fiap.sprint4devops.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Conta>> listar() {
        List<Conta> contas = service.listar();
        return ResponseEntity.ok(contas);
    }

    @PostMapping
    public ResponseEntity<Conta> criar(@Valid @RequestBody Conta conta) {
        Conta criada = service.criar(conta);
        return ResponseEntity.created(URI.create("/api/contas/" + criada.getId())).body(criada);
    }

    @GetMapping("{id}")
    public ResponseEntity<Conta> buscar(@PathVariable Long id) {
        Conta c = service.buscar(id);
        return ResponseEntity.ok(c);
    }

    @PutMapping("{id}")
    public ResponseEntity<Conta> atualizar(@PathVariable Long id, @Valid @RequestBody Conta conta) {
        Conta atualizada = service.atualizar(id, conta);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
