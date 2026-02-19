package br.com.alexsdm.postech.oficina.orcamento.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Veiculo {
    private UUID id;
    private String placa;
    private String marca;
    private String modelo;
    private Integer ano;
}
