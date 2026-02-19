package br.com.oficina.orcamento.adapter.in.controller.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ClienteRequest(
    @NotNull UUID id,
    @NotNull String nome,
    @NotNull String documento
) {
}
