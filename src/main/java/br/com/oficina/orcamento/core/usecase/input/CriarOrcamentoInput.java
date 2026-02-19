package br.com.oficina.orcamento.core.usecase.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CriarOrcamentoInput {
    private ClienteInput cliente;
    private VeiculoInput veiculo;
    private List<ItemOrcamentoInput> itens;
    private BigDecimal valorTotal;
    private UUID ordemServicoId;
}