CREATE TABLE orcamento (
    id UUID PRIMARY KEY,
    cliente_id UUID NOT NULL,
    cliente_nome VARCHAR(255) NOT NULL,
    cliente_documento VARCHAR(255) NOT NULL,
    veiculo_id UUID NOT NULL,
    veiculo_marca VARCHAR(255) NOT NULL,
    veiculo_modelo VARCHAR(255) NOT NULL,
    veiculo_placa VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    valor_total NUMERIC(12, 2) NOT NULL
);

CREATE TABLE item_orcamento (
    id UUID PRIMARY KEY,
    orcamento_id UUID NOT NULL,
    item_id UUID NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    preco NUMERIC(12, 2) NOT NULL,
    quantidade INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    FOREIGN KEY (orcamento_id) REFERENCES orcamento(id)
);
