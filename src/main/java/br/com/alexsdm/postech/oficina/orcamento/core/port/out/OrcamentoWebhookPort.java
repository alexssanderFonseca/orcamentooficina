package br.com.alexsdm.postech.oficina.orcamento.core.port.out;

import java.util.UUID;

public interface OrcamentoWebhookPort {
    void enviarAprovacao(UUID orcamentoId, UUID ordemServicoId);
}