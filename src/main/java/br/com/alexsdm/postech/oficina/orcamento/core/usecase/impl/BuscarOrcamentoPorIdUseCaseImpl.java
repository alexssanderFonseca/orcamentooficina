package br.com.alexsdm.postech.oficina.orcamento.core.usecase.impl;

import br.com.alexsdm.postech.oficina.orcamento.core.port.out.OrcamentoRepository;
import br.com.alexsdm.postech.oficina.orcamento.core.port.in.BuscarOrcamentoPorIdUseCase;
import br.com.alexsdm.postech.oficina.orcamento.core.usecase.output.BuscarOrcamentoPorIdOutput;
import br.com.alexsdm.postech.oficina.orcamento.core.exception.OrcamentoNaoEncontradaException;
import br.com.alexsdm.postech.oficina.orcamento.core.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuscarOrcamentoPorIdUseCaseImpl implements BuscarOrcamentoPorIdUseCase {

    private final OrcamentoRepository orcamentoRepository;

    @Override
    public BuscarOrcamentoPorIdOutput executar(UUID id) {
        var orcamento = orcamentoRepository.buscarPorId(id)
                .orElseThrow(OrcamentoNaoEncontradaException::new);
        var cliente = new Cliente(orcamento.getCliente().getId(), orcamento.getCliente().getNome(), orcamento.getCliente().getDocumento());
        return BuscarOrcamentoPorIdOutput.toOutput(orcamento, cliente);
    }
}
