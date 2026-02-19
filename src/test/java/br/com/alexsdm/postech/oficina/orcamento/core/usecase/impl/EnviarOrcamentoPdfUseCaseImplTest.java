package br.com.alexsdm.postech.oficina.orcamento.core.usecase.impl;

import br.com.alexsdm.postech.oficina.orcamento.core.entity.Cliente;
import br.com.alexsdm.postech.oficina.orcamento.core.entity.Orcamento;
import br.com.alexsdm.postech.oficina.orcamento.core.entity.Veiculo;
import br.com.alexsdm.postech.oficina.orcamento.core.exception.OrcamentoNaoEncontradaException;
import br.com.alexsdm.postech.oficina.orcamento.core.port.out.OrcamentoRepository;
import br.com.alexsdm.postech.oficina.orcamento.core.service.PdfGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnviarOrcamentoPdfUseCaseImplTest {

    @Mock
    private OrcamentoRepository orcamentoRepository;
    @Mock
    private PdfGenerator pdfGenerator;

    @InjectMocks
    private EnviarOrcamentoPdfUseCaseImpl enviarOrcamentoPdfUseCase;

    @Test
    void deveGerarPdfComSucesso() {
        // Arrange
        var orcamentoId = UUID.randomUUID();
        var cliente = new Cliente(UUID.randomUUID(), "Nome", "123");
        var veiculo = new Veiculo(UUID.randomUUID(), "Marca", "Modelo", "ABC-1234");
        var orcamento = new Orcamento(orcamentoId, UUID.randomUUID(), cliente, veiculo, Collections.emptyList(), BigDecimal.ZERO);
        var pdfBytes = new byte[]{1, 2, 3};

        when(orcamentoRepository.buscarPorId(orcamentoId)).thenReturn(Optional.of(orcamento));
        when(pdfGenerator.generate(any(Orcamento.class))).thenReturn(pdfBytes);

        // Act
        byte[] result = enviarOrcamentoPdfUseCase.executar(orcamentoId);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.length);
    }

    @Test
    void deveLancarExcecaoQuandoOrcamentoNaoEncontradoParaEnviarPdf() {
        var orcamentoId = UUID.randomUUID();
        when(orcamentoRepository.buscarPorId(orcamentoId)).thenReturn(Optional.empty());
        assertThrows(OrcamentoNaoEncontradaException.class, () -> enviarOrcamentoPdfUseCase.executar(orcamentoId));
    }
}
