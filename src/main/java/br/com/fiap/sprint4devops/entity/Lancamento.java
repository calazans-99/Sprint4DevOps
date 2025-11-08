package br.com.fiap.sprint4devops.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Lancamento")
public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLcto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_conta", nullable = false)
    private Conta conta;

    @NotNull
    @Column(length = 1)
    private Character tipo; // 'C' crédito, 'D' débito

    @DecimalMin("0.01")
    private BigDecimal valor;

    private String descricao;

    private OffsetDateTime dtEvento = OffsetDateTime.now();

    public Long getIdLcto() { return idLcto; }
    public void setIdLcto(Long idLcto) { this.idLcto = idLcto; }
    public Conta getConta() { return conta; }
    public void setConta(Conta conta) { this.conta = conta; }
    public Character getTipo() { return tipo; }
    public void setTipo(Character tipo) { this.tipo = tipo; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public OffsetDateTime getDtEvento() { return dtEvento; }
    public void setDtEvento(OffsetDateTime dtEvento) { this.dtEvento = dtEvento; }
}
