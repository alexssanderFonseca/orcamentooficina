package br.com.oficina.orcamento.core.port.in;

import java.util.UUID;

public interface EnviarOrcamentoPdfUseCase {
    byte[] executar(UUID id);
}
