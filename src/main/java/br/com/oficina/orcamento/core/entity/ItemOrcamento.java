package br.com.oficina.orcamento.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemOrcamento {
    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;
    private UUID itemId;
    private TipoItem tipo;
    
    public BigDecimal getTotal() {
        return this.preco.multiply(BigDecimal.valueOf(quantidade));
    }
}
