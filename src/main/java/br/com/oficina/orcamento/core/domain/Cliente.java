package br.com.oficina.orcamento.core.domain;

import java.util.UUID;

public record Cliente(
    UUID id,
    String nome,
    String cpfCnpj
) {
}
