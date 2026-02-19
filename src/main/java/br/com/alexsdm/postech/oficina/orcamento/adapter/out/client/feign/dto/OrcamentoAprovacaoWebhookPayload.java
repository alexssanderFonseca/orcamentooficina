package br.com.alexsdm.postech.oficina.orcamento.adapter.out.client.feign.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record OrcamentoAprovacaoWebhookPayload(
        @NotNull UUID orcamentoId,
        @NotNull UUID ordemServicoId
) { }