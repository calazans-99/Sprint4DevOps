package br.com.fiap.sprint4devops.repository;

import br.com.fiap.sprint4devops.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
