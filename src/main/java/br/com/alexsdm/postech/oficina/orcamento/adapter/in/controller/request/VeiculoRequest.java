package br.com.alexsdm.postech.oficina.orcamento.adapter.in.controller.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record VeiculoRequest(
    @NotNull UUID id,
    @NotNull String marca,
    @NotNull String modelo,
    @NotNull String placa
) {
}
