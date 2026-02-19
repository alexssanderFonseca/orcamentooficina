package br.com.alexsdm.postech.oficina.orcamento.adapter.in.controller.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrcamentoResponse(
        UUID id,
        BigDecimal valorTotal,
        OrcamentoClienteResponse cliente,
        OrcamentoVeiculoResponse veiculo,
        List<OrcamentoItemResponse> itens,
        String status
) {
}
