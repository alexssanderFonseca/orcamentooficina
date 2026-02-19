package br.com.oficina.orcamento.core.usecase.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class VeiculoInput {
    private UUID id;
    private String marca;
    private String modelo;
    private String placa;
}
