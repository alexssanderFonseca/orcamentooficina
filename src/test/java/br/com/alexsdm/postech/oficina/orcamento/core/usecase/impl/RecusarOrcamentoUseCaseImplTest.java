package br.com.alexsdm.postech.oficina.orcamento.core.usecase.impl;

import java.math.BigDecimal;
import br.com.alexsdm.postech.oficina.orcamento.core.entity.Cliente;
import br.com.alexsdm.postech.oficina.orcamento.core.entity.Orcamento;
import br.com.alexsdm.postech.oficina.orcamento.core.entity.OrcamentoStatus;
import br.com.alexsdm.postech.oficina.orcamento.core.entity.Veiculo;
import br.com.alexsdm.postech.oficina.orcamento.core.exception.OrcamentoNaoEncontradaException;
import br.com.alexsdm.postech.oficina.orcamento.core.port.out.OrcamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecusarOrcamentoUseCaseImplTest {

    @Mock
    private OrcamentoRepository orcamentoRepository;

    @InjectMocks
    private RecusarOrcamentoUseCaseImpl recusarOrcamentoUseCase;

    @Test
    void deveRecusarOrcamentoComSucesso() {
        // Arrange
        var orcamentoId = UUID.randomUUID();
        var cliente = new Cliente(UUID.randomUUID(), "Nome", "123");
        var veiculo = new Veiculo(UUID.randomUUID(), "Marca", "Modelo", "ABC-1234");
        var orcamento = new Orcamento(orcamentoId, UUID.randomUUID(), cliente, veiculo, Collections.emptyList(), BigDecimal.ZERO);
        assertEquals(OrcamentoStatus.CRIADO, orcamento.getStatus()); // Sanity check

        when(orcamentoRepository.buscarPorId(orcamentoId)).thenReturn(Optional.of(orcamento));

        // Act
        recusarOrcamentoUseCase.executar(orcamentoId);

        // Assert
        ArgumentCaptor<Orcamento> orcamentoCaptor = ArgumentCaptor.forClass(Orcamento.class);
        verify(orcamentoRepository, times(1)).salvar(orcamentoCaptor.capture());
        Orcamento orcamentoSalvo = orcamentoCaptor.getValue();

        assertEquals(OrcamentoStatus.RECUSADO, orcamentoSalvo.getStatus());
    }

    @Test
    void deveLancarExcecaoQuandoOrcamentoNaoEncontradoParaRecusar() {
        // Arrange
        var orcamentoId = UUID.randomUUID();
        when(orcamentoRepository.buscarPorId(orcamentoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(OrcamentoNaoEncontradaException.class, () -> {
            recusarOrcamentoUseCase.executar(orcamentoId);
        });

        verify(orcamentoRepository, never()).salvar(any(Orcamento.class));
    }
}
