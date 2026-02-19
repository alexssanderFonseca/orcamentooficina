package br.com.oficina.orcamento.adapter.out.persistence.mapper;

import br.com.oficina.orcamento.adapter.out.persistence.entity.ItemOrcamentoEntity;
import br.com.oficina.orcamento.adapter.out.persistence.entity.OrcamentoEntity;
import br.com.oficina.orcamento.adapter.out.persistence.entity.OrcamentoStatusEntity;
import br.com.oficina.orcamento.core.entity.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrcamentoMapper {

    public Orcamento toDomain(OrcamentoEntity entity) {
        var domain = new Orcamento();
        domain.setId(entity.getId());
        domain.setCliente(new Cliente(entity.getClienteId(), entity.getClienteNome(), entity.getClienteDocumento()));
        domain.setVeiculo(new Veiculo(entity.getVeiculoId(), entity.getVeiculoMarca(), entity.getVeiculoModelo(), entity.getVeiculoPlaca()));
        domain.setStatus(OrcamentoStatus.valueOf(entity.getStatus().name()));
        domain.setValorTotal(entity.getValorTotal());
        domain.setItens(entity.getItens().stream().map(this::toDomain).collect(Collectors.toList()));
        domain.setOrdemServicoId(entity.getOrdemServicoId());
        return domain;
    }

    public OrcamentoEntity toEntity(Orcamento domain) {
        var entity = new OrcamentoEntity();
        entity.setId(domain.getId());
        entity.setOrdemServicoId(domain.getOrdemServicoId());
        entity.setClienteId(domain.getCliente().getId());
        entity.setClienteNome(domain.getCliente().getNome());
        entity.setClienteDocumento(domain.getCliente().getDocumento());
        entity.setVeiculoId(domain.getVeiculo().getId());
        entity.setVeiculoMarca(domain.getVeiculo().getMarca());
        entity.setVeiculoModelo(domain.getVeiculo().getModelo());
        entity.setVeiculoPlaca(domain.getVeiculo().getPlaca());
        entity.setStatus(OrcamentoStatusEntity.valueOf(domain.getStatus().name()));
        entity.setValorTotal(domain.getValorTotal());
        entity.setItens(domain.getItens().stream()
                .map(item -> toEntity(item, entity))
                .toList());
        return entity;
    }

    private ItemOrcamento toDomain(ItemOrcamentoEntity entity) {
        return ItemOrcamento.builder()
                .id(entity.getId())
                .itemId(entity.getItemId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .preco(entity.getPreco())
                .quantidade(entity.getQuantidade())
                .tipo(entity.getTipo())
                .build();
    }

    private ItemOrcamentoEntity toEntity(ItemOrcamento domain, OrcamentoEntity orcamentoEntity) {
        var entity = new ItemOrcamentoEntity();
        entity.setId(domain.getId());
        entity.setOrcamento(orcamentoEntity);
        entity.setItemId(domain.getItemId());
        entity.setNome(domain.getNome());
        entity.setPreco(domain.getPreco());
        entity.setQuantidade(domain.getQuantidade());
        entity.setDescricao(domain.getDescricao());
        entity.setTipo(domain.getTipo());
        return entity;
    }
}
