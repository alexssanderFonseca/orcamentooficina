package br.com.oficina.orcamento.core.usecase.output;

public record OrcamentoClienteOutput(
        String id,
        String nome,
        String cpfCnpj
) {
}