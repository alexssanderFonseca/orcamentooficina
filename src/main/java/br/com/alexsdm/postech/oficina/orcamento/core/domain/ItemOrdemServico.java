package br.com.alexsdm.postech.oficina.orcamento.core.domain;

import br.com.alexsdm.postech.oficina.orcamento.core.entity.TipoItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ItemOrdemServico {
    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;
    private UUID itemId;
    private TipoItem tipo;
}
