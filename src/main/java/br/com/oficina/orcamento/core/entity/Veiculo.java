package br.com.oficina.orcamento.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {
    private UUID id;
    private String marca;
    private String modelo;
    private String placa;
}
