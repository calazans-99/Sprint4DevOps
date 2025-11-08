CREATE TABLE Conta (
    id_conta       INT IDENTITY(1,1) PRIMARY KEY,
    nome_titular   VARCHAR(120) NOT NULL,
    email          VARCHAR(200) NOT NULL UNIQUE,
    dt_criacao     DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME()
);

CREATE TABLE Lancamento (
    id_lcto   INT IDENTITY(1,1) PRIMARY KEY,
    id_conta  INT NOT NULL,
    tipo      CHAR(1) NOT NULL CHECK (tipo IN ('C','D')),
    valor     DECIMAL(12,2) NOT NULL CHECK (valor > 0),
    descricao VARCHAR(255) NULL,
    dt_evento DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),
    CONSTRAINT fk_lcto_conta FOREIGN KEY (id_conta) REFERENCES Conta(id_conta)
);
