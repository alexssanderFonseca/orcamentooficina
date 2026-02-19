package br.com.alexsdm.postech.oficina.orcamento.core.usecase.input;

import jakarta.validation.constraints.NotNull; // Import for @NotNull
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal; // Import for BigDecimal
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ItemOrcamentoInput {
    @NotNull private UUID itemId;
    @NotNull private int quantidade;
    @NotNull private String tipo;
    @NotNull private String nome;
    private String descricao; // Descrição can be null
    @NotNull private BigDecimal precoVenda;
}
