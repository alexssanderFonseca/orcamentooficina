package br.com.oficina.orcamento.core.entity;

import java.math.BigDecimal;
import java.util.UUID;

public record Item(
        UUID id,
        String nome,
        String descricao,
        BigDecimal precoVenda,
        String tipo
) {
}
