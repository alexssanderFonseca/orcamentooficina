package br.com.oficina.orcamento.adapter.in.controller.mapper;

import br.com.oficina.orcamento.core.usecase.input.CriarOrcamentoInput;
import br.com.oficina.orcamento.core.usecase.output.BuscarOrcamentoPorIdOutput;
import br.com.oficina.orcamento.adapter.in.controller.request.CriarOrcamentoRequest;
import br.com.oficina.orcamento.adapter.in.controller.response.OrcamentoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrcamentoControllerMapper {

    CriarOrcamentoInput toInput(CriarOrcamentoRequest request);

    OrcamentoResponse toResponse(BuscarOrcamentoPorIdOutput orcamento);
}
