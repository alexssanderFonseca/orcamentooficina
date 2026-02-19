package br.com.alexsdm.postech.oficina.orcamento.core.usecase.impl;

import br.com.alexsdm.postech.oficina.orcamento.core.entity.*;
import br.com.alexsdm.postech.oficina.orcamento.core.port.in.CriarOrcamentoUseCase;
import br.com.alexsdm.postech.oficina.orcamento.core.port.out.OrcamentoRepository;
import br.com.alexsdm.postech.oficina.orcamento.core.usecase.input.CriarOrcamentoInput;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Named
@RequiredArgsConstructor
public class CriarOrcamentoUseCaseImpl implements CriarOrcamentoUseCase {

    private final OrcamentoRepository orcamentoRepository;

    @Override
    public UUID executar(CriarOrcamentoInput input) {
        var cliente = new Cliente(input.getCliente().getId(),
                input.getCliente().getNome(),
                input.getCliente().getDocumento());

        var veiculo = new Veiculo(input.getVeiculo().getId(),
                input.getVeiculo().getMarca(),
                input.getVeiculo().getModelo(),
                input.getVeiculo().getPlaca());

        var orcamento = new Orcamento(
                UUID.randomUUID(),
                input.getOrdemServicoId(),
                cliente,
                veiculo,
                montarItens(input),
                input.getValorTotal()
        );
        return orcamentoRepository.salvar(orcamento).getId();
    }

    private List<ItemOrcamento> montarItens(CriarOrcamentoInput input) {
        return input.getItens().stream()
                .map(itemInput -> ItemOrcamento.builder()
                        .id(UUID.randomUUID())
                        .itemId(itemInput.getItemId())
                        .nome(itemInput.getNome())
                        .preco(itemInput.getPrecoVenda())
                        .descricao(itemInput.getDescricao())
                        .quantidade(itemInput.getQuantidade())
                        .tipo(TipoItem.valueOf(itemInput.getTipo())) // Assuming TipoItem can be created from String
                        .build())
                .toList();
    }
}
