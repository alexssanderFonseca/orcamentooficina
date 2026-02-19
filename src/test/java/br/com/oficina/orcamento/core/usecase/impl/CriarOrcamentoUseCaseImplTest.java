package br.com.oficina.orcamento.core.usecase.impl;

import br.com.oficina.orcamento.core.entity.Cliente;
import br.com.oficina.orcamento.core.entity.Item;
import br.com.oficina.orcamento.core.entity.Orcamento;
import br.com.oficina.orcamento.core.entity.Veiculo;
import br.com.oficina.orcamento.core.port.out.OrcamentoRepository;
import br.com.oficina.orcamento.core.usecase.input.ClienteInput;
import br.com.oficina.orcamento.core.usecase.input.CriarOrcamentoInput;
import br.com.oficina.orcamento.core.usecase.input.ItemOrcamentoInput;
import br.com.oficina.orcamento.core.usecase.input.VeiculoInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarOrcamentoUseCaseImplTest {

    @Mock
    private OrcamentoRepository orcamentoRepository;

    @InjectMocks
    private CriarOrcamentoUseCaseImpl criarOrcamentoUseCase;

    private CriarOrcamentoInput input;
    private UUID clienteId;
    private UUID veiculoId;
    private UUID pecaId;
    private UUID servicoId;

    @BeforeEach
    void setUp() {
        clienteId = UUID.randomUUID();
        veiculoId = UUID.randomUUID();
        pecaId = UUID.randomUUID();
        servicoId = UUID.randomUUID();

        var itemPecaInput = new ItemOrcamentoInput();
        itemPecaInput.setItemId(pecaId);
        itemPecaInput.setQuantidade(2);
        itemPecaInput.setTipo("PECA");

        var itemServicoInput = new ItemOrcamentoInput();
        itemServicoInput.setItemId(servicoId);
        itemServicoInput.setQuantidade(1);
        itemServicoInput.setTipo("SERVICO");

        var clienteInput = new ClienteInput();
        clienteInput.setId(clienteId);
        clienteInput.setNome("Nome Cliente");
        clienteInput.setDocumento("12345678901");

        var veiculoInput = new VeiculoInput();
        veiculoInput.setId(veiculoId);
        veiculoInput.setMarca("Marca");
        veiculoInput.setModelo("Modelo");
        veiculoInput.setPlaca("ABC-1234");

        input = new CriarOrcamentoInput();
        input.setCliente(clienteInput);
        input.setVeiculo(veiculoInput);
        input.setItens(List.of(itemPecaInput, itemServicoInput));
        input.setValorTotal(new BigDecimal("200.00"));
    }

    @Test
    void deveCriarOrcamentoComSucesso() {
        // Arrange
        var itemPeca = new Item(pecaId, "Filtro", "Desc Peca", new BigDecimal("50.00"), "PECA");
        var itemServico = new Item(servicoId, "Troca de Oleo", "Desc Servico", new BigDecimal("100.00"), "SERVICO");
        var cliente = new Cliente(clienteId, "Nome Cliente", "12345678901");
        var veiculo = new Veiculo(veiculoId, "Marca", "Modelo", "ABC-1234");
        var orcamentoSalvo = new Orcamento(UUID.randomUUID(), UUID.randomUUID(), cliente, veiculo, List.of(), BigDecimal.ZERO);

        when(orcamentoRepository.salvar(any(Orcamento.class))).thenReturn(orcamentoSalvo);

        // Act
        UUID orcamentoId = criarOrcamentoUseCase.executar(input);

        // Assert
        assertNotNull(orcamentoId);
        ArgumentCaptor<Orcamento> orcamentoCaptor = ArgumentCaptor.forClass(Orcamento.class);
        verify(orcamentoRepository).salvar(orcamentoCaptor.capture());
        Orcamento orcamentoCriado = orcamentoCaptor.getValue();
        assertEquals(2, orcamentoCriado.getItens().size());
        assertEquals(clienteId, orcamentoCriado.getCliente().getId());
    }

}
