package br.com.oficina.orcamento.core.exception;

public class OrcamentoNaoEncontradaException extends OrcamentoException {

    public OrcamentoNaoEncontradaException() {
        super("Orcamento não encontrado para o id solicitado");
    }
}