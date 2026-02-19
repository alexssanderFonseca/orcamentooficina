package br.com.oficina.orcamento.adapter.in.controller.response;

import br.com.oficina.orcamento.core.entity.TipoItem;

import java.math.BigDecimal;
import java.util.UUID;

public record OrcamentoItemResponse(
        UUID itemId,
        String nome,
        String descricao,
        Integer quantidade,
        BigDecimal preco,
        TipoItem tipo
) {
}
