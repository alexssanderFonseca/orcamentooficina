package br.com.oficina.orcamento.core.port.out;

import br.com.oficina.orcamento.core.entity.Orcamento;

import java.util.Optional;
import java.util.UUID;

public interface OrcamentoRepository {
    Orcamento salvar(Orcamento orcamento);

    Optional<Orcamento> buscarPorId(UUID id);
}
