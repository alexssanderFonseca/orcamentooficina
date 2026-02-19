package br.com.oficina.orcamento.core.port.in;

import java.util.UUID;

public interface RecusarOrcamentoUseCase {
    void executar(UUID id);
}
