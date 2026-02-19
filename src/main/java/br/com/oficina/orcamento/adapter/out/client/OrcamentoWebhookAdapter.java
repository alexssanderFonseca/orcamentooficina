package br.com.oficina.orcamento.adapter.out.client;

import br.com.oficina.orcamento.adapter.out.client.feign.OrdemServicoWebhookFeignClient;
import br.com.oficina.orcamento.adapter.out.client.feign.dto.OrcamentoAprovacaoWebhookPayload;
import br.com.oficina.orcamento.core.port.out.OrcamentoWebhookPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrcamentoWebhookAdapter implements OrcamentoWebhookPort {

    private static final Logger logger = LoggerFactory.getLogger(OrcamentoWebhookAdapter.class);
    private final OrdemServicoWebhookFeignClient webhookFeignClient;

    @Override
    public void enviarAprovacao(UUID orcamentoId, UUID ordemServicoId) {
        logger.info("Enviando webhook de aprovação para Ordem de Serviço ID: {} e Orçamento ID: {}", ordemServicoId, orcamentoId);
        var payload = new OrcamentoAprovacaoWebhookPayload(orcamentoId, ordemServicoId);
        try {
            webhookFeignClient.enviarAprovacaoWebhook(payload);
            logger.info("Webhook de aprovação enviado com sucesso para Ordem de Serviço ID: {}, orcamentoID: {}", ordemServicoId, orcamentoId);
        } catch (Exception e) {
            logger.error("Erro ao enviar webhook de aprovação para Ordem de Serviço ID: {}. Erro: {}", ordemServicoId, e.getMessage(), e);
            throw new RuntimeException("Falha ao enviar webhook de aprovação.", e);
        }
    }
}
