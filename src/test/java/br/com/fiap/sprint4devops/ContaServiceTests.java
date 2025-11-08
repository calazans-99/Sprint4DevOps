package br.com.fiap.sprint4devops;

import br.com.fiap.sprint4devops.entity.Conta;
import br.com.fiap.sprint4devops.repository.ContaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ContaServiceTests {

    @Autowired
    private ContaRepository repo;

    @Test
    void deveCriarEConsultarConta() {
        Conta c = new Conta();
        c.setNomeTitular("Ana");
        c.setEmail("ana@example.com");
        Conta salvo = repo.save(c);
        assertThat(salvo.getIdConta()).isNotNull();
        assertThat(repo.findByEmail("ana@example.com")).isPresent();
    }
}
