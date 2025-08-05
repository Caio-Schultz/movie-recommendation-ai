CREATE TYPE genero_enum as ENUM(
'ACAO', 'AVENTURA', 'ANIMACAO', 'COMEDIA', 'DRAMA', 'FICCAO CIENTIFICA', 'ROMANCE', 'SUSPENSE', 'TERROR', 'OUTROS');

CREATE TYPE faixa_etaria_enum as ENUM(
'LIVRE', 'MAIS_10', 'MAIS_12', 'MAIS_14', 'MAIS_16', 'MAIS_18');


CREATE TABLE tb_filme(
id BIGINT PRIMARY KEY,
nome VARCHAR(255) UNIQUE NOT NULL,
faixa_etaria faixa_etaria_enum NOT NULL,
genero genero_enum NOT NULL,
descricao TEXT
);