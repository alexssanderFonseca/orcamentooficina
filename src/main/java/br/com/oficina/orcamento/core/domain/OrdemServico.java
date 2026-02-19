package br.com.oficina.orcamento.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class OrdemServico {
    private UUID id;
    private Cliente cliente;
    private Veiculo veiculo;
    private List<ItemOrdemServico> itens;
}
