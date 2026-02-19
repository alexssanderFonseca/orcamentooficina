package br.com.alexsdm.postech.oficina.orcamento.core.usecase.impl;

import br.com.alexsdm.postech.oficina.orcamento.core.entity.Cliente;
import br.com.alexsdm.postech.oficina.orcamento.core.entity.Orcamento;
import br.com.alexsdm.postech.oficina.orcamento.core.entity.Veiculo;
import br.com.alexsdm.postech.oficina.orcamento.core.exception.OrcamentoNaoEncontradaException;
import br.com.alexsdm.postech.oficina.orcamento.core.port.out.OrcamentoRepository;
import br.com.alexsdm.postech.oficina.orcamento.core.usecase.output.BuscarOrcamentoPorIdOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarOrcamentoPorIdUseCaseImplTest {

    @Mock
    private OrcamentoRepository orcamentoRepository;

    @InjectMocks
    private BuscarOrcamentoPorIdUseCaseImpl buscarOrcamentoPorIdUseCase;

    @Test
    void deveBuscarOrcamentoPorIdComSucesso() {
        // Arrange
        var orcamentoId = UUID.randomUUID();
        var cliente = new Cliente(UUID.randomUUID(), "Nome", "123");
        var veiculo = new Veiculo(UUID.randomUUID(), "Marca", "Modelo", "ABC-1234");
        var orcamento = new Orcamento(orcamentoId, UUID.randomUUID(), cliente, veiculo, Collections.emptyList(), BigDecimal.ZERO);
        var domainCliente = new br.com.alexsdm.postech.oficina.orcamento.core.domain.Cliente(cliente.getId(), cliente.getNome(), cliente.getDocumento());
        var expectedOutput = new BuscarOrcamentoPorIdOutput(orcamentoId, null, null, null, null, null);

        when(orcamentoRepository.buscarPorId(orcamentoId)).thenReturn(Optional.of(orcamento));

        try (MockedStatic<BuscarOrcamentoPorIdOutput> mockedStatic = Mockito.mockStatic(BuscarOrcamentoPorIdOutput.class)) {
            mockedStatic.when(() -> BuscarOrcamentoPorIdOutput.toOutput(orcamento, domainCliente)).thenReturn(expectedOutput);

            // Act
            BuscarOrcamentoPorIdOutput result = buscarOrcamentoPorIdUseCase.executar(orcamentoId);

            // Assert
            assertNotNull(result);
            assertEquals(expectedOutput, result);
        }
    }

    @Test
    void deveLancarExcecaoQuandoOrcamentoNaoEncontrado() {
        var orcamentoId = UUID.randomUUID();
        when(orcamentoRepository.buscarPorId(orcamentoId)).thenReturn(Optional.empty());
        assertThrows(OrcamentoNaoEncontradaException.class, () -> buscarOrcamentoPorIdUseCase.executar(orcamentoId));
    }
}
