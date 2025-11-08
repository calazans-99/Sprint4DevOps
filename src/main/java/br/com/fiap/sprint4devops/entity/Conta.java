package br.com.fiap.sprint4devops.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;

    @NotBlank
    private String nomeTitular;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private OffsetDateTime dtCriacao = OffsetDateTime.now();

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lancamento> lancamentos = new ArrayList<>();

    public Long getIdConta() { return idConta; }
    public void setIdConta(Long idConta) { this.idConta = idConta; }
    public String getNomeTitular() { return nomeTitular; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public OffsetDateTime getDtCriacao() { return dtCriacao; }
    public void setDtCriacao(OffsetDateTime dtCriacao) { this.dtCriacao = dtCriacao; }
    public List<Lancamento> getLancamentos() { return lancamentos; }
    public void setLancamentos(List<Lancamento> lancamentos) { this.lancamentos = lancamentos; }
}
