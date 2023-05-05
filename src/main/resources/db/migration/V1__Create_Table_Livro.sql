CREATE TABLE IF NOT EXISTS tb_livro (
    id bigserial not null,
    autor varchar(255),
    editora varchar(255),
    img_url varchar(255),
    nome_categoria varchar(255),
    numero_de_paginas integer,
    preco float(53),
    titulo varchar(255),
    categoria_id bigint NOT NULL,
    PRIMARY KEY (id)
);