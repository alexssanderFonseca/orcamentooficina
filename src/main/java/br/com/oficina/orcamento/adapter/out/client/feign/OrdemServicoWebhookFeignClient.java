package br.com.oficina.orcamento.adapter.out.client.feign;

import br.com.oficina.orcamento.adapter.out.client.feign.dto.OrcamentoAprovacaoWebhookPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ordem-servico-webhook", url = "${feign.client.config.ordem-servico-webhook.url}")
public interface OrdemServicoWebhookFeignClient {

    @PostMapping("/webhooks/orcamento/aprovacao")
    void enviarAprovacaoWebhook(@RequestBody OrcamentoAprovacaoWebhookPayload payload);
}
