CREATE TABLE IF NOT EXISTS tb_livro (
    id bigserial not null,
    titulo varchar(255),
    autor varchar(255),
    editora varchar(255),
    numero_de_paginas integer,
    img_url varchar(255),
    preco float(53),
    nome_categoria varchar(255),
    categoria_id bigint NOT NULL,
    username_usuario varchar(30) not null,
    usuario_id bigint not null,
    PRIMARY KEY (id)
);