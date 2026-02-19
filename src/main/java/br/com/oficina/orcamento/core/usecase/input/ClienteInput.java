package br.com.oficina.orcamento.core.usecase.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ClienteInput {
    private UUID id;
    private String nome;
    private String documento;
}
