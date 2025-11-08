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

    @PostMapping
    public ResponseEntity<Conta> criar(@Valid @RequestBody Conta conta) {
        Conta salvo = service.criar(conta);
        return ResponseEntity.created(URI.create("/api/contas/" + salvo.getIdConta())).body(salvo);
    }

    @GetMapping
    public List<Conta> listar() { return service.listar(); }

    @GetMapping("{id}")
    public Conta buscar(@PathVariable Long id) { return service.buscar(id); }

    @PutMapping("{id}")
    public Conta atualizar(@PathVariable Long id, @Valid @RequestBody Conta conta) {
        return service.atualizar(id, conta);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
