package br.com.oficina.orcamento.adapter.out.persistence.entity;

import br.com.oficina.orcamento.core.entity.TipoItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "item_orcamento")
public class ItemOrcamentoEntity {

    @Id
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "orcamento_id", nullable = false)
    private OrcamentoEntity orcamento;

    @Column(name = "item_id", nullable = false)
    private UUID itemId;

    private String nome;

    private Integer quantidade;

    private BigDecimal preco;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;
}
