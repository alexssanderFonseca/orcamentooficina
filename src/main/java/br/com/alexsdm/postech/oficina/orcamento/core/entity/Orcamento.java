package br.com.alexsdm.postech.oficina.orcamento.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Orcamento {

    private UUID id;
    private UUID ordemServicoId;
    private Cliente cliente;
    private Veiculo veiculo;
    private List<ItemOrcamento> itens;
    private OrcamentoStatus status;
    private BigDecimal valorTotal;

    public Orcamento(
            UUID id,
            UUID ordemServicoId,
            Cliente cliente,
            Veiculo veiculo,
            List<ItemOrcamento> itens,
            BigDecimal valorTotal) {
                    this.id = id;
                    this.ordemServicoId = ordemServicoId;
                    this.cliente = cliente;        this.veiculo = veiculo;
        this.itens = itens;
        this.status = OrcamentoStatus.CRIADO;
        this.valorTotal = valorTotal;
    }

    public void aceitar() {
        this.status = OrcamentoStatus.ACEITO;
    }

    public boolean isAceito() {
        return OrcamentoStatus.ACEITO.equals(this.status);
    }

    public void recusar() {
        this.status = OrcamentoStatus.RECUSADO;
    }
}

