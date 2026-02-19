package br.com.oficina.orcamento.core.service;

import br.com.oficina.orcamento.core.entity.Orcamento;

public interface PdfGenerator {
    byte[] generate(Orcamento orcamento);
}
