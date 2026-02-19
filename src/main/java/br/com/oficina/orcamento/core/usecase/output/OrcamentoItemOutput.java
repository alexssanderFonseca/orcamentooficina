package br.com.oficina.orcamento.core.usecase.output;

import br.com.oficina.orcamento.core.entity.TipoItem;

import java.math.BigDecimal;
import java.util.UUID;

public record OrcamentoItemOutput(
        UUID itemId,
        String nome,
        String descricao,
        Integer quantidade,
        BigDecimal preco,
        TipoItem tipo
) {
}
