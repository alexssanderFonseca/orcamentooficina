package br.com.oficina.orcamento.core.usecase.output;

import br.com.oficina.orcamento.core.domain.Cliente;
import br.com.oficina.orcamento.core.entity.Orcamento;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record BuscarOrcamentoPorIdOutput(
        UUID id,
        BigDecimal valorTotal,
        String status,
        OrcamentoClienteOutput cliente,
        OrcamentoVeiculoOutput veiculo,
        List<OrcamentoItemOutput> itens
) {

    public static BuscarOrcamentoPorIdOutput toOutput(Orcamento orcamento,
                                                      Cliente cliente) {
        var itens = orcamento.getItens().stream()
                .map(item -> new OrcamentoItemOutput(
                        item.getItemId(),
                        item.getNome(),
                        item.getDescricao(),
                        item.getQuantidade(),
                        item.getPreco(),
                        item.getTipo()
                )).collect(Collectors.toList());

        var orcamentoClienteOutput = new OrcamentoClienteOutput(
                cliente.id().toString(),
                cliente.nome(),
                cliente.cpfCnpj()
        );

        // A entidade Cliente não possui mais dados do veículo
        var orcamentoVeiculoOutput = new OrcamentoVeiculoOutput(
                null,
                null,
                null,
                null,
                null
        );

        return new BuscarOrcamentoPorIdOutput(
                orcamento.getId(),
                orcamento.getValorTotal(),
                orcamento.getStatus().name(),
                orcamentoClienteOutput,
                orcamentoVeiculoOutput,
                itens
        );
    }
}
