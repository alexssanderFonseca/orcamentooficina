package br.com.alexsdm.postech.oficina.orcamento.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity(name = "orcamento")
@Getter
@Setter
@NoArgsConstructor
public class OrcamentoEntity {

    @Id
    private UUID id;

    @Column(name = "ordem_servico_id")
    private UUID ordemServicoId;

    private UUID clienteId;
    private String clienteNome;
    private String clienteDocumento;

    private UUID veiculoId;
    private String veiculoMarca;
    private String veiculoModelo;
    private String veiculoPlaca;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "orcamento_id")
    private List<ItemOrcamentoEntity> itens;

    private OrcamentoStatusEntity status;

    private BigDecimal valorTotal;
}
