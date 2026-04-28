CREATE TABLE veiculo (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao  VARCHAR(255) NOT NULL,
    marca      VARCHAR(50)  NOT NULL,
    modelo     VARCHAR(100) NOT NULL,
    opcionais  VARCHAR(500),
    valor      DECIMAL(15, 2)
);
