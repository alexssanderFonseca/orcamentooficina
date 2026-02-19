package br.com.alexsdm.postech.oficina.orcamento.core.usecase.impl;

import br.com.alexsdm.postech.oficina.orcamento.core.port.out.OrcamentoRepository;
import br.com.alexsdm.postech.oficina.orcamento.core.port.in.AprovarOrcamentoUseCase;
import br.com.alexsdm.postech.oficina.orcamento.core.exception.OrcamentoNaoEncontradaException;
import br.com.alexsdm.postech.oficina.orcamento.core.port.out.OrcamentoWebhookPort; // Re-added import
import lombok.RequiredArgsConstructor; // Re-added import
import org.springframework.stereotype.Service; // Re-added import

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AprovarOrcamentoUseCaseImpl implements AprovarOrcamentoUseCase {

    private final OrcamentoRepository orcamentoRepository;
    private final OrcamentoWebhookPort orcamentoWebhookPort; // Injected port

    @Override
    public void executar(UUID id) {
        var orcamento = orcamentoRepository.buscarPorId(id)
                .orElseThrow(OrcamentoNaoEncontradaException::new);
        orcamento.aceitar();
        orcamentoRepository.salvar(orcamento);
        orcamentoWebhookPort.enviarAprovacao(orcamento.getId(), orcamento.getOrdemServicoId());
    }
}
