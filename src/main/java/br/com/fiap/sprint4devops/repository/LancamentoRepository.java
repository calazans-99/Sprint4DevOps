package br.com.fiap.sprint4devops.repository;

import br.com.fiap.sprint4devops.entity.Conta;
import br.com.fiap.sprint4devops.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    List<Lancamento> findByConta(Conta conta);
}
